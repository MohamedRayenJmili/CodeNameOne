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
           
           // Full COmmande
                             Container CommandeContainer=new Container(BoxLayout.y());
                             // First Line in Container
                             Container idetatContainer=new Container(BoxLayout.x());
                                  idetatContainer.add(new Label("Id :"+String.valueOf(c.getId())));
           idetatContainer.add(new Label("Etat :"+c.getEtat()));
           //Second Line in Container
                             Container DatedestinationContainer= new Container(BoxLayout.x());
                         DatedestinationContainer.add(new Label("Date :"+String.valueOf(c.getDate())));
                         Container destionationContainer=new Container(BoxLayout.y());
                         destionationContainer.add(new Label("Destionation : "));
                         destionationContainer.add(new Label(c.getDestination()));
                         DatedestinationContainer.add(destionationContainer);
                             
                             
                             CommandeContainer.add(idetatContainer);
                             CommandeContainer.add(DatedestinationContainer);
                   container.add(CommandeContainer);
        }
        super.add(container);
    super.show();
    }
    
    
    
    
    
}
