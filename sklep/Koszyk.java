/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Koszyk 
{
    
    String szukajProdukt;
    double ilosc;
    
    public ArrayList<Produkt> koszyk = new ArrayList<Produkt>();
    public ListaProduktow listaProduktow = new ListaProduktow();
    
    //Wyszukuje produkt w sklepie, zwraca index produktu.
    public int wyszukajProduktWSklepie(String szukajProdukt)  
    {  
        return listaProduktow.wyszukajProdukt(szukajProdukt); 
    }
    //Wyszukuje produkt w sklepie, wyświetla informacje tekstową czy produkt jest w sklepie.
    public void wyszukajProduktWSklepieOpcja(String szukajProdukt)  
    {  
        listaProduktow.wyszukajProduktOpcja(szukajProdukt); 
    }
    
    //Wyszukuje produkt w koszyku, zwraca index produktu.
    public int wyszukajProduktWKoszyku(String szukajProdukt)
    {
       int index=-1;
       for(int i=0; i < koszyk.size(); i++)
       {
            String trim = szukajProdukt.trim();
            int nazwaP = trim.compareToIgnoreCase(koszyk.get(i).getNazwa());
            if(nazwaP == 0)
            {
                index = i;
            }
                       
       }
       
       return index;
          
    }
    //Wyszukuje produkt w koszyku, wyświetla informacje tekstową czy produkt znajduje w koszyku.
    public void wyszukajProduktWKoszykuOpcja (String szukajProdukt)
    {
       int index=-1;
       for(int i=0; i < koszyk.size(); i++)
       {    
            String trim = szukajProdukt.trim();
            int nazwaP = trim.compareToIgnoreCase(koszyk.get(i).getNazwa());
            if(nazwaP == 0)
            {
                index = i;
            }
           
            
       }
       
      if(index == -1)
       {
           System.out.println("Produkt nie znajduje się w koszyku");
       }
       else
       {
           System.out.println("Produkt znajduje się w koszyku");
       }
          
    }
    
    //Wyświetla zawartość koszyka.
    public void wyswietlKoszyk()
    {
        for(int i=0; i < koszyk.size(); i++)
       {
           System.out.println(koszyk.get(i).getNazwa() + " " + "Cena" + " " + koszyk.get(i).getCena() + " " + "Ilość" + " " + koszyk.get(i).getIlosc());           
       }
    }
    //Wyświetla liste produktów w sklepie.
    public void wyswietlListaProduktow()
    {
        
        listaProduktow.wyswietlProdukty();
  
    }
    //Dodaje produkt do koszyka.
    public void dodajDoKoszyka(int index, int ilosc)
    {   
        Produkt produkt = new Produkt(listaProduktow.listaProduktow.get(index).getNazwa(), listaProduktow.listaProduktow.get(index).getCena(), listaProduktow.listaProduktow.get(index).getIlosc(), listaProduktow.listaProduktow.get(index).getIloscMin());
        koszyk.add(produkt);
        koszyk.get(koszyk.size()-1).setIlosc(ilosc);
        double iloscOdejm = listaProduktow.listaProduktow.get(index).getIlosc()-ilosc;
        listaProduktow.listaProduktow.get(index).setIlosc(iloscOdejm);
    }
    //Usuwa produkt z koszyka
    public void usunZKoszyka(int index)   
    {   
     
        double iloscDodaj = listaProduktow.listaProduktow.get(listaProduktow.wyszukajProdukt(koszyk.get(index).getNazwa())).getIlosc() + koszyk.get(index).getIlosc();
      
        listaProduktow.listaProduktow.get(listaProduktow.wyszukajProdukt(koszyk.get(index).getNazwa())).setIlosc(iloscDodaj);
  
        koszyk.remove(index);
    
        
    }
    //Kupuj, czyści zawartość koszyka.
    public void kupuj()
    {    
        
        koszyk.clear();
    }
    //Zlicza wartość koszyka i ją zwraca.
    public double wartoscKoszyka()
    {
        double wartosc = 0;
        for(int i = 0;i < koszyk.size(); i++)
        {
           wartosc += koszyk.get(i).getCena()*koszyk.get(i).getIlosc();
        }
        return wartosc;
    }
    
    //Zmienia ilość produktu koszyku, odejmując ilość produktu w sklepie. 
    public void zmienIlosc(int index, double ilosc)
    {
        listaProduktow.listaProduktow.get(listaProduktow.wyszukajProdukt(koszyk.get(index).getNazwa())).setIlosc(listaProduktow.listaProduktow.get(listaProduktow.wyszukajProdukt(koszyk.get(index).getNazwa())).getIlosc()+ koszyk.get(index).getIlosc());
        koszyk.get(index).setIlosc(ilosc);
        double iloscOdejm = listaProduktow.listaProduktow.get(listaProduktow.wyszukajProdukt(koszyk.get(index).getNazwa())).getIlosc()-ilosc;
        listaProduktow.listaProduktow.get(listaProduktow.wyszukajProdukt(koszyk.get(index).getNazwa())).setIlosc(iloscOdejm);
    }
    
    public void dodajIlosc(int index, double ilosc)
    {   double odejmijWartosc = listaProduktow.listaProduktow.get(listaProduktow.wyszukajProdukt(koszyk.get(index).getNazwa())).getIlosc()-ilosc;       
        
        koszyk.get(index).setIlosc(koszyk.get(index).getIlosc()+ilosc);
        listaProduktow.listaProduktow.get(listaProduktow.wyszukajProdukt(koszyk.get(index).getNazwa())).setIlosc(odejmijWartosc);
        
        
        
        
        
    }
    
    public void save(ArrayList<Produkt> lista)
   {
        try
            {
                PrintWriter save = new PrintWriter(new FileWriter("listaKoszyk.txt"));
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
            BufferedReader reader = new BufferedReader(new FileReader("listaKoszyk.txt"));
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
                        koszyk.add(produkt);
                        }
                    }
            reader.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void saveLog(ArrayList<Produkt> lista)
   {
        try
            {
                PrintWriter save = new PrintWriter(new FileWriter("log.txt", true));
                save.println("Kupił:");
                for(int i = 0; i < lista.size(); i++)
                {   
                    
                    save.println("Nazwa: " + lista.get(i).getNazwa() + " Ilość: " + String.valueOf(lista.get(i).getIlosc()) );
                    
                    
                }                
                save.close();
            }
        
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    
    
}
    
   
       
    
    
    
    
    
