
package sklepGui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sklep.Koszyk;
import sklep.Produkt;


public class KoszykFXMLController implements Initializable {

    @FXML private ListView listViewKoszyk;
    @FXML private TextField textFieldWartoscKoszyka;
    @FXML private TextField textFieldNazwa;
    @FXML private TextField textFieldCena;
    @FXML private TextField textFieldIlosc;
    @FXML private Button buttonUsunWybranyProdukt;
    @FXML private Button buttonUsunWszystko;
    @FXML private Button buttonKupuj;
    @FXML private Button buttonWyloguj;
    @FXML ListProperty<Produkt> listProperty = new SimpleListProperty<>();
    
    int indexListaKoszyk;
    Koszyk koszyk = new Koszyk();
    
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
    public void  actionKupuj(ActionEvent event) throws IOException {
        koszyk.saveLog(koszyk.koszyk);
        koszyk.koszyk.clear();
        koszyk.save(koszyk.koszyk);
        Parent userScene= FXMLLoader.load(getClass().getResource("KupujFXML.fxml"));
        Scene userS = new Scene(userScene);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(userS);
        window.show();
    } 
    
    @FXML
    public void  actionUsunWszystko(ActionEvent event) throws IOException {
        int i = 0;
        while(!koszyk.koszyk.isEmpty())
        {              
            koszyk.usunZKoszyka(i);    
            listProperty.set(FXCollections.observableArrayList(koszyk.koszyk));
            listViewKoszyk.setItems(listProperty);
            textFieldNazwa.clear();
            textFieldCena.clear();
            textFieldIlosc.clear();
            textFieldWartoscKoszyka.setText(String.valueOf(koszyk.wartoscKoszyka()));
            listViewKoszyk.getSelectionModel().clearSelection();            
        }
        koszyk.save(koszyk.koszyk);
            
    }
    
    @FXML
    public void  actionWyloguj(ActionEvent event) throws IOException {
        
       
        int i = 0;
        while(!koszyk.koszyk.isEmpty())
        {              
            koszyk.usunZKoszyka(i);    
            listProperty.set(FXCollections.observableArrayList(koszyk.koszyk));
            listViewKoszyk.setItems(listProperty);
            textFieldNazwa.clear();
            textFieldCena.clear();
            textFieldIlosc.clear();
            textFieldWartoscKoszyka.setText(String.valueOf(koszyk.wartoscKoszyka()));
            listViewKoszyk.getSelectionModel().clearSelection();            
        }
        
        koszyk.save(koszyk.koszyk);
        koszyk.listaProduktow.save(koszyk.listaProduktow.listaProduktow);
        
        
        Parent userScene= FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene userS = new Scene(userScene);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(userS);
        window.show();
           
        }
    
    @FXML
    public void actionUsunWybranyProdukt(ActionEvent event) throws IOException {
        koszyk.usunZKoszyka(indexListaKoszyk);
        listProperty.set(FXCollections.observableArrayList(koszyk.koszyk));
        listViewKoszyk.setItems(listProperty);
        textFieldNazwa.clear();
        textFieldCena.clear();
        textFieldIlosc.clear();
        textFieldWartoscKoszyka.setText(String.valueOf(koszyk.wartoscKoszyka()));
        listViewKoszyk.getSelectionModel().clearSelection();
        koszyk.save(koszyk.koszyk);
        
      
    }
    
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    koszyk.read();
    
    listProperty.set(FXCollections.observableArrayList(koszyk.koszyk));
    listViewKoszyk.setItems(listProperty);
    textFieldWartoscKoszyka.setText(String.valueOf(koszyk.wartoscKoszyka()));
    listViewKoszyk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produkt>() {
          public void changed(ObservableValue<? extends Produkt> observable,
            Produkt oldValue, Produkt newValue) {
            indexListaKoszyk = listViewKoszyk.getSelectionModel().getSelectedIndex();
            if(indexListaKoszyk>-1)
            {
            textFieldNazwa.setText(String.valueOf(koszyk.koszyk.get(indexListaKoszyk).getNazwa()));
            textFieldCena.setText(String.valueOf(koszyk.koszyk.get(indexListaKoszyk).getCena()) +" zł");
            textFieldIlosc.setText(String.valueOf(koszyk.koszyk.get(indexListaKoszyk).getIlosc() + " kg"));
            }
           }
        });   
    }
}
    

