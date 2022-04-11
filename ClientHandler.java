package Projekti;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
      public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
      private Socket socket;
      private BufferedReader bufferedReader;
      private BufferedWriter bufferedWriter;
      private String clientUsername;
}
