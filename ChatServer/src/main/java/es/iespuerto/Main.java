package es.iespuerto;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Server server = null;
        // Manejamos la excepción que lanza Server
        try {
            server = new Server();
        } catch (IOException ex) {
            System.err.println("No se ha podido inicializar el servidor");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            server.waitConnections();
        } catch (IOException ex) {
            System.err.println("No se ha podido iniciar la comunicación con el cliente");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
    }
}