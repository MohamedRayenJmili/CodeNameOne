package com.mycompany.myapp;


import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import static com.codename1.ui.CN.*;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
import com.codename1.ui.validation.GroupConstraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;

public class MyApplication {

    private Form current;
    private Resources theme;
    private Form home;

    public void init(Object context) {
          updateNetworkThreadCount(2);
          theme = UIManager.initFirstTheme("/theme");
//
//        // Disable the global toolbar as we want a layered toolbar
          Toolbar.setGlobalToolbar(false);
//
//        // Pro only feature, uncomment if you have a pro subscription
          Log.bindCrashProtection(true);


        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }

       new SignInForm(theme).show();
    }
//public void ShowListStores(){
// Form ListStores=new Form("Store List" , new BorderLayout());
// 
// Toolbar tb = ListStores.getToolbar();
// Image icon = theme.getImage("icon.png");
// Container topBar=BorderLayout.west(new Label(icon));
// topBar.add(BorderLayout.SOUTH, new Label("StoreShip"));
// tb.addComponentToSideMenu(topBar);
// 
// tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e->{ShowListStores();});
// tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, e->{ShowProfile();});
// tb.addMaterialCommandToSideMenu("Produits", FontImage.MATERIAL_LIST, e->{ShowProduits();});
// tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e->{ShowWebsite();});
// tb.addMaterialCommandToSideMenu("Settings", FontImage.MATERIAL_SETTINGS, e->{ShowSettings();});
// tb.addMaterialCommandToSideMenu("Payments", FontImage.MATERIAL_PAYMENTS, e->{ShowPayments();});
// tb.addMaterialCommandToSideMenu("Evenements", FontImage.MATERIAL_EVENT, e->{ShowEvents();});
// tb.addMaterialCommandToSideMenu("Reclamations", FontImage.MATERIAL_RECYCLING, e->{ShowReclamations();});
//
//    Container list= new Container(BoxLayout.y());
//    list.setScrollableY(true);
//    for(int i=0;i<11;i++){
//    MultiButton mb=new MultiButton("Store"+i);
//    mb.setTextLine2("show more ...");
//    list.add(mb);
//    }
//        ListStores.add(CENTER,list);
//    // List Store= (List) uib.findByName("List des Stores", ListStores);
//               // Store.addItem("123456");
//        ListStores.show();
//}
//    public void ShowProfile(){
//         Form Profile=new Form("Profile" , new BorderLayout());
//         Profile.show();
//    }
//    public void ShowProduits(){
//         Form Produits=new Form("Produits" , new BorderLayout());
//         Produits.show();
//    }
//    public void ShowSettings(){
//         Form Settings=new Form("Settings" , new BorderLayout());
//         Settings.show();
//}
//    public void ShowPayments(){
//         Form Payments=new Form("Payments" , new BorderLayout());
//         Payments.show();
//}
//    public void ShowEvents(){
//         Form Events=new Form("Events" , new BorderLayout());
//         Events.show();
//}
//    public void ShowReclamations(){
//         Form Reclamations=new Form("Reclamations" , new BorderLayout());
//         Reclamations.show();
//}
//    public void ShowWebsite(){
//         Form Website=new Form("Website" , new BorderLayout());
//         Website.show();
//}
    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

}

