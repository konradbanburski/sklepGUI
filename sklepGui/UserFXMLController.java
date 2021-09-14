/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklepGui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sklep.Koszyk;
import sklep.Produkt;

/**
 * FXML Controller class
 *
 * @author Mac
 */
public class UserFXMLController implements Initializable {
    
    
    public Koszyk koszyk = new Koszyk();
    int indexListaProduktow; 
    int indexKoszyk;   
    int ilosc;
   
    @FXML private ListView listProdukty;
  
    @FXML private TextField textFieldNazwa;
    @FXML private TextField textFieldCena;
    @FXML private TextField textFieldIloscDostepna;
    @FXML private TextField textFieldIlosc;
    @FXML private Label labelMsg; 
  
    
    
    
    @FXML ListProperty<Produkt> listProperty = new SimpleListProperty<>();
 
    
    
   
    
    
   
    public void  actionDodaj(ActionEvent event) throws IOException {
             
        String nothing = "";
       if(textFieldIlosc.getText().equals(nothing) || textFieldIlosc.getText().equals("0"))
       {
        textFieldIlosc.setStyle("-fx-border-color: Red");  
        labelMsg.setText("Podaj ilość produktu.");
       }
       else
       {
           ilosc = Integer.valueOf(textFieldIlosc.getText()); 
        
           String nazwa = koszyk.listaProduktow.listaProduktow.get(indexListaProduktow).getNazwa();
       
           if(ilosc<=koszyk.listaProduktow.listaProduktow.get(indexListaProduktow).getIlosc())
           {
               
                if(koszyk.wyszukajProduktWKoszyku(nazwa)!= -1)
                {
                
                koszyk.dodajIlosc(koszyk.wyszukajProduktWKoszyku(nazwa), ilosc);
                textFieldIlosc.setStyle("-fx-border-color: grey");                           
                textFieldIloscDostepna.setText(String.valueOf(koszyk.listaProduktow.listaProduktow.get(indexListaProduktow).getIlosc() + " kg"));
                textFieldIlosc.clear();  
                labelMsg.setText("Produkt znajdował się już w koszyku, dodano nową ilość do poprzedniej.");         
                }
                else
                {
                koszyk.dodajDoKoszyka(indexListaProduktow, ilosc);
                textFieldIlosc.setStyle("-fx-border-color: LightGrey");                           
                textFieldIloscDostepna.setText(String.valueOf(koszyk.listaProduktow.listaProduktow.get(indexListaProduktow).getIlosc() + " kg"));
                textFieldIlosc.clear();  
                labelMsg.setText("Produkt dodano do koszyka.");
                
                }
           }
        else
           {
               textFieldIlosc.setStyle("-fx-border-color: Red");
               labelMsg.setText("Podano zbyt dużą ilość.");
           }
        
        }   
    }
   
    
    public void  actionWyloguj(ActionEvent event) throws IOException {
               
        Parent userScene= FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene userS = new Scene(userScene);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(userS);
        window.show();
           
        }
    
    
    public void  actionPrzejdzDoKoszyka(ActionEvent event) throws IOException {
        
        koszyk.save(koszyk.koszyk);
        koszyk.listaProduktow.save(koszyk.listaProduktow.listaProduktow);
        Parent userScene= FXMLLoader.load(getClass().getResource("KoszykFXML.fxml"));
        Scene userS = new Scene(userScene);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(userS);
        window.show();
        }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    listProperty.set(FXCollections.observableArrayList(koszyk.listaProduktow.listaProduktow));
    listProdukty.setItems(listProperty);
    koszyk.read();
    textFieldIlosc.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d{0,10}?")){
                textFieldIlosc.setText(oldValue);
            }
        }
    });
    
    listProdukty.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produkt>() {
          public void changed(ObservableValue<? extends Produkt> observable,
            Produkt oldValue, Produkt newValue) {
            indexListaProduktow = listProdukty.getSelectionModel().getSelectedIndex();
            textFieldNazwa.setText(String.valueOf(koszyk.listaProduktow.listaProduktow.get(indexListaProduktow).getNazwa()));
            textFieldCena.setText(String.valueOf(koszyk.listaProduktow.listaProduktow.get(indexListaProduktow).getCena()) +" zł");
            textFieldIloscDostepna.setText(String.valueOf(koszyk.listaProduktow.listaProduktow.get(indexListaProduktow).getIlosc()) +" kg");
            textFieldIlosc.clear();
          }
        });
 
   
           
   
        
    
    
    
        }   
}

    
    
    
    
    
    

