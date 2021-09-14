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
public class User {
    
    User(String login, String haslo)
    {
        setLogin(login);
        setHaslo(haslo);
    }
    
     User(String login, String haslo, boolean flaga)
    {
        setLogin(login);
        setHaslo(haslo);
        setFlaga(flaga);
    }
    
    private String login; 
    private String haslo;
    private boolean flaga = false;
    
    public String getLogin()
    {
        return login;
    }
    
    public String getHaslo()
    {
        return haslo;
    }
    
     public boolean getFlaga()
    {
        return flaga;
    }
    
    public void setLogin (String login)
    {
        this.login = login;
    }
    
    public void setHaslo (String haslo)
    {
        this.haslo = haslo;
    }
   
    public void setFlaga (boolean flaga)
    {
        this.flaga = flaga;
    }
}
