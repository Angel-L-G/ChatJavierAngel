/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.alumno.chat.model;

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
    // Socket lanza dos excepciones: UnknownHostException para cuando no sepa
    // cual es el servidor al que tiene que conectarse y el IOException cuando no
    // pueda inicializar el socket.

    // Creamos un método para manejar el envío de datos
    public void connect() throws IOException {
        System.out.println("Cliente conectado al Servidor en el HOST: " + HOST + " y PORT: " + PORT);
        // Inicializamos ambos objetos
        recibirDatos = new DataInputStream(socket.getInputStream());
        enviarDatos = new DataOutputStream(socket.getOutputStream());
        // De esta manera tendremos ambos sockets conectados para que puedan comunicarse entre si.
        // ¿Cómo lo enviamos?
        // Tanto el envío como la recepción de los datos han de ser del mismo tipo de dato
        // Si el server escribe un string, el cliente debe leer un string, si el cliente escribe un booleam, el server
        // debe leer un boolean
        //System.out.println(recibirDatos.readUTF());
        readUTF();
        readUTF();
        String opcion = "";
        while (!opcion.equals("salir")) {
            readUTF(); // Menú
            opcion = writeUTF(); // Opción
            if (!opcion.equals("salir")) {
                readUTF(); // Primer numero
                writeUTF(); // Envío num1
            }
        }
        System.out.println("¡Adiós!");
    }

    private void readUTF() {
        try {
            System.out.println(Colors.ANSI_GREEN + recibirDatos.readUTF() + Colors.ANSI_RESET);
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
