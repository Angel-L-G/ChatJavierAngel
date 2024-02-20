package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private final ServerSocket serverSocket;
    private final int PORT = 60000;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public void connect() throws IOException {
        Socket socket = serverSocket.accept();

        System.out.println("Cliente conectado correctamente");


    }
}
