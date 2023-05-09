/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.storeship.gui;

import com.codename1.io.Preferences;
import com.storeship.entities.User;

/**
 *
 * @author Bejaoui
 */
public class SessionManager {
    
    public static Preferences pref ;
     
    
    private static int id ; 
    private static String email; 
    private static String passowrd ;
    private static String roles;
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SessionManager.user = user;
    }

    
    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        SessionManager.roles = roles;
    }

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);
    }

    public static void setId(int id) {
        pref.set("id",id);
    }


    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    
    
    
    
    
    
    
}
