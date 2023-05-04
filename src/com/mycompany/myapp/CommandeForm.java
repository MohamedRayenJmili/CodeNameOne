/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Commande;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Service.Service_Commande;
import java.util.ArrayList;

/**
 *
 * @author Jmili
 */
public class CommandeForm extends BaseForm {

    Container container=new Container(BoxLayout.y());
    public CommandeForm(Resources res) {
        super("Commande", new FlowLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
                tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        setTitle("Listes des Commandes");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {}); 
        ArrayList<Commande> Commandes=Service_Commande.getInstance().getAllCommande();
        System.out.println(" this is the list of commandands "+Commandes);
        for (Commande c:Commandes)
        {
            System.out.println("this is a asingle commandes "+c);
           // addElement(c);
           Container CommandeContainer=new Container();
           try{
            CommandeContainer=Service_Commande.getInstance().commandeinsideContainer(c);
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
