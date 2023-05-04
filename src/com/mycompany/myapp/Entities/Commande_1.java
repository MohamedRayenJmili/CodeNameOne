/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Entities;

import java.util.Date;

/**
 *
 * @author Plop
 */
public class Commande_1 {

    private int id;
    private User user;
    private String destination;
    private float prix;
    private String etat;
    private Date date;
    private String Payment;
    public Commande_1() {
    }

    public Commande_1(int id) {
        this.id = id;
    }

    public Commande_1(User user, String destination, float prix, String etat) {
        this.user = user;
        this.destination = destination;
        this.prix = prix;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", user=" + user + ", destination=" + destination + ", prix=" + prix + ", etat=" + etat + ", date=" + date + '}';
    }
    
    public Commande_1(User user, String destination, float prix, String etat, Date date) {
        this.user = user;
        this.destination = destination;
        this.prix = prix;
        this.etat = etat;
        this.date = date;
    }

    public Commande_1(int id, User user, String destination, float prix, String etat, Date date) {
        this.id = id;
        this.user = user;
        this.destination = destination;
        this.prix = prix;
        this.etat = etat;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User id_user) {
        this.user = id_user;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String Payment) {
        this.Payment = Payment;
    }
    

    
}
