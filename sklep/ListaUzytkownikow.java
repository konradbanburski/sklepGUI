/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Mac
 */
public class ListaUzytkownikow {
   
   public List<User> listaUzytkownikow = new ArrayList<User>();
    
   User user1 = new User("admin", "admin", true);
   User user2 = new User("user", "user");
  
   
   ListaUzytkownikow()
   {
       listaUzytkownikow.add(user1);
       listaUzytkownikow.add(user2);
   }
   
   public void addUser(String login, String haslo)
   {
       User user = new User(login, haslo);
       listaUzytkownikow.add(user);
   }
   
   public void addUser(String login, String haslo, boolean flaga)
   {
       User user = new User(login, haslo, flaga);
       listaUzytkownikow.add(user);
   }
   
   public boolean szukaj(String login, String haslo)
   {
       boolean flaga = false;
       for( int i = 0; i < listaUzytkownikow.size(); i++)
       {
           if(login.equals(listaUzytkownikow.get(i).getLogin()) &&haslo.equals(listaUzytkownikow.get(i).getHaslo()))
           {
               flaga = listaUzytkownikow.get(i).getFlaga();
           }
       }
       return flaga;
   }
   
}
