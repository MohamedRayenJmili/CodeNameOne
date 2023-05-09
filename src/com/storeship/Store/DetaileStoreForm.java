/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.storeship.Store;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.storeship.entities.Store;
import com.storeship.gui.BaseForm;
import com.storeship.gui.SessionManager;
import com.storeship.services.ServiceStore;
import java.util.ArrayList;

/**
 *
 * @author Jmili
 */
public class DetaileStoreForm extends BaseForm {
    Container container=new Container(BoxLayout.y());

    public DetaileStoreForm(int id) {
                super("Detaile Store", BoxLayout.y());
        Toolbar tb = new Toolbar(false);
        setToolbar(tb);
                tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setTitle("Detaile d'un store");
        tb.addSearchCommand(e -> {});
         Store store=ServiceStore.getInstance().getOneStore(id);

           // addElement(c);
           Container StoreContainer=new Container();
           try{
               
               
            StoreContainer=ServiceStore.getInstance().StoreinsideContainer(store,SessionManager.getUser().getId());
           // Full Store
              
} catch (Exception e) {
    e.printStackTrace();
         
}
  container.add(StoreContainer);
        
        
//        Commande c=new Commande();
//        c.setEtat("Pending");
//        c.setDestination("Tunis Centre Ville");
//        c.setPayment("chk-zaezaezaeazezaeza");
//        c.setPrix(52.0f);
//        c.setUser(new User(6));
//        Commande commande=Service_Commande.getInstance().addCommande(c);
//        System.out.println("nEW Commade ID "+commande);
container.getStyle().setMarginTop(15);
        super.add(container);
    super.show();
    }
    
    
}
