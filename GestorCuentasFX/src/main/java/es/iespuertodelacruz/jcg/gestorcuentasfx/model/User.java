/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.jcg.gestorcuentasfx.model;

/**
 *
 * @author dam2
 */
public class User {
    private String idChat;
    private String nick;

    public User(String nick, String idChat) {
        this.idChat = idChat;
        this.nick = nick;
    }
    
    public User() {
        
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
