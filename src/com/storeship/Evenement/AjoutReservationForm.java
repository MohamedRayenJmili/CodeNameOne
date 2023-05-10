/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.storeship.Evenement;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.pidevv.MyApplication;
import com.storeship.entities.Evenement_entite;
import com.storeship.entities.Reservation_entite;
import com.storeship.services.ServiceReservation;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author bhk
 */
public class AjoutReservationForm extends Form{

    public AjoutReservationForm(Form previous,Evenement_entite evenement) {
        setTitle("Add a new Cours");
        setLayout(BoxLayout.y());
        
        TextField nom = new TextField("","nbPlaces");
       
        Button btnValider = new Button("Add Reservation");
        Button btnAfficher = new Button("Afficher Reservation");
        btnAfficher.addActionListener((evt) -> {
          //  new ListEvenementForm(res).show();
        });
       btnValider.addActionListener((e) -> {
            
            
            try {
                
                if(  nom.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                   // Create a Date object with the current date
// Get the current date
Date currentDate = new Date();

// Format the date using the SimpleDateFormat object
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String formattedDate = formatter.format(currentDate);

// Create the Reservation object with the formatted date and the nbPlaces from the TextField
Reservation_entite a = new Reservation_entite();
a.setNbr_place(Integer.parseInt(nom.getText()));
a.setEv(evenement);
                  
                    System.out.println("data  Commande == "+a);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceReservation.getInstance().ajoutReservation(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListCommandeForm
                    new ListReservationForm().show();
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        addAll(nom,btnValider,btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
