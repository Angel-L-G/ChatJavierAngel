package es.iespuerto;

public class User {
    private String idChat;
    private String nombre;

    public User(){}

    public User(String idChat, String nombre) {
        this.idChat = idChat;
        this.nombre = nombre;
    }

    public String getIdChat() {
        return idChat;
    }
    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
