package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String HOST;
    private final int PORT;
    // Declaramos el puerto y el host al que nos queremos conectar.
    private final Socket socket;
    // Declaramos nuestro objeto de tipo socket.
    // Declaramos dos objetos. DataInputStream para recibir datos y DataOutputStream para enviarlos.
    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;

    public Client(String HOST, int PORT) throws IOException {
        this.HOST = HOST;
        this.PORT = PORT;
        System.out.println("Iniciando Socket en el HOST: "+HOST+" y PORT: "+PORT);
        socket = new Socket(HOST,PORT);
        // Inicializamos nuestro socket indicandole el puerto y el host al que conectarse.
    }

    public void connect() throws IOException{
        System.out.println("Cliente conectado al Servidor en el HOST: "+HOST+" y PORT: "+PORT);

        recibirDatos = new DataInputStream(socket.getInputStream());
        enviarDatos = new DataOutputStream(socket.getOutputStream());

        readUTF();
        readUTF();
        String opcion = "a";
        while (!opcion.equals("salir")) {
            readUTF(); // Menú
            opcion = writeUTF(); // Opción
            if (!opcion.equals("salir")) {
                readUTF(); // Primer numero
                writeUTF();
            }
        }
        System.out.println("¡Adiós!");
    }
    private void readUTF() {
        try {
            System.out.println(Colors.ANSI_GREEN+recibirDatos.readUTF()+Colors.ANSI_RESET);
        } catch (IOException ex) {
            System.err.println("Error al leer datos del servidor");
        }
    }

    private String writeUTF() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        try {
            enviarDatos.writeUTF(text);
        } catch (IOException ex) {
            System.err.println("Error al enviar datos al servidor");
        }
        return text;
    }

    private int writeINT() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        try {
            enviarDatos.writeInt(num);
        } catch (IOException ex) {
            System.err.println("Error al enviar datos al servidor.");
        }
        return num;

    }

}
