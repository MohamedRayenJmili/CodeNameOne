/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.storeship.entities;

/**
 *
 * @author Jmili
 */
public class Categorie_store {

    private int id;
    private String libelle;
 

    public Categorie_store() {
    }

    public Categorie_store(int id, String nom) {
        this.id = id;
        this.libelle = nom;
    }

    public Categorie_store(String nom) {
        this.libelle = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return libelle;
    }

    public void setNom(String nom) {
        this.libelle = nom;
    }

    public Categorie_store(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + libelle + '}';
    }  
}

