/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Plop
 */
public class StaticPanier {
     public static StaticPanier instance = null;
   private static Map<Produit, Integer> panierCourant = new HashMap<>();
    public static StaticPanier getInstance() {
        if (instance == null) {
            instance = new StaticPanier();
        }
        return instance;
    }

    public static Map<Produit, Integer> getPaniercourrant() {
        return panierCourant;
    }

    // never to be used
    public static void setPaniercourrant(Map<Produit, Integer> panierCourant) {
        StaticPanier.panierCourant = panierCourant;
    }
   

public static void addItemToPanier(Produit produit) {
    Integer quantite = panierCourant.get(produit);
    if (quantite == null) {
        panierCourant.put(produit, 1);
    } else {
        panierCourant.put(produit, quantite + 1);
    }
}

public static void removeItemFromPanier(Produit produit) {
    Integer quantite = panierCourant.get(produit);
    if (quantite != null) {
        quantite--;
        if (quantite == 0) {
            panierCourant.remove(produit);
        } else {
            panierCourant.put(produit, quantite);
        }
    }
}

    
    
}
