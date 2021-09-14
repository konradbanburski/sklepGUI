/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Mac
 */
public class ListaProduktow {
    
   
   public ArrayList<Produkt> listaProduktow = new ArrayList<Produkt>();
   /*Produkt produkt1 = new Produkt("Brokuły", 5, 100, 20);
   Produkt produkt2 = new Produkt("Marchew", 2, 100, 20);
   Produkt produkt3 = new Produkt("Ziemniaki", 2, 100, 20);
   Produkt produkt4 = new Produkt("Kalafior", 10, 2, 10);
   */
    public ListaProduktow()
    {
       read();
        
        /*
               listaProduktow.add(produkt1);
               listaProduktow.add(produkt2);
               listaProduktow.add(produkt3);
               listaProduktow.add(produkt4);
*/
    }   
    
   //Wyświetla produkty w sklepie.         
   public void wyswietlProdukty()
   {
       for(int i=0; i < listaProduktow.size(); i++)
       {
           System.out.println(listaProduktow.get(i).getNazwa() + " " + "Cena" + " " + listaProduktow.get(i).getCena() + " " + "Ilość" + " " + listaProduktow.get(i).getIlosc());
           
       }
   }
   //Dodaje nowy produkt do listy.
   public void dodajProdukt(String nazwa, double cena, double ilosc, double iloscMin)
   {
       Produkt produkt = new Produkt(nazwa, cena, ilosc, iloscMin);
       listaProduktow.add(produkt);
   }
   //Usuwa produkt z listy.
   public void usunProdukt(int i)
   {
       listaProduktow.remove(i);
   }
    
   //Wyszukuje produtk w sklepie, zwraca index produktu.
   public int wyszukajProdukt (String nazwa)
   {
       int index=-1;
      
       for(int i=0; i < listaProduktow.size(); i++)
       {
            String trim = nazwa.trim();
            int nazwaP = trim.compareToIgnoreCase(listaProduktow.get(i).getNazwa());
           
            if(nazwaP == 0)
            {
                index = i;
            }
       }
          
       return index;
              
   }
   
   //Wyszukuje produkt w sklepie, wyświetla wiadomość tekstową czy produkt istnieje w sklepie.
   public void wyszukajProduktOpcja (String nazwa)
   {
       int index = -1;
       
       for(int i=0; i < listaProduktow.size(); i++)
       {    
            String trim = nazwa.trim();
            int nazwaP = trim.compareToIgnoreCase(listaProduktow.get(i).getNazwa());
            if(nazwaP == 0)
            {
                index = i;
            }
       }
       
       if(index == -1)
       {
           System.out.println("Brak produktu");
       }
       else
       {
           System.out.println("Produkt dostępny");
       }
   }
   
   public void zmienNazwe (String nazwa, int index)
   {
       listaProduktow.get(index).setNazwa(nazwa);
   }
   
   //Zmienia ilość produktu.
   public void zmienIlosc (double ilosc, int index)
   {       
       listaProduktow.get(index).setIlosc(ilosc);       
   }
   //Zmienia cene produktu.
   public void zmienCene (double cena, int index)
   {
       listaProduktow.get(index).setCena(cena);
   }
   
   public void zmienIloscMin(double iloscMin, int index)
   {
       listaProduktow.get(index).setIloscMin(iloscMin);
   }
   //Wyświetla produkty z wartością równą lub mniejszą od minimalnej.
   
           
   
   public void wyswietlMin()
   {
       for(int i=0; i < listaProduktow.size(); i++)
       {
           if(listaProduktow.get(i).getIloscMin()>=listaProduktow.get(i).getIlosc())
           {
           System.out.println(listaProduktow.get(i).getNazwa() + " " + "Ilość" + " " + listaProduktow.get(i).getIlosc() + " " + "Ilość minimalna" + " " + listaProduktow.get(i).getIloscMin());
           }
       }
   }
   
   public void save(ArrayList<Produkt> lista)
   {
        try
            {   
                
                    PrintWriter save = new PrintWriter(new FileWriter("listaProduktow.txt"));
                    for(int i = 0; i < lista.size(); i++)
                    {   
                    
                    save.println(lista.get(i).getNazwa());
                    save.println(String.valueOf(lista.get(i).getCena()));
                    save.println(String.valueOf(lista.get(i).getIlosc()));
                    save.println(String.valueOf(lista.get(i).getIloscMin()));
                    save.println("end");
                    }
                save.close();
                
               
            }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
   
    public void read()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("listaProduktow.txt"));
            String wiersz = "";
            
            while((wiersz = reader.readLine()) != null)
                    {
                        if(wiersz.equals("end"))
                                {
                                    reader.skip(0);
                                }
                        else
                        {
                        Produkt produkt = new Produkt(wiersz, Double.parseDouble(reader.readLine()), Double.parseDouble(reader.readLine()),Double.parseDouble(reader.readLine()));
                        listaProduktow.add(produkt);
                        }
                    }
            reader.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
   
}
   
    
