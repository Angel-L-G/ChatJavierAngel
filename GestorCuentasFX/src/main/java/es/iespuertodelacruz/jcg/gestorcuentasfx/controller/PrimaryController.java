/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package es.iespuertodelacruz.jcg.gestorcuentasfx.controller;

import es.iespuertodelacruz.jcg.gestorcuentasfx.App;
import es.iespuertodelacruz.jcg.gestorcuentasfx.model.Client;
import es.iespuertodelacruz.jcg.gestorcuentasfx.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Javier Marcelo Cedrés González   javmarcego@gmail.com
 */
public class
PrimaryController implements Initializable {
    
    @FXML
    private TextField txtNick;
    @FXML
    private Button btnEnviar;
    @FXML
    private TextField txtIdChat;
    @FXML
    private TextArea txaChat;
    @FXML
    private TextField txtMensaje;
    @FXML
    private Button btnEnviar2;
    
    private User user;
    
    private Client client;

    private BlockingQueue<String> cola;
    @FXML
    private TextField txtPort;
    @FXML
    private Button btnLogout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new User();
        this.cola = new LinkedBlockingQueue<>();
    }

    @FXML
    private void enviar(ActionEvent event) throws IOException {
        String javier = "172.26.5.0";
        String host = "localhost";
        int port = Integer.parseInt(txtPort.getText());
        
        client = null;
        try{
            client = new Client(host, port);
            client.connect(txtNick.getText(), txtIdChat.getText());
            
            // Iniciar el hilo aquí después de asignar el valor a client
            Thread readThread = new Thread(() -> {
                while (true) {
                    String mensaje = client.recibirMensaje();
                    cola.add(mensaje);
                    if (cola.peek() != null) {
                        System.out.println("Read: " + cola.peek());
                    }
                }
            });
            readThread.start();
            
            Thread writeThread = new Thread(() -> {
                while (true) {
                    String mensaje = cola.poll();
                    
                    if (mensaje != null) {
                        txaChat.appendText(mensaje + "\n");
                        System.out.println("Write: " + mensaje);
                    }
                    
                }
            });
            writeThread.start();
        } catch (IOException ex){
            System.err.println("No se ha podido conectar al Servidor con HOST: "+host+" y PORT: "+port);
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void enviar2(ActionEvent event) {
        client.writeUTF(txtMensaje.getText());
    }

    @FXML
    private void logout(ActionEvent event) {
        System.exit(0);
    }
}
