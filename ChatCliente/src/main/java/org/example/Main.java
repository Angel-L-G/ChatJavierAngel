package org.example;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String javier = "172.26.5.0";
        String host = "localhost";
        int port = 60000;

        Client client = null;
        try{
            client = new Client(host, port);
            //client.connect();
        } catch (IOException ex){
            System.err.println("No se ha podido conectar al Servidor con HOST: "+host+" y PORT: "+port);
            System.err.println(ex.getMessage());
        }
    }
}