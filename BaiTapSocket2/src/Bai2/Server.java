package Bai2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
  private HashMap<String, ClientHandler> clients = new HashMap<>();
  public Server() throws Exception {
    ServerSocket serverSocket = new ServerSocket(12340);
    System.out.println("Server started");
    while (true) {
      Socket socket = serverSocket.accept();
      System.out.println("Client connected");
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String username = in.readLine();
        ClientHandler clientHandler = new ClientHandler(username,in, out, clients);
      }
      catch (Exception e) {
        socket.close();
        e.printStackTrace();
      }
    }
  }
  public static void main(String[] args) throws Exception {
    new Server();
  }
}

