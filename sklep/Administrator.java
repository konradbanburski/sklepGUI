/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

/**
 *
 * @author Mac
 */
public class Administrator {
    
    int index;
    double ilosc, cena;
    String szukajProdukt;
    
    ListaProduktow listaProduktow = new ListaProduktow();
    
    //Dodaje nowy produkt do listy produktów w sklepie.
    void dodajProdukt(String nazwa, double cena, double ilosc, double iloscMin)
    {
        listaProduktow.dodajProdukt(nazwa, cena, ilosc, iloscMin );
    }
    //Usuwa istniejący produkt z listy produktów w sklepie.
    void usunProdukt(int index)
    {
        listaProduktow.usunProdukt(index);
    }
    //Wyszukuje produkt w sklepie, zwraca index produktu.
    int wyszukajProdukt(String szukajProdukt)
    {
        return index = listaProduktow.wyszukajProdukt(szukajProdukt);       
    }
    //Wyszukuje produkt w sklepie, informuje wiadomością tekstową czy produkt istnieje w sklepie.
    void wyszukajProduktOpcja(String szukajProdukt)
    {
        listaProduktow.wyszukajProduktOpcja(szukajProdukt);
    }
            
    //Wyświetla produkty w sklepie.
    void wyświetlProdukty()
    {
        listaProduktow.wyswietlProdukty();
    }
    //Wyświetla produkty w sklepie posiadające ilość równą lub mniejszą od ilości minimalnej.
    void wyświetlMin()
    {
        listaProduktow.wyswietlMin();
    }
    //Zmienia ilość produktu.
    void zmienIlosc(double ilosc, int index)
    {
        listaProduktow.zmienIlosc(ilosc, index);
    }
    //Zmienia cene produktu.
    void zmienCena(double cena, int index)
    {
        listaProduktow.zmienCene(cena, index);
    }
    
}
