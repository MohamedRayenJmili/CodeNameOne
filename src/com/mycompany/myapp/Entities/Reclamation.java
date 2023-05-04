package com.mycompany.myapp.Entities;

import java.util.Date;


public class Reclamation {
private int IdReclamation;
private User IdAdmin;
private User IdUser;
private Produit IdProduit;
private Commande IdCommande;
private String Contenu;
private Date Date;
private String Etat;
private TypeReclamation Type;
private String imageString;

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public Reclamation(int IdReclamation, User IdAdmin, User IdUser, Produit IdProduit, Commande IdCommande, String Contenu, Date Date, String Etat, TypeReclamation Type) {
        this.IdReclamation = IdReclamation;
        this.IdAdmin = IdAdmin;
        this.IdUser = IdUser;
        this.IdProduit = IdProduit;
        this.IdCommande = IdCommande;
        this.Contenu = Contenu;
        this.Date = Date;
        this.Etat = Etat;
        this.Type = Type;
    }

    public Reclamation(User IdAdmin, User IdUser, Produit IdProduit, Commande IdCommande, String Contenu, Date Date, String Etat, TypeReclamation Type) {
        this.IdAdmin = IdAdmin;
        this.IdUser = IdUser;
        this.IdProduit = IdProduit;
        this.IdCommande = IdCommande;
        this.Contenu = Contenu;
        this.Date = Date;
        this.Etat = Etat;
        this.Type = Type;
    }

    public Reclamation() {
    }

    public Reclamation(int IdReclamation, User IdUser, Produit IdProduit, Commande IdCommande, String Contenu, String Etat, TypeReclamation Type) {
        this.IdReclamation = IdReclamation;
        this.IdUser = IdUser;
        this.IdProduit = IdProduit;
        this.IdCommande = IdCommande;
        this.Contenu = Contenu;
        this.Etat = Etat;
        this.Type = Type;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "IdReclamation=" + IdReclamation + ", IdAdmin=" + IdAdmin + ", IdUser=" + IdUser + ", IdProduit=" + IdProduit + ", IdCommande=" + IdCommande + ", Contenu=" + Contenu + ", Date=" + Date + ", Etat=" + Etat + ", Type=" + Type + ", imageString=" + imageString + '}';
    }

    public Reclamation(int IdReclamation, User IdUser, Produit IdProduit, Commande IdCommande, String Contenu, Date Date, String Etat, TypeReclamation Type) {
        this.IdReclamation = IdReclamation;
        this.IdUser = IdUser;
        this.IdProduit = IdProduit;
        this.IdCommande = IdCommande;
        this.Contenu = Contenu;
        this.Date = Date;
        this.Etat = Etat;
        this.Type = Type;
    }

    public int getIdReclamation() {
        return IdReclamation;
    }

    public void setIdReclamation(int IdReclamation) {
        this.IdReclamation = IdReclamation;
    }

    public User getIdAdmin() {
        return IdAdmin;
    }

    public void setIdAdmin(User IdAdmin) {
        this.IdAdmin = IdAdmin;
    }

    public User getIdUser() {
        return IdUser;
    }

    public void setIdUser(User IdUser) {
        this.IdUser = IdUser;
    }

    public Produit getIdProduit() {
        return IdProduit;
    }

    public void setIdProduit(Produit IdProduit) {
        this.IdProduit = IdProduit;
    }

    public Commande getIdCommande() {
        return IdCommande;
    }

    public void setIdCommande(Commande IdCommande) {
        this.IdCommande = IdCommande;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public TypeReclamation getType() {
        return Type;
    }

    public void setType(TypeReclamation Type) {
        this.Type = Type;
    }


}
