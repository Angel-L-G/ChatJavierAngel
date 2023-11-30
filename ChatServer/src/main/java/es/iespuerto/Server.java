package es.iespuerto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class Server {
    private final ServerSocket serverSocket;
    private LinkedList<SocketThread> threads = new LinkedList<SocketThread>();
    private ArrayList<User> users = new ArrayList<User>();
    private final int PORT = 60000;
    private DataOutputStream enviarDatos = null;
    private DataInputStream recibirDatos = null;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public LinkedList<SocketThread> getThreads() {
        return threads;
    }

    private String readUTF() {
        String texto = "";
        try {
            texto = recibirDatos.readUTF();
            System.out.println(Colors.ANSI_RED+texto+Colors.ANSI_RESET);
        } catch (IOException ex) {
            System.err.println("Error al leer datos del cliente");
        }
        return texto;
    }

    private void writeUTF(String text) {
        try {
            enviarDatos.writeUTF(text);
        } catch (IOException ex) {
            System.err.println("Error al enviar datos al cliente");
        }
    }



    public void waitConnections() throws IOException {
        while (true) {
            System.out.println("Esperando conexiones de clientes");
            Socket socket = serverSocket.accept();

            System.out.println("Cliente conectado correctamente");

            this.recibirDatos = new DataInputStream(socket.getInputStream());
            this.enviarDatos = new DataOutputStream(socket.getOutputStream());

            SocketThread socketThread = new SocketThread(recibirDatos, enviarDatos);
            threads.add(socketThread);

            socketThread.start();
        }
    }
}
