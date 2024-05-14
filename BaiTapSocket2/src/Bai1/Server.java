package Bai1;

import java.io.*;
import java.net.*;

public class Server {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(12345);
      System.out.println("Server is running...");

      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket);

        Thread clientHandler = new Thread(new ClientHandler(clientSocket));
        clientHandler.start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class ClientHandler implements Runnable {
  private Socket clientSocket;

  public ClientHandler(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }

  @Override
  public void run() {
    try {
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      for (int i = 1; i <= 1000; i++) {
        out.println(i);
        Thread.sleep(1000);
      }
      out.close();
      clientSocket.close();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
