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
  
     public void sendMessageToClient(String messageToClient) {
    	try {
    		bufferedWriter.write(messageToClient);
    		bufferedWriter.newLine();
    		bufferedWriter.flush();
     	}
    	catch (IOException e){
    		e.printStackTrace();
    		System.out.println("Error sending message to the client!");
    		closeEverything(socket,bufferedReader, bufferedWriter);
    	}
    }
    
    public void recieveMessageFromClient() {
		new Thread (new Runnable(){
			@Override
			public void run() {
				while(socket.isConnected()) {
					try {
						String messageFromClient = bufferedReader.readLine();
						
					}catch (IOException e) {
						e.printStackTrace();
						System.out.println("Error recievingh message from the client");
						closeEverything(socket, bufferedReader, bufferedWriter);
						break;
					}
				}
			}
		}).start();
	}
	
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
      	 try {
               if (bufferedReader != null) {
                   bufferedReader.close();
               }
               if (bufferedWriter != null) {
                   bufferedWriter.close();
               }
               if (socket != null) {
                   socket.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       
      }
	
    
  
  public static void main (String [] args){
     
     }
}
