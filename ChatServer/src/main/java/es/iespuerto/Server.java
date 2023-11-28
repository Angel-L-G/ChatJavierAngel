package es.iespuerto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;



    private final int PORT = 60000;


    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public void waitConnections() throws IOException {
        while (true) {
            System.out.println("Esperando conexiones de clientes");
            Socket socket = serverSocket.accept();

            System.out.println("Cliente conectado correctamente");

            DataInputStream recibirDatos = new DataInputStream(socket.getInputStream());
            DataOutputStream enviarDatos = new DataOutputStream(socket.getOutputStream());
            SocketThread socketThread = new SocketThread(recibirDatos, enviarDatos);
            socketThread.start();
        }
    }
}
