/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklepGui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sklep.Logowanie;

/**
 * FXML Controller class
 *
 * @author Mac
 */
public class KupujFXMLController implements Initializable {

    @FXML
    public void  actionPowrotDoSklepu(ActionEvent event) throws IOException {
      
 
      
        Parent userScene= FXMLLoader.load(getClass().getResource("UserFXML.fxml"));
        Scene userS = new Scene(userScene);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(userS);
        window.show();
        
    }
    
    @FXML
    public void  actionWyloguj(ActionEvent event) throws IOException {
               
        Parent userScene= FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene userS = new Scene(userScene);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(userS);
        window.show();
           
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
