/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PICodeName.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author OMEN
 */
public class SessionManager {
   
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String Username ; 
    private static String emailadresse; 
    private static String passowrd ;
    private static String images;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getUsername() {
        return pref.get("Username",Username);
    }

    public static void setUsername(String userName) {
         pref.set("Username",Username);
    }

    public static String getEmailadresse() {
        return pref.get("email",emailadresse);
    }

    public static void setEmailadresse(String emailadresse) {
         pref.set("email",emailadresse);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getImages() {
        return pref.get("images",images);
    }

    public static void setImages(String Images) {
         pref.set("Images",Images);
    }
    
    
    
    
    
    
}

