/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Entities.Forms.CommandesForm;

import com.codename1.ui.Button;
import com.mycompany.myapp.*;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Commande;
import com.mycompany.myapp.Entities.Produit;
import com.mycompany.myapp.Entities.StaticPanier;
import com.mycompany.myapp.Entities.User;
import com.mycompany.myapp.Service.Service_Commande;
import com.mycompany.myapp.Service.Service_Panier;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jmili
 */
public class PanierForm extends BaseForm {

    
    Container container=new Container(BoxLayout.y());
    public PanierForm(Resources res) {
        super("Panier", new FlowLayout());
        
        
                /////Testing Adding Products and DIsplaying them from Panier
        // Creating a Produit with an ID of 1, name "Laptop", price 999.99, and quantity 10
Produit p1 = new Produit();
p1.setId(1);
p1.setNom("Laptop");
p1.setPrix(999.99);
p1.setQuantite(10);

// Creating a Produit with an ID of 2, name "Phone", price 599.99, and quantity 5
Produit p2 = new Produit();
p2.setId(2);
p2.setNom("Phone");
p2.setPrix(599.99);
p2.setQuantite(5);

// Creating a Produit with an ID of 3, name "Mouse", price 29.99, and quantity 20
Produit p3 = new Produit();
p3.setId(3);
p3.setNom("Mouse");
p3.setPrix(29.99);
p3.setQuantite(20);

StaticPanier.getInstance().addItemToPanier(p3);
StaticPanier.getInstance().addItemToPanier(p1);
StaticPanier.getInstance().addItemToPanier(p2);
StaticPanier.getInstance().addItemToPanier(p3);
StaticPanier.getInstance().addItemToPanier(p3);
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
                tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        setTitle("Panier Courrant");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {}); 
        Map<Produit, Integer> Paniercourant=StaticPanier.getInstance().getPaniercourrant();
        System.out.println(" this is the list of Panier == "+Paniercourant);
        

        
        
for (Map.Entry<Produit, Integer> entry : Paniercourant.entrySet()) 
        {
            System.out.println("this is a asingle commandes "+entry);
           // addElement(c);
           Container PanierContainer=new Container();
           try{
            PanierContainer =Service_Panier.getInstance().PanierInsideContainer(entry);
           // Full COmmande
              
} catch (Exception e) {
    e.printStackTrace();
}
                                

                   container.add(PanierContainer);
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
        
        
        // button to add Destination Label , and Insert into Database the Details
        
     
           Container ConfirmContainer=new Container(BoxLayout.y());

         Button btn3 = new Button("ConfirmPanier");
        btn3.getAllStyles().setBgColor(0xFAA276);
    btn3.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 14));
    btn3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
    // do something with the commandId
            //move to PaymentForm
       new PaymentForm(res).show();
        }
    });
    ConfirmContainer.add(btn3);
    ConfirmContainer.getStyle().setMarginTop(15);
    super.add(ConfirmContainer);
    super.show();
    }
  
    
    
   
    
    
}
