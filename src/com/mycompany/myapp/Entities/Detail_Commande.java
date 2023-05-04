/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Entities;

import java.util.Objects;

/**
 *
 * @author Plop
 */
public class Detail_Commande {
    
  private int  id;
  private Commande  commande;
  private Store  store;
  private Produit  produit;
  private int  quantite;
  private float  prix_total;
  private String etat;

    public Detail_Commande(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Detail_Commande{" + "id=" + id + ", commande=" + commande + ", store=" + store + ", produit=" + produit + ", quantit\u00e9=" + quantite + ", prix_total=" + prix_total + ", etat=" + etat + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public Detail_Commande() {
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Detail_Commande(Commande commande, Store store, Produit produit, int quantite, float prix_total, String etat) {
        this.commande = commande;
        this.store = store;
        this.produit = produit;
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.etat = etat;
    }

    public Detail_Commande(int id, Commande commande, Store store, Produit produit, int quantite, float prix_total, String etat) {
        this.id = id;
        this.commande = commande;
        this.store = store;
        this.produit = produit;
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.etat = etat;
    }

  public static void convertProduitToDetail(Detail_Commande dc,Produit p,Store s,int quantite,Commande c){
      dc.setStore(s);
       dc.setProduit(p);
       dc.setCommande(c);
       dc.setQuantite(quantite);
       dc.setPrix_total((float)p.getPrix()*quantite);
       dc.setEtat("Pending");
     
  }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.produit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Detail_Commande other = (Detail_Commande) obj;
        return Objects.equals(this.produit, other.produit);
    }
    
}
