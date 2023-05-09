/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.storeship.Produit;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.storeship.entities.Produit;
import com.storeship.services.ProduitService;


/**
 *
 * @author aminh
 */
public class GererProduit extends Form{

Resources theme1 = UIManager.initFirstTheme("/theme");
     
    Produit currentProduit;


    
       TextField nomTF;
    Label nomLabel;
    
       TextField prixTF;
    Label prixLabel;
    
 
    
       TextField quantityTF;
    Label quantityLabel;

         TextField etatTF;
    Label etatLabel;


    Button manageButton;
    
     

    Form previous;

    public GererProduit(Form previous) {
        super(AfficherProduit.currentProduit == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentProduit = AfficherProduit.currentProduit;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {





        nomLabel = new Label("nom : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le nom");
        
     
        
        
        prixLabel = new Label("prix : ");
        prixLabel.setUIID("labelDefault");
        prixTF = new TextField();
        prixTF.setHint("Tapez le prix");
        

        
        
        quantityLabel = new Label("quantite : ");
        quantityLabel.setUIID("labelDefault");
        quantityTF = new TextField();
        quantityTF.setHint("Tapez le quantite");


        
        etatLabel = new Label("etat : ");
        etatLabel.setUIID("labelDefault");
        etatTF = new TextField();
        etatTF.setHint("Tapez l' etat");





        if (currentProduit== null) {


            manageButton = new Button("Ajouter");
        } else {

                 nomTF.setText(currentProduit.getNom());
   
                 prixTF.setText(String.valueOf(currentProduit.getPrix()));
                       
               quantityTF.setText(String.valueOf(currentProduit.getQuantite()));
               
               
                 etatTF.setText(String.valueOf(currentProduit.getEtat()));
     

               

        
           




            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");
 
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    
        container.setUIID("containerRounded");

        container.addAll(


                nomLabel, nomTF,
                prixLabel,prixTF,
                quantityLabel,quantityTF,
                   etatLabel,etatTF,
          
                manageButton
        );

        this.addAll(container);
    }
    
 

    private void addActions() {
        

        
        
        
        
        
        
        
   if (currentProduit == null) {
       
       
    manageButton.addActionListener(action -> {
        if (controleDeSaisie()) {
            int responseCode = ProduitService.getInstance().add(
                    new Produit(
                     
                           Integer.parseInt(quantityTF.getText()),
                           Integer.parseInt(etatTF.getText()),
                            nomTF.getText(),
                            Float.parseFloat(prixTF.getText())
                    
                          
                            
                            
                          
                    ),
                    ""
            );
            if (responseCode == 200) {
                EmailSender.sendEmail();
                // call the email sending method here
                Dialog.show("Succés", "Produit ajouté avec succes", new Command("Ok"));
                showBackAndRefresh();
            } else {
                Dialog.show("Erreur", "Erreur d'ajout de Produit. Code d'erreur : " + responseCode, new Command("Ok"));
            }
        }
    });
} else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ProduitService.getInstance().edit(
                            new Produit(
                                    currentProduit.getId(),
                               Integer.parseInt(quantityTF.getText()),
                           Integer.parseInt(etatTF.getText()),
                            nomTF.getText(),
                            Float.parseFloat(prixTF.getText())
                    
                          
                                 

                            )
                    );
                    if (responseCode == 200) {
                        Dialog.show("Succés", "Produit modifié avec succes", new Command("Ok"));
                        showBackAndRefresh();
                    } else if (responseCode == 250) {
                        Dialog.show("Error", "Heure invalide", new Command("Ok"));
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de Produit. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((AfficherProduit) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        
          if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
          
         if (prixTF.getText().equals("")) {
        Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
        return false;
    } else {
        try {
            Double.parseDouble(prixTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", "Le prix doit être un nombre", new Command("Ok"));
            return false;
        }
    }
            
              if (etatTF.getText().equals("")) {
            Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
            return false;
        }
              
          if (quantityTF.getText().equals("")) {
        Dialog.show("Avertissement", "Sujet vide", new Command("Ok"));
        return false;
    } else {
        try {
            Double.parseDouble(quantityTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", "La quantité doit être un nombre", new Command("Ok"));
            return false;
        }
    }



       

        return true;
    }    
}
