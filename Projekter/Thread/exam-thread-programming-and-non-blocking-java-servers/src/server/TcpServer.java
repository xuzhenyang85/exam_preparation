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
    public long count = 0; // brugt til counter gæsterne i incr(), long fordi der kan være stort
    public int specCount = 0; // brugt til counter tråder i trådlisten
    
    // ArrayList<> hører ind collections. Undgå race condition, return tråd sikkert list
    private final List<ClientThreads> clientThreads = Collections.synchronizedList(new ArrayList<>());
    
    /**
     * Add new client thread to List<ClientThreads>
     * @param Client Thread
     */
    public void addClientThread(ClientThreads h)
    {
        clientThreads.add(h);
    }

    /**
     * remove the specific client thread 
     * @param Client Thread 
     */
    public void removeClientThread(ClientThreads h)
    {
        clientThreads.remove(h);
    }
    
    /**
     * Oprettelse af ny tråd med klient forbindelse
     * @throws IOException 
     */
    public void listenForClients() throws IOException
    {
        // server til forbindelsen, venter for nogen
        ServerSocket serverSocket = new ServerSocket(); // class imp server socket, den venter for request til der kommer in fra nettet
        serverSocket.bind(new InetSocketAddress(IP, PORT)); //binder den serversocket til bestemt ip og port, kaster exceptions(IO, security, illegal), inetSocketAddress, opret socketAddress fra en IP adress og Port nr
        
        while (true)
        {
            Socket socket = serverSocket.accept(); // hører fra en forbindelse til denne socket og acceptere det, returner en ny socket, kaster exceptions(IO, Security, SocketTimeout, IllegalBlockingMode)
            new ClientThreads(socket, this).start(); // start() excute run() i tråde og run koderne
        }
    }
    
    /**
     * synchronized, metoden der counter gæsterne 
     */
    public synchronized void inc(){
        count++;
    }
    
    /**
     * getter count
     * @return et tal
     */
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
        new TcpServer().listenForClients(); // Kører serveren
    }
    
}
