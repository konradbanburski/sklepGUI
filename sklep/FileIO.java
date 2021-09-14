/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Mac
 */
public class FileIO {
    
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
   
    public void saveLog(String login)
    {
                try
                {
                    PrintWriter save = new PrintWriter(new FileWriter("log.txt", true));
                    save.println("___________");
                    save.println("Zalogowany: " + login);   
                    save.close();
                }
                catch(IOException e)
                {
                    System.out.println(e.getMessage());
                }
    }
    
    public void saveLogChanges(String text)
    {
        try
                {
                    PrintWriter save = new PrintWriter(new FileWriter("log.txt", true));
                    save.println(text);                 
                    save.close();
                }
                catch(IOException e)
                {
                    System.out.println(e.getMessage());
                }
    }
    
   public void clearLog()
    {
                try
                {
                    
                    PrintWriter save = new PrintWriter(new FileWriter("log.txt", false));
                    save.println("");
                    save.close();
                }
                catch(IOException e)
                {
                    System.out.println(e.getMessage());
                }
    }
           
}
