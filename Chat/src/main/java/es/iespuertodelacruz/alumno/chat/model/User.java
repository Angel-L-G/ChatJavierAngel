/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.alumno.chat.model;

/**
 *
 * @author dam2
 */
public class User {
    private int idChat;
    private String nick;

    public User(String nick, int idChat) {
        this.idChat = idChat;
        this.nick = nick;
    }
    
    public User() {
        
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
