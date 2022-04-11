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
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }  
  
  public static void main (String [] args){
     
     }
}
