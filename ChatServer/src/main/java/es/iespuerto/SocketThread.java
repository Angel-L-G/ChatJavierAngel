package es.iespuerto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketThread extends Thread {

    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;
    Server server;
    private ChatRoom room;
    private final BlockingQueue<String> cola;
    private User u;

    public SocketThread (DataInputStream dis, DataOutputStream dos, Server s) {
        recibirDatos = dis;
        enviarDatos = dos;
        u = new User();
        this.server = s;
        this.cola = new LinkedBlockingQueue<>();
    }

    public User getUser() {
        return u;
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
        u.setIdChat(chat);

        System.out.println(Colors.ANSI_CYAN+u.getNombre()+" "+u.getIdChat()+Colors.ANSI_RESET);
    }

    @Override
    public void run() {
        writeUTF("¡Bienvenido a tu Chat!");

        writeUTF("Selecciona una opción:");
        String opcion = "";
        writeUTF("Escriba 'entrar' para entrar, si quiere salir escriba 'salir'");
        opcion = readUTF();

        if(opcion.equals("entrar")) {
        	setUser();

            server.joinChatRoom(u.getIdChat(), enviarDatos);
            this.room = server.getChatRoom(u.getIdChat());

            writeUTF("Escriba el Mensaje: ");

            Thread readThread = new Thread(() -> {
                while (true) {
                    try {
                        String mensaje = recibirDatos.readUTF();
                        cola.add(mensaje);
                        if (mensaje.equals("salir")){
                            break;
                        }
                        //room.broadcastMessage(u.getNombre() + ": " + mensaje, enviarDatos);
                        Thread.sleep(2000);
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            readThread.start();

            // Hilo para la escritura
            Thread writeThread = new Thread(() -> {
                while (true) {
                    for (int i = 0; i < cola.size(); i++) {
                        String mensaje = cola.poll();

                        if(mensaje != null){
                            System.out.println(mensaje);
                            if (mensaje.equals("salir")){
                                break;
                            }
                            room.broadcastMessage(u.getNombre() + ": " + mensaje, enviarDatos);
                        }
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            writeThread.start();

        }
    }
}
