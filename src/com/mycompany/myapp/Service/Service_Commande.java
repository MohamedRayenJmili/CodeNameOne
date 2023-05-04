/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Service;

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
import com.mycompany.myapp.Entities.Commande;
import com.mycompany.myapp.Statics.Statics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import com.codename1.ui.Button;
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
/**
 *
 * @author Plop
 */
public class Service_Commande {

    public ArrayList<Commande> Commandes;
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

        String url = Statics.URL_COMMUN + "gson/commande/historique";

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

    
    public Commande addCommande(Commande c){
        
        String etatcommande = c.getEtat();
        String destinationcommande =  c.getDestination();
        Float prixcommande =c.getPrix();
        String paymentcommande =c.getPayment();
                Commande commande=new Commande();
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        //String url = Statics.BASE_URL + "create?nomStation=" + s.getNomStation() + "&localisationStation=" + s.getLocalisationStation() + "&veloStation=" + s.getVeloStation();
//        String url = DataSource.BASE_URL + "add/" + name + "/" + vill + "/" + nbr;
       //////////// ul 2 
        String url = Statics.URL_COMMUN+ "gson/Addcommande?client=6&etat="+etatcommande+"&destination="+destinationcommande+"&"
                + "prix="+prixcommande+"&payment="+paymentcommande+"";
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
    
    
 public Container commandeinsideContainer(Commande c) {
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



private void actionButtonClicked(int commandId) {
    // do something with the commandId
}

}
