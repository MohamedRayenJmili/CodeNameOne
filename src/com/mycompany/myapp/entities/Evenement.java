/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author 
 */
public class Evenement {
    private int id, NbMax;
    private String lieuEV,ImageFile,titreEv,DescEv;
    private Date Datedebut,Datefin;
    private int type,reservations;

    public Evenement(int id, int NbMax, String lieuEV, String titreEv, String DescEv, Date Datedebut, Date Datefin, String ImageFile) {
        this.id = id;
        this.NbMax = NbMax;
        this.lieuEV = lieuEV;
        
        this.titreEv = titreEv;
        this.DescEv = DescEv;
        this.Datedebut = Datedebut;
        this.Datefin = Datefin;
        this.ImageFile = ImageFile;
    }
    public Evenement( int NbMax, String lieuEV, String titreEv, String DescEv, Date Datedebut, Date Datefin, String ImageFile) {
        //this.id = id;
        this.NbMax = NbMax;
        this.lieuEV = lieuEV;
        
        this.titreEv = titreEv;
        this.DescEv = DescEv;
        this.Datedebut = Datedebut;
        this.Datefin = Datefin;
        this.ImageFile = ImageFile;
    }

    public Evenement() {
    }

    public Evenement(int NbMax, String lieuEV, String ImageFile, String titreEv, String DescEv, Date Datedebut, Date Datefin) {
        this.NbMax = NbMax;
        this.lieuEV = lieuEV;
        this.ImageFile = ImageFile;
        this.titreEv = titreEv;
        this.DescEv = DescEv;
        this.Datedebut = Datedebut;
        this.Datefin = Datefin;
    }

    public Evenement(int NbMax, String lieuEV, String ImageFile, String titreEv, String DescEv) {
        this.NbMax = NbMax;
        this.lieuEV = lieuEV;
        this.ImageFile = ImageFile;
        this.titreEv = titreEv;
        this.DescEv = DescEv;
    }

    public Evenement(int NbMax, String lieuEV, String titreEv, String DescEv) {
        this.NbMax = NbMax;
        this.lieuEV = lieuEV;
        this.titreEv = titreEv;
        this.DescEv = DescEv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbMax() {
        return NbMax;
    }

    public void setNbMax(int NbMax) {
        this.NbMax = NbMax;
    }

    public String getLieuEV() {
        return lieuEV;
    }

    public void setLieuEV(String lieuEV) {
        this.lieuEV = lieuEV;
    }

    public String getImageFile() {
        return ImageFile;
    }

    public void setImageFile(String ImageFile) {
        this.ImageFile = ImageFile;
    }

    public String getTitreEv() {
        return titreEv;
    }

    public void setTitreEv(String titreEv) {
        this.titreEv = titreEv;
    }

    public String getDescEv() {
        return DescEv;
    }

    public void setDescEv(String DescEv) {
        this.DescEv = DescEv;
    }

    public Date getDatedebut() {
        return Datedebut;
    }

    public void setDatedebut(Date Datedebut) {
        this.Datedebut = Datedebut;
    }

    public Date getDatefin() {
        return Datefin;
    }

    public void setDatefin(Date Datefin) {
        this.Datefin = Datefin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", NbMax=" + NbMax + ", lieuEV=" + lieuEV + ", ImageFile=" + ImageFile + ", titreEv=" + titreEv + ", DescEv=" + DescEv + ", Datedebut=" + Datedebut + ", Datefin=" + Datefin + ", type=" + type + ", reservations=" + reservations + '}';
    }

   

   

    
    
}
