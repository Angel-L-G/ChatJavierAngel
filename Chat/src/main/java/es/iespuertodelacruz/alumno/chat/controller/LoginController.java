/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.iespuertodelacruz.alumno.chat.controller;

import es.iespuertodelacruz.alumno.chat.App;
import es.iespuertodelacruz.alumno.chat.model.Client;
import es.iespuertodelacruz.alumno.chat.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtNick;
    @FXML
    private Button btnEnviar;
    @FXML
    private TextField txtIdChat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = new User(txtNick.getText(), Integer.parseInt(txtIdChat.getText()));
    }    

    @FXML
    private void enviar(ActionEvent event) {
        try {
            App.setRoot("chat.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
