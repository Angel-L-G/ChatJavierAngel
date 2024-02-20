/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package es.iespuertodelacruz.jcg.gestorcuentasfx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author Javier Marcelo Cedrés González   javmarcego@gmail.com
 */
public class
PrimaryController implements Initializable {
    
    @FXML
    private Button btnSubirFichero;
    
    Stage stage;
    
    FTPClient client;
    @FXML
    private Button btnDescargarFichero;
    @FXML
    private TextField txtDescargarFichero;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        client = new FTPClient();
        
        try {
            String servidorFTP = "localhost";
            String user = "dam2";
            String password = "J4V4";
            client.enterLocalPassiveMode();

            System.out.println("Nos conectamos a: " + servidorFTP);
            
            client.connect(servidorFTP);
            client.login(user, password);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void btnSubirFichero(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) btnSubirFichero.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(selectedFile.getAbsolutePath());
                boolean uploadResult = client.storeFile(selectedFile.getName(), fileInputStream);
                fileInputStream.close();
                System.out.println("Subida al Servidor: " + uploadResult);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void btnDescargarFichero(ActionEvent event) {
        String localDownloadFilePath = "/home/dam2/Descargas/" + txtDescargarFichero.getText();
        
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(localDownloadFilePath);
            boolean downloadResult = client.retrieveFile(txtDescargarFichero.getText(), fileOutputStream);
            fileOutputStream.close();
            System.out.println("Descarga del Servidor al Ordenador: " + downloadResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
