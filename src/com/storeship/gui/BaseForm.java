/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.storeship.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.storeship.Commande.CommandeForm;
import com.storeship.Commande.PanierForm;
import com.storeship.Commande.StoreCommandeForm;
import com.storeship.Evenement.ListEvenementForm;
import com.storeship.Evenement.ListReservationForm;
import com.storeship.Produit.AfficherProduit;
import com.storeship.Reclamation.AddReclamation;
import com.storeship.Reclamation.ReclamationStat;
import com.storeship.Store.DetaileStoreForm;
import com.storeship.Store.ListStoresForm;
import com.storeship.Store.NewStoreForm;
import com.storeship.entities.Store;
import com.storeship.services.ServiceStore;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile.jpg"), "PictureWhiteBackgrond"))
        ));

        tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_HOME, e -> new StatistiquePieForm(res).show());
        tb.addMaterialCommandToSideMenu("Profle", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());

        if (SessionManager.getUser().getRoles().equals("ROLE_CLIENT")) {
            tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_PAYMENTS, e -> new PanierForm(res).show());
            tb.addMaterialCommandToSideMenu("Historique Commande", FontImage.MATERIAL_EXIT_TO_APP, e -> new CommandeForm(res).show());
            tb.addMaterialCommandToSideMenu("Liste Evenement", FontImage.MATERIAL_EXIT_TO_APP, e -> new ListEvenementForm(res).show());
            tb.addMaterialCommandToSideMenu("Liste Store", FontImage.MATERIAL_EXIT_TO_APP, e -> new ListStoresForm(res).show());
            tb.addMaterialCommandToSideMenu("Liste Produit", FontImage.MATERIAL_EXIT_TO_APP, e -> new AfficherProduit().show());
            tb.addMaterialCommandToSideMenu("Reclamation Stats", FontImage.MATERIAL_EXIT_TO_APP, e -> new ReclamationStat().show());
            tb.addMaterialCommandToSideMenu("Add Reclamation", FontImage.MATERIAL_EXIT_TO_APP, e -> new AddReclamation().show());
            tb.addMaterialCommandToSideMenu("Liste Reservation", FontImage.MATERIAL_EXIT_TO_APP, e -> new ListReservationForm().show());

        } else if (SessionManager.getUser().getRoles().equals("ROLE_PARTNER")) {
            try {
                Store store = ServiceStore.getInstance().getOneStore(SessionManager.getUser().getId());

                tb.addMaterialCommandToSideMenu("My Store", FontImage.MATERIAL_EXIT_TO_APP, e -> {
                    if (store == null) {
                        System.out.println("message 1");
                        new NewStoreForm(res).show();
                        System.out.println("message 2");
                    } else {
                        System.out.println("messsage 3");
                        new DetaileStoreForm(SessionManager.getUser().getId()).show();
                        System.out.println("message 4");

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            tb.addMaterialCommandToSideMenu("Partner Commande", FontImage.MATERIAL_EXIT_TO_APP, e -> new StoreCommandeForm(res).show());

        }

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res).show());

    }

}
