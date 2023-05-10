/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.storeship.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.storeship.entities.Commande;
import com.storeship.entities.Detail_Commande;
import com.storeship.entities.Produit;
import com.storeship.utils.Statics;
/**
 *
 * @author Plop
 */
public class Service_Commande {

    public ArrayList<Commande> Commandes;
      public ArrayList<Detail_Commande> DetailCommandes;
    public Commande Commande;
    private ConnectionRequest req;
    public static Service_Commande instance = null;

    public static Service_Commande getInstance() {
        if (instance == null) {
            instance = new Service_Commande();
        }
        return instance;
    }

    private Service_Commande() {
        req = new ConnectionRequest();
    }

    public ArrayList<Commande> getlistcommandes(String jsontext) {
        try {
            Commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CommandesListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) CommandesListJson.get("root");
            System.out.println("Global List " + list);
            for (Map<String, Object> obj : list) {
                Commande c = new Commande();
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println("id variable == " + id);
//                
               
              try {
    String dateString = obj.get("date").toString();
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    Date date = inputFormat.parse(dateString);
    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
    c.setDate(outputFormat.format(date));
} catch (Exception e) {
    e.printStackTrace();
}
      
///

                c.setDestination(obj.get("destination").toString());
                // add more according to the GSON response
                c.setId((int) id);
                c.setEtat(obj.get("etat").toString());
                Commandes.add(c);
            }

        } catch (Exception e) {
    e.printStackTrace();
        }
        return Commandes;

    }
    public ArrayList<Detail_Commande> getlistDetailscommandes(String jsontext) {
        try {
            DetailCommandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CommandesListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) CommandesListJson.get("root");
            System.out.println("Global List " + list);
            for (Map<String, Object> obj : list) {
                Detail_Commande c = new Detail_Commande();
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println("id variable == " + id);
               c.setQuantite((int) Float.parseFloat(obj.get("quantite").toString()));
                c.setPrix_total( Float.parseFloat(obj.get("prix_total").toString()));
                // add more according to the GSON response
                c.setId((int) id);
                c.setEtat(obj.get("etat").toString());
                // setting the Produit id , and Produit Nom
                 Map<String, Object> produitJson = (Map<String, Object>) obj.get("produit");
    float produitId = Float.parseFloat(produitJson.get("id").toString());
    String produitNom = produitJson.get("nom").toString();
    Produit produit = new Produit((int)produitId, produitNom);
    c.setProduit(produit);
    
         Map<String, Object> commandeJson = (Map<String, Object>) obj.get("commande");
    float commandeId = Float.parseFloat(commandeJson.get("id").toString());
    String commandeetat = commandeJson.get("etat").toString();
    Commande commande = new Commande((int)commandeId, commandeetat);
    c.setCommande(commande);
                
                
                DetailCommandes.add(c);
            }

        } catch (Exception e) {
    e.printStackTrace();
        }
        return DetailCommandes;

    }
    
    
    public Commande getidCommande(String jsontext) {
                    Commande commande =new Commande();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> CommandesListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
           
            System.out.println("Global List " + CommandesListJson);
           
                float id = Float.parseFloat(CommandesListJson.get("id").toString());
                
                commande.setId((int) id);

        } catch (Exception e) {
            System.out.println("Error Inside the Service_Commande Line 94 \n" + e.getMessage());
        }
        return commande;

    }


    public ArrayList<Commande> getAllCommande() {

        // get Connected Client ID
        String client="1";
        String url = Statics.BASE_URL + "/gson/commande/historique?client="+client;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("Listening Correct");
                Commandes = getlistcommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("Retunring COmmandes +++++" + Commandes);
        return Commandes;
    }
    
     public ArrayList<Detail_Commande> getConnectedStoresCommande() {

         String store="6";
        String url = Statics.BASE_URL + "/gson/historique/DetailCommande/Store?store="+store;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("Listening Correct");
                DetailCommandes= getlistDetailscommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueue(req);
        System.out.println("Retunring COmmandes +++++" + Commandes);
        return DetailCommandes;
    }

    
    public Commande addCommande(Commande c){
        
        String etatcommande = c.getEtat();
        String destinationcommande =  c.getDestination();
        Float prixcommande =c.getPrix();
        String paymentcommande =c.getPayment();
                Commande commande=new Commande();
       //////////// ul 2 
        String url = Statics.BASE_URL+ "/gson/Addcommande?client=6&etat="+etatcommande+"&destination="+destinationcommande+"&payment="+paymentcommande+"";
        System.out.println(url);
        //ConnectionRequest.setCertificateValidation(false);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Commande = getidCommande(new String(req.getResponseData())) ; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        
    return Commande;}
    
    public void addDetail_Commande(Produit produit,int quantite,Commande c){
        
        String etatcommande = c.getEtat();
        String destinationcommande =  c.getDestination();
        Float prixcommande =c.getPrix();
        String paymentcommande =c.getPayment();
                Commande commande=new Commande();
       //////////// ul 2 
        String url = Statics.BASE_URL+ "/gson/AddDetail?commande="+c.getId()+"&etat=Pending&quantite="+quantite+"&produit="+produit.getId()+"";
        System.out.println(url);
        //ConnectionRequest.setCertificateValidation(false);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Commande = getidCommande(new String(req.getResponseData())) ; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        
    
    }
    
     public boolean UpdateDetail_Commande(Detail_Commande detail){
        final boolean[] returnboolean={false};
        String etatcommande = detail.getEtat();
        String iddetail = String.valueOf(detail.getId());
    
        String url = Statics.BASE_URL+ "/gson/update/DetailCommande/Store?detail="+iddetail+"&etat="+etatcommande;
        System.out.println(url);
        //ConnectionRequest.setCertificateValidation(false);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             returnboolean[0]=(evt.getResponseCode() == 200); //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
 
        
    return returnboolean[0];
    }
 public Container commandeinsideContainer(Commande c) {
    Container CommandeContainer = new Container(new BorderLayout());
    CommandeContainer.getAllStyles().setBorder(Border.createLineBorder(2, 0x000000));
    CommandeContainer.getAllStyles().setMarginUnit(Style.UNIT_TYPE_DIPS);
    CommandeContainer.getAllStyles().setMarginBottom(3);
    CommandeContainer.getAllStyles().setBgColor(0x915951);

    Container SecondContainer = new Container(BoxLayout.y());
    // First Line in Container
    Container idetatContainer = new Container(BoxLayout.x());
    idetatContainer.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 14));
    idetatContainer.add(new Label("Id :"));
    idetatContainer.add(new Label(String.valueOf(c.getId())));
    idetatContainer.add(new Label(" Etat :"));
    idetatContainer.add(new Label(c.getEtat()));

    // Second Line in Container
    Container destionationContainer = new Container(BoxLayout.y());
    destionationContainer.add(new Label("Destionation : "));
    destionationContainer.add(new Label(c.getDestination()));

    SecondContainer.add(idetatContainer);
    SecondContainer.add(destionationContainer);

    Container dateContainer = new Container(BoxLayout.x());
    dateContainer.add(new Label("Date: " + c.getDate()));
    SecondContainer.add(dateContainer);

    Button btn = new Button("Details");
    btn.getAllStyles().setBgColor(0xFAA276);
    btn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 14));
    btn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            actionButtonClicked(c.getId());
        }
    });
    CommandeContainer.add(BorderLayout.EAST,btn);
            CommandeContainer.add(BorderLayout.CENTER,SecondContainer);

  


    return CommandeContainer;
}
 
 
 
 public Container DetailcommandeinsideContainer(Detail_Commande c) {
    Container CommandeContainer = new Container(new BorderLayout());
    CommandeContainer.getAllStyles().setBorder(Border.createLineBorder(2, 0x000000));
    CommandeContainer.getAllStyles().setMarginUnit(Style.UNIT_TYPE_DIPS);
    CommandeContainer.getAllStyles().setMarginBottom(3);
    CommandeContainer.getAllStyles().setBgColor(0xFAE1B2);

    Container SecondContainer = new Container(BoxLayout.y());
    // First Line in Container
    Container idetatContainer = new Container(BoxLayout.x());
    idetatContainer.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 14));
    idetatContainer.add(new Label("Id :"));
    idetatContainer.add(new Label(String.valueOf(c.getId())));
    
    
        Container idetatContainer2 = new Container(BoxLayout.x());
idetatContainer2.add(new Label(" Etat :"));
Label etatlabel=new Label(c.getEtat());
    idetatContainer2.add(etatlabel);
    
    Container assemble=new Container(BoxLayout.y());

    // Second Line in Container
    Container quantiteContainer = new Container(BoxLayout.y());
    quantiteContainer.add(new Label("quantite : "));
    quantiteContainer.add(new Label(String.valueOf(c.getQuantite())));

    assemble.addAll(idetatContainer,idetatContainer2);
    SecondContainer.add(assemble);
    SecondContainer.add(quantiteContainer);

    Container IdProduitContainer = new Container(BoxLayout.x());
    IdProduitContainer.add(new Label("Id Produit : "+c.getProduit().getId()));
    Container produitContainer = new Container(BoxLayout.x());
    produitContainer.add(new Label("produit : " + c.getProduit().getNom()));
    
    SecondContainer.addAll(IdProduitContainer,produitContainer);
        Container combobutton=new Container(BoxLayout.y());

    if (!c.getEtat().equals("Canceled") && !c.getEtat().equals("Completed")) {
     ComboBox<String> etatComboBox = new ComboBox<>("Pending", "Progress", "Completed");

    Button btn = new Button("Update Etat");
    btn.getAllStyles().setBgColor(0xFAA276);
    btn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 14));
    btn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {

            c.setEtat(etatComboBox.getSelectedItem());
                 if (UpdateDetail_Commande(c))
                 {
                     etatlabel.setText(c.getEtat());
                     if (c.getEtat().equals("Completed"))
                     {
                                              combobutton.removeAll();

                     }
                 }

        }
    });
  
    etatComboBox.setSelectedItem(c.getEtat());

    combobutton.addAll(etatComboBox,btn);
    }  
    CommandeContainer.add(BorderLayout.EAST,combobutton);
    
  
            CommandeContainer.add(BorderLayout.CENTER,SecondContainer);



    return CommandeContainer;
}



private void actionButtonClicked(int commandId) {
    // do something with the commandId
}


}
