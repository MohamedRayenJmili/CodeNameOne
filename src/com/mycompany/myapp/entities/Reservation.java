/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Salma
 */
public class Reservation {
   
    private int id,nbplaces;
private Date date;
private Evenement ev;

    public Reservation(int id, int nbplaces, Date date) {
        this.id = id;
        this.nbplaces = nbplaces;
        this.date = date;
    }
     public Reservation( int nbplaces, Date date) {
       
        this.nbplaces = nbplaces;
        this.date = date;
    }

    public Reservation() {
    }

    public Evenement getEv() {
        return ev;
    }

    public void setEv(Evenement ev) {
        this.ev = ev;
    }

   

    public Reservation(int nbplaces) {
        this.nbplaces = nbplaces;
    }

   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbplaces() {
        return nbplaces;
    }

    public void setNbplaces(int nbplaces) {
        this.nbplaces = nbplaces;
    }

 
    
    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", nbplaces=" + nbplaces + ", date=" + date + '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

   
    
    
    

}
