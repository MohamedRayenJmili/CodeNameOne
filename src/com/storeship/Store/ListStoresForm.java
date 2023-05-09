
package com.storeship.Store;

import com.codename1.ui.Container;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.storeship.entities.Store;
import com.storeship.gui.BaseForm;
import com.storeship.gui.SessionManager;
import com.storeship.services.ServiceStore;
import java.util.ArrayList;



public class ListStoresForm extends BaseForm {
    Container container=new Container(BoxLayout.y());

    public ListStoresForm(Resources res) {
        super("Liste des Stores", new FlowLayout());
        Toolbar tb = new Toolbar(false);
        setToolbar(tb);
        tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        setTitle("Liste des Stores");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        ArrayList<Store> stores=ServiceStore.getInstance().getAllStore();
        System.out.println(" this is the list of commandands "+stores);
        for (Store s:stores)
        {
            System.out.println("this is a asingle commandes "+s);
           // addElement(c);
           Container StoreContainer=new Container();
           try{
            StoreContainer=ServiceStore.getInstance().StoreinsideContainer(s,SessionManager.getUser().getId());
           // Full Store
              
} catch (Exception e) {
    e.printStackTrace();
}
  container.add(StoreContainer);
        }
        
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
