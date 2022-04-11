package Projekti;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  
  private final ServerSocket serverSocket;

  public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
  }
  
  public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                InetAddress my_localhost = InetAddress.getLocalHost();
                System.out.println("The IP Address of client is : " + (my_localhost.getHostAddress()).trim());
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }  
  
     public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  
  public static void main (String [] args)throws IOException {
        ServerSocket serverSocket = new ServerSocket(4689);
        Server server = new Server(serverSocket);
        server.startServer();
     
     }
}
