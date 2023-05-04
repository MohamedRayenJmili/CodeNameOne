/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Entities;

import java.util.Date;

/**
 *
 * @author medio
 */
public class Reservation_entite {
    private int id_reservation;
    private User user; 
    private Evenement_entite ev ; 
    private Date date;
    private int nbr_place;
    private String imgQr;

    public Reservation_entite() {
        
    }

    public Reservation_entite(int id_reservation) {
        this.id_reservation = id_reservation;
    }
    


    public Reservation_entite(int id_reservation, int nbr_place) {
        this.id_reservation = id_reservation;
        this.nbr_place = nbr_place;
    }

   /* public Reservation_entite(int user, int ev, Date date, int nbr_place, String imgQr) {
        this.user = user;
        this.ev = ev;
        this.date = date;
        this.nbr_place = nbr_place;
        this.imgQr = imgQr;
    }
*/
    public String getImgQr() {
        return imgQr;
    }

    public void setImgQr(String imgQr) {
        this.imgQr = imgQr;
    }
    
    

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Evenement_entite getEv() {
        return ev;
    }

    public void setEv(Evenement_entite ev) {
        this.ev = ev;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    @Override
    public String toString() {
        return "Reservation_entite{" + "id_reservation=" + id_reservation + ", user=" + user + ", ev=" + ev + ", date=" + date + ", nbr_place=" + nbr_place + '}';
    }

    public Reservation_entite(int id_reservation, User user, Evenement_entite ev, Date date, int nbr_place, String imgQr) {
        this.id_reservation = id_reservation;
        this.user = user;
        this.ev = ev;
        this.date = date;
        this.nbr_place = nbr_place;
        this.imgQr = imgQr;
    }

    public Reservation_entite(User user, Evenement_entite ev, Date date, int nbr_place) {
        this.user = user;
        this.ev = ev;
        this.date = date;
        this.nbr_place = nbr_place;
    }

    public Reservation_entite(int id_reservation, User user, Evenement_entite ev, Date date) {
        this.id_reservation = id_reservation;
        this.user = user;
        this.ev = ev;
        this.date = date;
    }

    public Reservation_entite(int id_reservation, User user, Evenement_entite ev, Date date, int nbr_place) {
        this.id_reservation = id_reservation;
        this.user = user;
        this.ev = ev;
        this.date = date;
        this.nbr_place = nbr_place;
    }

    

    
    
    
    
    
}
   

    
    

    