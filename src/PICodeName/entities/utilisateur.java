/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.entities;

/**
 *
 * @author OMEN
 */
public class utilisateur {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class Utilisateur {
    
    private int id;
    private String Username;
    private String emailadresse;
    private String images;
   
    private String password;

    public Utilisateur(String Username, String emailadresse, String images, String password) {
        this.Username = Username;
        this.emailadresse = emailadresse;
        this.images= images;
       
        this.password = password;
      
    }
    
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    


    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String Username, String emailadresse) {
        this.Username = Username;
        this.emailadresse = emailadresse;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getEmailadresse() {
        return emailadresse;
    }

    public void setEmailadresse(String emailadresse) {
        this.emailadresse = emailadresse;
    }

    public String getimages() {
        return images;
    }

    public void setimages(String images) {
        this.images =images;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", Username=" + Username + ", emailadresse=" + emailadresse + ", images" + images + '}';
    }

    public Utilisateur(int id, String Username, String emailadresse, String images) {
        this.id = id;
        this.Username = Username;
        this.emailadresse = emailadresse;
        this.images = images;
        
    }

    public Utilisateur(String username, String emailadresse, String images) {
        this.Username = Username;
        this.emailadresse = emailadresse;
        this.images = images;
    }
    
    
    
    
    
}
    
}
