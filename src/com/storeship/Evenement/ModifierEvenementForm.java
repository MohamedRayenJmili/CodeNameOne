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
import com.storeship.entities.Evenement_entite;
import com.storeship.services.ServiceEvenement;


/**
 *
 * @author Lenovo
 */
public class ModifierEvenementForm extends Form {
    
    Form current;
    public ModifierEvenementForm(Evenement_entite r,Form previous) {
        setTitle("Modifier Evenement");
        
        TextField nom = new TextField(r.getLieuEvenement(), "lieuEv" , 20 , TextField.ANY);
        TextField adress = new TextField(r.getDescriptionEvenement(), "DescEv" , 20 , TextField.ANY);
        TextField surface = new TextField(String.valueOf(r.getTitreEvenement()) , "titreEv" , 20 , TextField.ANY);           
        TextField PrixProduit = new TextField(String.valueOf(r.getNbMax_place()) , "nbMax" , 20 , TextField.ANY);           
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setLieuEvenement(nom.getText());
           r.setDescriptionEvenement(adress.getText());
        r.setTitreEvenement(surface.getText());
                
             r.setNbMax_place((int) Float.parseFloat(PrixProduit.getText()));
       //appel fonction modfier avis men service
       
       if(ServiceEvenement.getInstance().modifierEvenement(r)) { // if true
        //   new ListEvenementForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
       //    new ListEvenementForm(res).show();
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
