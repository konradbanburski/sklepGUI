
package sklepGui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sklep.*;


public class AdminFXMLController implements Initializable {

    FileIO fileIO = new FileIO();
    ListaProduktow listaProduktow = new ListaProduktow();

@FXML private MenuBar myMenuBar;
    
@FXML private Pane paneLog;
@FXML private TextArea textAreaLog;

@FXML private Pane paneInfo;
@FXML private ListView listViewProdukty;
@FXML private TextField textFieldNazwa;
@FXML private TextField textFieldCena;
@FXML private TextField textFieldIlosc;
@FXML private TextField textFieldIloscMin;
@FXML private Button buttonZatwierdzZmiany;
@FXML private Button buttonUsunZaznaczony;
@FXML private Button buttonUtworzNowy;
@FXML private Label labelInfo;
     
 @FXML ListProperty<Produkt> listProperty = new SimpleListProperty<>();
 int index;






//////////////////////////

public void actionWyswietlLog(ActionEvent event) throws IOException
{       
    if(paneLog.isVisible())
    {
       paneLog.setVisible(false);    
    }
    else
    {
    paneLog.setVisible(true);
    textAreaLog.clear();
    readLog();
    }
}
    
public void actionWyswietlInfo(ActionEvent event) throws IOException
{   
    if(paneLog.isVisible())
    {
       paneLog.setVisible(false);    
    }
}


    public void actionUsunWybranyProdukt(ActionEvent event) throws IOException {
        fileIO.saveLogChanges("Usunięto produkt " + listaProduktow.listaProduktow.get(index).getNazwa());
        listaProduktow.usunProdukt(index);
        listProperty.set(FXCollections.observableArrayList(listaProduktow.listaProduktow));
        listViewProdukty.setItems(listProperty);
        textFieldNazwa.clear();
        textFieldCena.clear();
        textFieldIlosc.clear();
        textFieldIloscMin.clear();        
        listViewProdukty.getSelectionModel().clearSelection();
        listaProduktow.save(listaProduktow.listaProduktow);
        labelInfo.setText("Wybrany produkt został usunięty");
    }


    public void actionDodajProdukt(ActionEvent event) throws IOException {
        if(listaProduktow.wyszukajProdukt(textFieldNazwa.getText())== -1)
        {
            listaProduktow.dodajProdukt(textFieldNazwa.getText(),Double.valueOf(textFieldCena.getText()), Double.valueOf(textFieldIlosc.getText()), Double.valueOf(textFieldIloscMin.getText()));
            listProperty.set(FXCollections.observableArrayList(listaProduktow.listaProduktow));
            listViewProdukty.setItems(listProperty);
            fileIO.saveLogChanges("Dodano nowy produkt: " + textFieldNazwa.getText() + " Cena: " + Double.valueOf(textFieldCena.getText()) + " Ilość: " + Double.valueOf(textFieldIlosc.getText()) + " Ilość minimalna: " + Double.valueOf(textFieldIloscMin.getText()));

            textFieldNazwa.clear();
            textFieldCena.clear();
            textFieldIlosc.clear();
            textFieldIloscMin.clear();        
            listViewProdukty.getSelectionModel().clearSelection();
            listaProduktow.save(listaProduktow.listaProduktow);
            labelInfo.setText("Dodano nowy produkt.");
        }
        else
        {
        labelInfo.setText("Produkt o takie nazwie już istnieje.");
        fileIO.saveLogChanges("Próba dodania produktu, który już istnieje.");
        }
    }

    public void actionZatwierdzZmiany(ActionEvent event) throws IOException {

            listaProduktow.zmienNazwe(textFieldNazwa.getText(), index);
            listaProduktow.zmienCene(Double.valueOf(textFieldCena.getText()), index);
            listaProduktow.zmienIlosc(Double.valueOf(textFieldIlosc.getText()), index);
            listaProduktow.zmienIloscMin(Double.valueOf(textFieldIloscMin.getText()), index);
            fileIO.saveLogChanges("Wykonano zmiany w produkcie: " + textFieldNazwa.getText() + " Cena: " + Double.valueOf(textFieldCena.getText()) + " Ilość: " + Double.valueOf(textFieldIlosc.getText()) + " Ilość minimalna: " + Double.valueOf(textFieldIloscMin.getText()));
            listProperty.set(FXCollections.observableArrayList(listaProduktow.listaProduktow));
            listViewProdukty.setItems(listProperty);
            textFieldNazwa.clear();
            textFieldCena.clear();
            textFieldIlosc.clear();
            textFieldIloscMin.clear();        
            listViewProdukty.getSelectionModel().clearSelection();
            listaProduktow.save(listaProduktow.listaProduktow);
            labelInfo.setText("Zmiany zostały wykonane.");
            
    }

    public void  actionWyloguj(ActionEvent event) throws IOException {
               
        Parent userScene= FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene userS = new Scene(userScene);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)myMenuBar).getScene().getWindow();
        
        window.setScene(userS);
        window.show();
           
        }
    
    public void actionWyczyscLog(ActionEvent event) throws IOException {
        
        fileIO.clearLog();
        textAreaLog.clear();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    listProperty.set(FXCollections.observableArrayList(listaProduktow.listaProduktow));
    listViewProdukty.setItems(listProperty);
    
    textFieldCena.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d{0,10}([\\.]\\d{0,4})?")){
                textFieldCena.setText(oldValue);
            }
        }
    });
    textFieldIlosc.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            if (!newValue.matches("\\d{0,10}([\\.]\\d{0,4})?")){
                textFieldIlosc.setText(oldValue);
            }
        }
    });
    textFieldIloscMin.textProperty().addListener(new ChangeListener<String>() { 
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
            
            
                    
            if (!newValue.matches("\\d{0,10}([\\.]\\d{0,4})?")){
                textFieldIloscMin.setText(oldValue);
            }
        }
    });


    
    listViewProdukty.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produkt>() {
          public void changed(ObservableValue<? extends Produkt> observable,
            Produkt oldValue, Produkt newValue) {
            index = listViewProdukty.getSelectionModel().getSelectedIndex();
            if(index>-1)
            {
            textFieldNazwa.setText(String.valueOf(listaProduktow.listaProduktow.get(index).getNazwa()));
            textFieldCena.setText(String.valueOf(listaProduktow.listaProduktow.get(index).getCena()));
            textFieldIlosc.setText(String.valueOf(listaProduktow.listaProduktow.get(index).getIlosc()));
            textFieldIloscMin.setText(String.valueOf(listaProduktow.listaProduktow.get(index).getIloscMin()));
            if(Double.valueOf(textFieldIloscMin.getText())>Double.valueOf(textFieldIlosc.getText()))
            {
                textFieldIlosc.setStyle("-fx-border-color: Red");
                labelInfo.setText("Ilość produktu poniżej dozwolonej minimalnej");
            }
            else
            {
                textFieldIlosc.setStyle("-fx-border-color: LightGrey"); 
                labelInfo.setText("");
            }
            }
          }
        });
     
   
    
        
    }    
    
    
    
    
    
 public void readLog()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("log.txt")));
              String text = "";
            while((text = reader.readLine()) != null)
                    {
                        textAreaLog.appendText(text);
                        textAreaLog.appendText("\n");
                    }
            reader.close();      
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

}

