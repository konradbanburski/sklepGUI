/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklepGui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sklep.*;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;


/**
 *
 * @author Mac
 */
public class LoginFXMLController implements Initializable {
    
        
    @FXML
    private TextField haslo;

    @FXML
    private TextField login;
    
    private Sklep sklep;
    
    ArrayList<Produkt> list = new ArrayList();
    Koszyk koszyk = new Koszyk();
    FileIO fileIO = new FileIO();
  

    
    @FXML
    public void  logowanie(ActionEvent event) throws IOException {
        Logowanie logowanie = new Logowanie();
 
        if(logowanie.logowanie(login.getText(), haslo.getText())==true)
        { 
            if(logowanie.listaUzytkownikow.szukaj(login.getText(), haslo.getText()))
            {
                fileIO.saveLog(login.getText());
                Parent userScene= FXMLLoader.load(getClass().getResource("AdminFXML.fxml"));
                Scene userS = new Scene(userScene);
        
                //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
                window.setScene(userS);
                window.show();
                    
            }
            else
            {
                fileIO.saveLog(login.getText());
                Parent userScene= FXMLLoader.load(getClass().getResource("UserFXML.fxml"));
                Scene userS = new Scene(userScene);
        
                //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
                window.setScene(userS);
                window.show();
 
        }
    }
}
    
 
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      koszyk.save(list);
    }    
    
}
