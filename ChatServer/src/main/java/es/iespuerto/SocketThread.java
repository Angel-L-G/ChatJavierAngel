package es.iespuerto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SocketThread extends Thread {

    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;
    private LinkedList<SocketThread> threads = new LinkedList<SocketThread>();
    private ArrayList<SocketThread> paired = new ArrayList<SocketThread>();

    private User u;

    public SocketThread (DataInputStream dis, DataOutputStream dos, LinkedList<SocketThread> threads) {
        recibirDatos = dis;
        enviarDatos = dos;
        u = new User();
        this.threads = threads;
    }

    public User getUser() {
        return u;
    }

    public void setConnected(ArrayList<SocketThread> st) {
        paired = st;
    }

    public void paring() {
        ArrayList<SocketThread> paring = new ArrayList<SocketThread>();
        for(SocketThread thread : threads) {
            System.out.println(thread.getUser().getIdChat());
            int actual = thread.getUser().getIdChat();
            if(actual == u.getIdChat()) {
                System.out.println("Añadido");
                paring.add(thread);
            }
        }

        for(SocketThread thread : paring) {
            setConnected(paring);
        }

        paired = paring;
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

    private Integer readINT() {
        Integer num = 0;;
        try {
            num = recibirDatos.readInt();
            System.out.println(Colors.ANSI_RED+num+Colors.ANSI_RESET);
        } catch (IOException ex) {
            System.err.println("Error al leer datos del cliente");
        }
        return num;
    }

    private void setUser(){
        writeUTF("Escriba su nombre de User: ");
        String nick = readUTF();
        u.setNombre(nick);

        writeUTF("Id Chat: ");
        String chat = readUTF();
        u.setIdChat(Integer.parseInt(chat));

        System.out.println(Colors.ANSI_CYAN+u.getNombre()+" "+u.getIdChat()+Colors.ANSI_RESET);

        paring();
    }

    public void writeAllConected(String msg){
        for(SocketThread thread : paired) {
            thread.writeUTF(msg);
        }
    }

    @Override
    public void run() {
        writeUTF("¡Bienvenido a tu Chat!");

        writeUTF("Selecciona una opción:");
        String opcion = "";
        writeUTF("Escriba 'entrar' para entrar, si quiere salir escriba 'salir'");
        opcion = readUTF();

        switch (opcion) {
            case "entrar":
                setUser();
                writeUTF("Escriba el Mensaje: ");
                while (!opcion.equals("salir")) {
                    String mensaje = readUTF();
                    writeAllConected(mensaje);
                    mensaje = "";

                    opcion = "";
                }
                break;
            default:
                writeUTF("Opción incorrecta.");
            }
    }
}
