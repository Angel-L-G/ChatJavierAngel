package es.iespuerto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SocketThread extends Thread {

    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;

    private User user;

    public SocketThread (DataInputStream dis, DataOutputStream dos, User u) {
        recibirDatos = dis;
        enviarDatos = dos;
        user = u;
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

    private void chat(){
        writeUTF("Escriba el Mensaje: ");
        String mensaje = readUTF();

        writeUTF(mensaje);
    }

    public void run() {
        writeUTF("¡Bienvenido a tu Chat!");
        writeUTF("Selecciona una opción:");
        String opcion = "";
        while (!opcion.equals("salir")) {
            writeUTF("Escriba 'entrar' para entrar, si quiere salir escriba 'salir'");
            opcion = readUTF();
            switch (opcion) {
                case "salir":
                    writeUTF("Gracias por utilizar este chat.");
                    System.out.println("Cliente desconectado.");
                    break;
                case "entrar":
                    chat();
                    break;
                default:
                    writeUTF("Opción incorrecta.");
            }
        }
    }
}
