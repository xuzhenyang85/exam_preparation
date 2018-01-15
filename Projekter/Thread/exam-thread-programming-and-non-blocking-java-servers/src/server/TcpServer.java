package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author xu
 */
public class TcpServer {
    
    public static int PORT = 8080; // static gør variable tilgændige altid og kan deles
    public static String IP = "127.0.0.1";
    public long count = 0;
    public int specCount = 0; // 
    
    // ArrayList<> hører ind collections. Undgå race condition, return tråd sikkert list
    private final List<ClientThreads> clientThreads = Collections.synchronizedList(new ArrayList<>());
    
    public void addClientThread(ClientThreads h)
    {
        clientThreads.add(h);
    }

    public void removeClientThread(ClientThreads h)
    {
        clientThreads.remove(h);
    }
    
    public void listenForClients() throws IOException
    {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(IP, PORT));
        while (true)
        {
            Socket socket = serverSocket.accept(); //Important Blocking call
            new ClientThreads(socket, this).start();
            
        }
    }
    
    public synchronized void inc(){
        count++;
    }
    
    public long getCount(){
        return count;
    }
    /**
     * Hod styr på hvor mange klienter er connect
     * @return en tal
     */
    public int spectators(){
        specCount = 0;
        for (ClientThreads clientThread : clientThreads) {
            specCount++;
        }
        return specCount;
    }
    
    public static void main(String[] args) throws IOException {
        //String.format: convert den value til string med custom format, %s betyder string
        System.out.println(String.format("Server Startet, bound to: %s. Listening on: %d", IP, PORT));
        new TcpServer().listenForClients();
    }
    
}
