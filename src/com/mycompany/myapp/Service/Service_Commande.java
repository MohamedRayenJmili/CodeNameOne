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
import java.util.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.Commande;
import com.mycompany.myapp.Statics.Statics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author Plop
 */
public class Service_Commande {

    public ArrayList<Commande> Commandes;
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
               

         String dateString = obj.get("date").toString();
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
Date date = dateFormat.parse(dateString);
c.setDate(date);
///

                c.setDestination(obj.get("destination").toString());
                // add more according to the GSON response
                c.setId((int) id);
                c.setEtat(obj.get("etat").toString());
                Commandes.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error Inside the Service_Commande Line 59 \n" + e.getMessage());
        }
        return Commandes;

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
      int idclent = .getUser();
        String etatcommznde = s.getLocalisationStation();
        string destinationcommande =  s.getVeloStation();
        string prixcommande =;
        string paymentcommande =;
                
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        //String url = Statics.BASE_URL + "create?nomStation=" + s.getNomStation() + "&localisationStation=" + s.getLocalisationStation() + "&veloStation=" + s.getVeloStation();
//        String url = DataSource.BASE_URL + "add/" + name + "/" + vill + "/" + nbr;
        String url = Statics.URL_COMMUN+ "/gson/Addcommande?nomStation=aaa&localisationStation=bbb&veloStation=123";
       //////////// ul 2 
        String url2 = Statics.URL_COMMUN+ "/gson/Addcommande?client=6&etat=Pending&destination=Tunis+Centre+Ville\"%20+%20\"&prix=550.00&payment=chk6-ezaeazeazezaeaz";
        System.out.println(url);
        //ConnectionRequest.setCertificateValidation(false);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        
    return c;}
}
