/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.storeship.Commande;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.storeship.entities.Detail_Commande;
import com.storeship.gui.BaseForm;
import com.storeship.services.Service_Commande;
import java.util.ArrayList;

/**
 *
 * @author Jmili
 */
public class StoreCommandeForm extends BaseForm {

    Container container=new Container(BoxLayout.y());
    public StoreCommandeForm(Resources res) {
        super("Store Commandes", new FlowLayout());
        Toolbar tb = new Toolbar(false);
        setToolbar(tb);
                tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        setTitle("Listes des Store Commandes");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
   
        ArrayList<Detail_Commande> Commandes=Service_Commande.getInstance().getConnectedStoresCommande();
        System.out.println(" this is the list of Detail inside code name one commandands "+Commandes);
        for (Detail_Commande c:Commandes)
        {
            System.out.println("this is a asingle Detail commandes "+c);
           // addElement(c);
           Container CommandeContainer=new Container();
           try{
            CommandeContainer=Service_Commande.getInstance().DetailcommandeinsideContainer(c);
           // Full COmmande
              
} catch (Exception e) {
    e.printStackTrace();
}
                                

                   container.add(CommandeContainer);
        }
        
//        Commande c=new Commande();
//        c.setEtat("Pending");
//        c.setDestination("Tunis Centre Ville");
//        c.setPayment("chk-zaezaezaeazezaeza");
//        c.setPrix(52.0f);
//        c.setUser(new User(6));
//        Commande commande=Service_Commande.getInstance().addCommande(c);
//        System.out.println("nEW Commade ID "+commande);
        super.add(container);
    super.show();
    }
    
    
   
    
    
}
