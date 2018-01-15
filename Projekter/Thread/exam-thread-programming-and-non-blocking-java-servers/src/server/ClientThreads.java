package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author xu
 */
public class ClientThreads extends Thread {

    private Socket socket;// Forbindelse
    private TcpServer masterServer;
    private PrintWriter writer; // class, Prints formatted representations of objects to a text-output stream.
    private Scanner reader; // 
    private String role = "";

    public ClientThreads(Socket socket, TcpServer masterServer) throws IOException {
        this.socket = socket;
        this.masterServer = masterServer;
        this.reader = new Scanner(socket.getInputStream()); //return en intput stream fra this socket
        this.writer = new PrintWriter(socket.getOutputStream(), true); //Returns an output stream for this socket. kaster IOException
    }

    public void sendMessage(String msg) {
        writer.println(msg);
    }
    
    public String getRole(){
        return role;
    }
    
    public void setRole(String role){
        this.role = role;
    }

    @Override
    public void run() {

        this.masterServer.addClientThread(this);// opret denne klasse til masterServer listen

        System.out.println("Ny forbindelsen er oprettet!");
        sendMessage("Ny forbindelsen er oprettet!");
        sendMessage("---- Commands: ---- \n turnstile (Sets role to turnstile) \n monitor (Sets role to monitor \n count (promts the server to count up - requires turnstile role) \n showcount (shows the current count - requires monitor or turnstile role) \n spectators (show amount of connected spectators - requires monitor role)");
        
        String message = reader.nextLine();

        while (!message.toUpperCase().equals("QUIT")) {
            switch (message.toUpperCase()) {
                case "TURNSTILE":
                    if (this.role == "") {
                        this.setRole("Turnstile");
                        sendMessage("Noted as a turnstile");
                   
                    }
                    else{
                        sendMessage("Role already set, you have role:" + this.getRole());
                    }
                    break;
                case "MONITOR":
                    if(this.role.equals("")){
                        this.setRole("Monitor");
                        sendMessage("Noted as a monitor");
                    }
                    else{
                        sendMessage("Role already set, you have role:" + this.getRole());
                    }
                    break;
                case "COUNT":
                    if(this.role.equals("Turnstile")){
                        this.masterServer.inc();
                        sendMessage("Counted +1 " + "(total count is:"+ String.valueOf(this.masterServer.getCount() + ")"));
                    }
                    else{
                        sendMessage("You dont have rigths to acces this counter, you need to have turnstile role");
                    }
                    break;
                case "SHOWCOUNT":
                    if(this.role.equals("Turnstile") || this.getRole().equals("Monitor")){
                        sendMessage("Count is:" + String.valueOf(this.masterServer.getCount()));
                    }
                    else{
                        sendMessage("You need either Turnstile og Monitor role to view the count");
                    }
                    break;
                    
                case "SPECTATORS": // viser hvor mange clients er connection
                    if(this.role.equals("Monitor")){
                        sendMessage("Amount of connected spectators " + this.masterServer.spectators());
                    }
                    else{
                        sendMessage("You dont have rigths to acces this counter, you need to have turnstile role");
                    }
                    break;
                default:
                    sendMessage("Invalid value" + message);
                    break;
            }
            message = reader.nextLine();
        }
        try {
            System.out.println("Forbindelsen er lukket!"); // debug
            sendMessage("Forbindelsen er lukket!");
            this.masterServer.removeClientThread(this); // fjerner fra masterServer listen
            socket.close();// lukker forbindelsen

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
