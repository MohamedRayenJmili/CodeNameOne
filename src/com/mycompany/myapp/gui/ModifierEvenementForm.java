/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Evenement;

import com.mycompany.myapp.services.ServiceEvenement;


/**
 *
 * @author Lenovo
 */
public class ModifierEvenementForm extends Form {
    
    Form current;
    public ModifierEvenementForm(Evenement r,Form previous) {
        setTitle("Modifier Evenement");
        
        TextField nom = new TextField(r.getLieuEV() , "lieuEv" , 20 , TextField.ANY);
        TextField adress = new TextField(r.getDescEv() , "DescEv" , 20 , TextField.ANY);
        TextField surface = new TextField(String.valueOf(r.getTitreEv()) , "titreEv" , 20 , TextField.ANY);           
        TextField PrixProduit = new TextField(String.valueOf(r.getNbMax()) , "nbMax" , 20 , TextField.ANY);           
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setLieuEV(nom.getText());
           r.setDescEv(adress.getText());
        r.setTitreEv(surface.getText());
                
             r.setNbMax((int) Float.parseFloat(PrixProduit.getText()));
       //appel fonction modfier avis men service
       
       if(ServiceEvenement.getInstance().modifierEvenement(r)) { // if true
           new ListEvenementForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListEvenementForm(previous).show();
       });
       
        Container content = BoxLayout.encloseY(
                new FloatingHint(nom),
                new FloatingHint(adress),
                new FloatingHint(surface),
                new FloatingHint(PrixProduit),
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        
        
    }
}
