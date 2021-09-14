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
public class Logowanie {
 
    String login, haslo;
    
    
   public ListaUzytkownikow listaUzytkownikow = new ListaUzytkownikow();
       
   public boolean logowanie(String login, String haslo)
    {
       
        boolean status = false;
        for(int i = 0; i < listaUzytkownikow.listaUzytkownikow.size(); i++)
        {
            if(login.equals(listaUzytkownikow.listaUzytkownikow.get(i).getLogin()))
            {
                System.out.println("Login poprawny");
                if(haslo.equals(listaUzytkownikow.listaUzytkownikow.get(i).getHaslo()))
                {
                    status = true;
                    System.out.println("Haslo poprawne");
                }
            }
           
        }
        System.out.println(status);
        return status;
    }
    
    
    
}
