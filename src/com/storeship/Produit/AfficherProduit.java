/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.storeship.Produit;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.storeship.entities.Produit;
import com.storeship.gui.BaseForm;
import com.storeship.services.ProduitService;
import com.codename1.ui.util.Resources;
import com.storeship.entities.StaticPanier;
import com.storeship.gui.SessionManager;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.FlowLayout;
import com.pidevv.MyApplication;


import java.util.ArrayList;

/**
 *
 * @author aminh
 */
public class AfficherProduit extends BaseForm{
    
      Resources theme1 = UIManager.initFirstTheme("/theme");

    public static Produit currentProduit= null;
    Button addBtn;


    public AfficherProduit() {
     super("Panier", new FlowLayout());
      
        Toolbar tb = new Toolbar(false);
        setToolbar(tb);
                tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        setTitle("Panier Courrant");
        getContentPane().setScrollVisible(false);
          Resources res =MyApplication.getTheme();
        setTitle("List Reservations");
        super.addSideMenu(res);
        addGUIs();
        addActions();

//        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);


        ArrayList<Produit> listProduit = ProduitService.getInstance().getAll();


        if (listProduit.size() > 0) {
            for (Produit produit : listProduit) {
                Component model = makeProduitModel(produit);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentProduit= null;
            new GererProduit(this).show();
        });

    }

    Label sujetLabel, sujetLabe2, sujetLabe3,sujetLabe4;


    private Container makeModelWithoutButtons(Produit produit) {
        Container produitModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //produitModel.setUIID("containerRounded");


      

        sujetLabel = new Label("nom : " + produit.getNom());
        sujetLabel.setUIID("labelDefault");
        
       sujetLabe2 = new Label("prix : " + produit.getPrix());
        sujetLabe2.setUIID("labelDefault");
        
         sujetLabe3 = new Label("quantite: " + produit.getQuantite());
        sujetLabe3.setUIID("labelDefault");
        
         sujetLabe4 = new Label("etat : " + produit.getEtat());
        sujetLabe4.setUIID("labelDefault");
        
        //aaaaaaa
  Container imageContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            String imageUrl = produit.getPhoto();
            System.out.println(
            
            
            
            
            
            );
            if (imageUrl != null && !imageUrl.isEmpty()) {
                String fullImageUrl = "http://127.0.0.1:8000/uploads/user/images/" + imageUrl;
                System.out.println("Full IMAGE URL CALLER "+ fullImageUrl);
                try {
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(350, 550), true);
                    URLImage urlImage = URLImage.createToStorage(placeholder, "rimage_" + imageUrl, fullImageUrl, URLImage.RESIZE_SCALE);
                    Label photoEvent = new Label(urlImage);
                    imageContainer.add(photoEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //veloContainer.add(BorderLayout.WEST, imageContainer);



        produitModel.addAll(

             sujetLabel,sujetLabe2 , sujetLabe3, sujetLabe4 ,imageContainer
        );

        return produitModel;
    }

    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeProduitModel(Produit produit) {

        Container produitModel = makeModelWithoutButtons(produit);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

       if(!SessionManager.getUser().getRoles().equals("ROLE_CLIENT")){
             editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentProduit = produit;
            new GererProduit(this).show();
        });
       }
      
        String buttondetails="";
        if(SessionManager.getUser().getRoles().equals("ROLE_CLIENT"))
                 buttondetails="Ajouter Panier";
        else
             buttondetails="Supprimer";
        deleteBtn = new Button(buttondetails);
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
              if(!SessionManager.getUser().getRoles().equals("ROLE_CLIENT")){
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce Produit ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = ProduitService.getInstance().delete(produit.getId());

                if (responseCode == 200) {
                    currentProduit = null;
                    dlg.dispose();
                    produitModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du Produit. Code d'erreur : " + responseCode, new Command("Ok"));
                }
                  if (responseCode == 200) {
                EmailSender.sendEmail();
                // call the email sending method here
                Dialog.show("Succés", "Produit Supprimer avec succes", new Command("Ok"));
             
            } else {
                Dialog.show("Erreur", "Erreur d'ajout de Produit. Code d'erreur : " + responseCode, new Command("Ok"));
            }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
              }
              else
              {
                  StaticPanier.getInstance().addItemToPanier(produit);
              }
        });
        if(!SessionManager.getUser().getRoles().equals("ROLE_CLIENT"))
        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        produitModel.add(btnsContainer);

        return produitModel;
    }
    
}
