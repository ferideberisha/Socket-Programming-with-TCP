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
      }
   
   }
   public static void main (String [] args){
   
   }
}
