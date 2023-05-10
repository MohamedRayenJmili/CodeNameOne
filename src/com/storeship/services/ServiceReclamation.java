package com.storeship.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.storeship.entities.Reclamation;
import com.storeship.entities.TypeReclamation;
import com.storeship.utils.Statics;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;

public class ServiceReclamation {

    public static ServiceReclamation instance = null;
    private ConnectionRequest req;
    public boolean resultOK;
    private ArrayList<Reclamation> reclamations;

    private ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addReclamation(Reclamation r) {
        String url = Statics.BASE_URL + "/Json/add_reclamation/" + r.getIdUser() + "/" + r.getIdCommande() + "/" + r.getEtat() + "/" + r.getDate() + "/" + r.getImage()+ "/" + r.getContenu() + "/" + r.getIdProduit() + "/" + r.getIdAdmin() + "/" + r.getType().getId();
        System.out.println("url====" + url );
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
public static ArrayList<Reclamation> parseReclamations(String jsonText) {
    ArrayList<Reclamation> reclamations = new ArrayList<>();

    try {
        JSONParser parser = new JSONParser();
        Map<String, Object> reclamationsJson = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsJson.get("root");       
     for (Map<String, Object> obj : list) {  
    String contenu = (String) obj.get("description");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = obj.get("date").toString();
    Date date = formatter.parse(dateString);
    String etat = (String) obj.get("etat");
    String image = (String) obj.get("image");
         TypeReclamation type = new TypeReclamation("produit");
    int id = ((Number) obj.get("id")).intValue();
         Reclamation r = new Reclamation(id, 8, 0, etat,image, date, contenu, 0, 0, type);
    reclamations.add(r); // add the parsed Reclamation object to the ArrayList
}
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    return reclamations;
}


    public ArrayList<Reclamation> getAllReclamations() {
        String url = Statics.BASE_URL + "/Json/getReclamations";
        req.setUrl(url);
        req.setPost(false);
        System.out.println("com");
         req.addResponseListener(event -> {
             reclamations = parseReclamations(new String(req.getResponseData()));
        });
      
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
    public ArrayList<Reclamation> getReclamationsById(int id) {
        String url = Statics.BASE_URL + "/Json/getReclamationbyId/"+id;
        req.setUrl(url);
        req.setPost(false);
        System.out.println("com");
         req.addResponseListener(event -> {
             reclamations = parseReclamations(new String(req.getResponseData()));
                     System.out.println("com"+reclamations);

        });
      
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }

    public boolean updateReclamation(Reclamation r) {
        String url = Statics.BASE_URL + "/Json/updateReclamation/" + r.getIdReclamation() + "/" + r.getEtat();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean deleteReclamation(int id) {
        String url = Statics.BASE_URL + "/Json/delete_reclamation/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }}