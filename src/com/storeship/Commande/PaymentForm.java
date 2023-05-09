/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.storeship.Commande;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.storeship.entities.Commande;
import com.storeship.entities.Produit;
import com.storeship.entities.StaticPanier;
import com.storeship.entities.User;
import com.storeship.gui.BaseForm;
import com.storeship.gui.SessionManager;
import com.storeship.services.PaymentProcessor;
import com.storeship.services.Service_Commande;
import java.util.ArrayList;
import java.util.Map;
import jdk.internal.dynalink.beans.StaticClass;

/**
 *
 * @author Jmili
 */
public class PaymentForm extends BaseForm {

    
    Container container=new Container(BoxLayout.y());
    double totalPrice = 0;

    public PaymentForm(Resources res) {
     
        super("Panier", new FlowLayout());
         Toolbar tb = new Toolbar(false);
        setToolbar(tb);
                tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        setTitle("Payment Form");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
           //get the connected User
               User user=SessionManager.getUser();
//--------------------------------------------------------------------
        TextField destinationLabel=new TextField("","Destination");
        TextField Cardinfo=new TextField("","Card Informations");
                TextField ExpirationMonth=new TextField("","Expiration Month ");
                        TextField ExpirationYear=new TextField("","Expiration Year ");
                        TextField cvc=new TextField("","cvc ");
Container informationsContainer=new Container(BoxLayout.y());

///Calculate the total SUm

///// Calculate the TOtal Sum
// Create label for sum
Label sumLabel = new Label("Total: " + totalPrice);
sumLabel.getAllStyles().setFgColor(0xFF0000); // Set the color to red
Map<Produit, Integer> panierCourant = StaticPanier.getPaniercourrant();

for (Map.Entry<Produit, Integer> entry : panierCourant.entrySet()) {
    Produit produit = entry.getKey();
    int quantite = entry.getValue();
    double itemTotalPrice = produit.getPrix() * quantite;
    totalPrice += itemTotalPrice;
}
// Create label for length of map
Label lengthLabel = new Label("Panier Length: " + StaticPanier.getPaniercourrant().size());
lengthLabel.getAllStyles().setFgColor(0x0000FF); // Set the color to blue
Button btn4 = new Button("ConfirmPanier");
        btn4.getAllStyles().setBgColor(0xFAA276);
    btn4.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 14));
    btn4.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
    // do something with the commandId
            //move to PaymentForm
       Commande commande=new Commande();
       /////// total Prix
       Map<Produit, Integer> panierCourant = StaticPanier.getPaniercourrant();

for (Map.Entry<Produit, Integer> entry : panierCourant.entrySet()) {
    Produit produit = entry.getKey();
    int quantite = entry.getValue();
    double itemTotalPrice = produit.getPrix() * quantite;
    totalPrice += itemTotalPrice;
}
//-------------------------------------------------------------------

       commande.setUser(user);
       commande.setEtat("Pending");
       commande.setDestination(destinationLabel.getText());
       commande.setPrix((float)totalPrice);
       commande.setPayment("");
       // ----------------------Payment -----------------------------------------
   PaymentProcessor pp=new PaymentProcessor();
       commande=pp.processPayment(Cardinfo.getText(),ExpirationMonth.getText(),ExpirationYear.getText(),cvc.getText(),commande);

///----------------------------------------------------------------------------
       
       if (commande.getPayment().length()!=0)
       {
       Commande commande2=Service_Commande.getInstance().addCommande(commande);
       commande.setId(commande2.getId());
       

for (Map.Entry<Produit, Integer> entry : panierCourant.entrySet()){
    Service_Commande.getInstance().addDetail_Commande(entry.getKey(), entry.getValue(), commande);
    System.out.println("Added ++++"+entry);
}
        StaticPanier.getInstance().clear();
        
        }  else {
           Dialog.show("Payment Failure", "Your Payment Process Has Failed", "OK", null);

    }
        }
      
    });
    container.getStyle().setMarginTop(15);
    container.addAll(new Label("Destination"),destinationLabel,new Label("Card Info"),Cardinfo,new Label("Expiration Month"),ExpirationMonth,new Label("Expiration Year"),ExpirationYear,new Label("CVC"),cvc,btn4);
        super.add(container);
    super.show();
    }
  
    
    
   
    
    
}
