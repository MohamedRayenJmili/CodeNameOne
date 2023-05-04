package com.mycompany.myapp.Entities;

import java.util.ArrayList;
import java.util.List;

public class ProduitStore {
    private Store St;
    private List<Produit> prod=new ArrayList<>();

    public ProduitStore() {
    }
    public ProduitStore(List<Produit> prod) {
        this.prod=prod;
    }    
    public ProduitStore(Store St) {
        this.St=St;
    }
    public ProduitStore(List<Produit> prod, Store St) {
        this.prod = prod;
        this.St=St;
    }

    @Override
    public String toString() {
        return "ProduitStore{" +
                "ceci est le store: \n" + St +
                "\n la list des produit du store sont:" + prod +
                '}';
    }

    public void setSt(Store St) {
        this.St = St;
    }

    public void setProd(List<Produit> prod) {
        this.prod=prod;
    }

    public Store getSt() {
        return St;
    }

    public List<Produit> getProd() {
        return prod;
    }
public void ajouterproduit(Produit pdt){
    this.prod.add(pdt);
}
   
}
