package es.iespuerto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SocketThread extends Thread {

    private DataInputStream recibirDatos = null;
    private DataOutputStream enviarDatos = null;
    Server server;
    private ChatRoom room;

    private User u;

    public SocketThread (DataInputStream dis, DataOutputStream dos, Server s) {
        recibirDatos = dis;
        enviarDatos = dos;
        u = new User();
        this.server = s;
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

        switch (opcion) {
            case "entrar":
                setUser();

                server.joinChatRoom(u.getIdChat(), enviarDatos);
                this.room = server.getChatRoom(u.getIdChat());

                writeUTF("Escriba el Mensaje: ");
                while (!opcion.equals("salir")) {
                    int aux = 0;
                    try {
                        aux= recibirDatos.available();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    String mensaje;

                    if(aux > 0){
                        mensaje = readUTF();

                        room.broadcastMessage(mensaje, enviarDatos);
                    }

                    mensaje = "";
                    opcion = "";
                }
                break;
            default:
                writeUTF("Opción incorrecta.");
            }

            /*
            Thread readThread = new Thread(() -> {
                    while (true) {
                        try {
                            System.out.println("1");
                            String mensaje = recibirDatos.readUTF();
                            room.broadcastMessage(u.getNombre() + ": " + mensaje, enviarDatos);
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                });
                readThread.start();

                // Hilo para la escritura
                Thread writeThread = new Thread(() -> {
                    while (true) {
                        System.out.println("2");
                        String mensaje = readUTF();
                        room.broadcastMessage(mensaje, enviarDatos);
                    }
                });
                writeThread.start();
            */

    }
}
