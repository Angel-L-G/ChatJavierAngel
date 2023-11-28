package es.iespuerto;

public class User {
    private int idChat;
    private String nombre;

    public User(){}

    public User(int idChat, String nombre) {
        this.idChat = idChat;
        this.nombre = nombre;
    }

    public int getIdChat() {
        return idChat;
    }
    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
