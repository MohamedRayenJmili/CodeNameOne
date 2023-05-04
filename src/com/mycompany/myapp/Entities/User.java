/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Entities;

/**
 *
 * @author Admin
 */
public class User {
    
    private int id ;
    private String email;
    private String roles;
    private String password;
    private String nom;
    private String prenom;
    private int age;
    private String adresse;
    private String image;
    private String genre;
    private String phone;
    private int etat;
   

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String email, String roles, String password, String nom, String prenom, 
            int age, String adresse, String image, String genre, String phone, int etat) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.image = image;
        this.genre = genre;
        this.phone = phone;
        this.etat = etat;
    }
    
    public User(String email, String roles, String password, String nom, String prenom,
            int age, String adresse, String image, String genre, String phone, int etat) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.image = image;
        this.genre = genre;
        this.phone = phone;
        this.etat = etat;
    }
    
      public User(String email, String roles, String password, String nom, String prenom,
            int age, String adresse, String image, String genre, String phone) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.image = image;
        this.genre = genre;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", nom=" + nom 
                + ", prenom=" + prenom + ", age=" + age + ", adresse=" + adresse + ", image=" + image + ", genre=" 
                + genre + ", phone=" + phone + ", etat=" + etat + '}';
    }

    
    
    

    

    
   

    
    
    
    
    
}
