package Projekti;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
   
   private Socket socket;
   private BufferedReader bufferedReader;
   private BufferedWriter bufferedWriter;
   private String username;
   
   public Client (Socket socket, String username){
      try{
            this.socket=socket; 
            this.username = username;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      } catch (IOxception e){
            closeEverything(socket, bufferedReader, bufferedWriter);
      }
   
   }
   
   public void sendMessage(){
      try{
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            Scanner scanner = new Scanner(System.in);
            bufferedWriter.flush();
      } while (socket.isConnected()) {
              String messageToSend = scanner.nextLine();
              bufferedWriter.write(username + ": " + messageToSend);
              bufferedWriter.newLine();
              bufferedWriter.flush();
       } catch (IOException e) {
              System.out.println("Error sending message to the client");
              closeEverything(socket, bufferedReader, bufferedWriter);
        }
   }
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = bufferedReader.readLine();
                        String messageFromServer = bufferedReader.readLine();
                        System.out.println(msgFromGroupChat);
                    } catch (IOException e) {
                       e.printStackTrace();
                    	System.out.println("Error recieving message from the client");
                        closeEverything(socket, bufferedReader, bufferedWriter);
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
        
   public static void main (String [] args) throws IOException {
      try {
    	      File myObj = new File("C:\\\\Users\\\\Admin\\\\Desktop\\\\VM Argument.txt");
    	      Scanner myReader = new Scanner(myObj);  
    	      while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                FileWriter myWriter = new FileWriter("C:\\Users\\Admin\\Desktop\\source.txt");
                myWriter.write("Files in Java might be tricky, but it is fun enough!");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
    	      }
               myReader.close();
    	    } catch (FileNotFoundException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
      
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter your username for the group chat: ");
      String username = scanner.nextLine();
      Socket socket = new Socket("localhost", 7251);
      
      Client client = new Client(socket, username);
      client.listenForMessage();
      client.sendMessage(username);
               
   }
}
