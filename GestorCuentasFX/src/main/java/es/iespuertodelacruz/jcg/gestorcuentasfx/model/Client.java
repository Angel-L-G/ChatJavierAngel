/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.jcg.gestorcuentasfx.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author dam2
 */
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
        System.out.println("Iniciando Socket en el HOST: " + HOST + " y PORT: " + PORT);
        socket = new Socket(HOST, PORT);
        // Inicializamos nuestro socket indicandole el puerto y el host al que conectarse.
    }

    public void connect(String nick, String idChat) throws IOException {
        System.out.println("Cliente conectado al Servidor en el HOST: " + HOST + " y PORT: " + PORT);

        recibirDatos = new DataInputStream(socket.getInputStream());
        enviarDatos = new DataOutputStream(socket.getOutputStream());

        readUTF();
        readUTF();
        readUTF();

        writeUTF("entrar");

        readUTF();
        writeUTF(nick);

        readUTF();
        writeUTF(idChat);

        System.out.println("¡Adiós!");
    }

    public String readUTF() {
        String texto = "";
        try {
            texto = recibirDatos.readUTF();
            System.out.println(Colors.ANSI_RED + texto + Colors.ANSI_RESET);
        } catch (IOException ex) {
            System.err.println("Error al leer datos del cliente");
        }
        return texto;
    }

    public void writeUTF(String text) {
        try {
            enviarDatos.writeUTF(text);
        } catch (IOException ex) {
            System.err.println("Error al enviar datos al cliente");
        }
    }

    public int writeINT() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        try {
            enviarDatos.writeInt(num);
        } catch (IOException ex) {
            System.err.println("Error al enviar datos al servidor.");
        }
        return num;

    }

    
    public String recibirMensaje() {
        return readUTF();
    }
}
