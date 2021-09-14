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
public class Produkt{
        
    private String nazwa;
    private double cena;
    private double ilosc;
    private double iloscMin;
    
    Produkt()
    {
        
    }
    
    
    Produkt(String nazwa, double cena, double ilosc, double iloscMin) 
    {
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilosc = ilosc;
        this.iloscMin = iloscMin;
    }
    
    public String getNazwa()
    {
        return nazwa;
    }
    
    public double getCena()
    {
        return cena;
    }
    
    public double getIlosc()
    {
        return ilosc;
    }
    
    public double getIloscMin()
    {
        return iloscMin;
    }
    
    public void setNazwa(String nazwa)
    {
        this.nazwa = nazwa;
    }

    public void setCena(double cena)
    {
        this.cena = cena;
    }
    
    
    public void setIlosc(double ilosc)
    {
        this.ilosc = ilosc;
    }
    
    public void setIloscMin(double iloscMin)
    {
        this.iloscMin = iloscMin;
    }

    @Override
    public String toString() {
        return nazwa;
    }

    

    
}



 