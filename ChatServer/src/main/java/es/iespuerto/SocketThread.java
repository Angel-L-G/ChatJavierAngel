package es.iespuerto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SocketThread extends Thread {

    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;

    private User u;

    public SocketThread (DataInputStream dis, DataOutputStream dos) {
        recibirDatos = dis;
        enviarDatos = dos;
        u = new User();
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

    private int readINT() {
        int num = 0;
        try {
            num = recibirDatos.readInt();
            System.out.println(Colors.ANSI_RED+num+Colors.ANSI_RESET);
        } catch (IOException ex) {
            System.err.println("Error al leer datos del cliente");
        }
        return num;
    }

    private void chat(){
        writeUTF("Escriba el Mensaje: ");
        String mensaje = readUTF();

        writeUTF(mensaje);
    }

    private void setUser(){
        writeUTF("Escriba su nombre de User: ");
        String nick = readUTF();
        u.setNombre(nick);

        writeUTF("Escriba su nombre de User: ");
        int chat = readINT();
        u.setIdChat(chat);

        System.out.println(Colors.ANSI_CYAN+u.getNombre()+" "+u.getIdChat()+Colors.ANSI_RESET);
    }

    @Override
    public void run() {
        writeUTF("¡Bienvenido a tu Chat!");

        //setUser();

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
