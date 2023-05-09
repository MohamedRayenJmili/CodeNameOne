/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.storeship.Evenement;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

import com.storeship.entities.Reservation_entite;
import com.storeship.services.ServiceReservation;


/**
 *
 * @author Lenovo
 */
public class ModifierReservationForm extends Form {
    
    Form current;
    public ModifierReservationForm(Reservation_entite r,Form previous) {
        setTitle("Modifier Cours");
         TextField nb = new TextField(String.valueOf(r.getNbr_place()) , "nbPlaces" , 20 , TextField.ANY);           
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
               
             r.setNbr_place((int) Float.parseFloat(nb.getText()));
       //appel fonction modfier avis men service  
           
       //appel fonction modfier avis men service
       
       if(ServiceReservation.getInstance().modifierReservation(r)) { // if true
           new ListReservationForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListReservationForm(previous).show();
       });
       
        Container content = BoxLayout.encloseY(
                new FloatingHint(nb),
                
               
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        
        
    }
}
