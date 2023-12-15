package es.iespuerto;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class ChatRoom {
    private Set<DataOutputStream> clients;
    private String roomId;

    public ChatRoom() {
        clients = new HashSet<>();
    }

    public ChatRoom(String rid) {
        this.roomId = rid;
        clients = new HashSet<>();
    }

    public synchronized void addClient(DataOutputStream client) {
        clients.add(client);
    }

    public synchronized void broadcastMessage(String message, DataOutputStream sender) {
        for (DataOutputStream client : clients) {
            try {
                client.writeUTF(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
