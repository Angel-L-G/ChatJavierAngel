/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package es.iespuertodelacruz.jcg.gestorcuentasfx.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Javier Marcelo Cedrés González   javmarcego@gmail.com
 */
public class
PrimaryController implements Initializable {
    
    @FXML
    private Button btnSubirFichero;
    
    Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String javier = "172.26.5.0";
        String host = "localhost";
        int port = 60000;
    }

    @FXML
    private void btnSubirFichero(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) btnSubirFichero.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }
}
