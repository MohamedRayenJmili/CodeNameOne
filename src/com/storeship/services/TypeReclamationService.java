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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.storeship.entities.TypeReclamation;
import com.storeship.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeReclamationService {

    private static TypeReclamationService instance;
  private ConnectionRequest req;
    public boolean resultOK;
    private ArrayList<TypeReclamation> typeReclamations;

    private TypeReclamationService() {
        req = new ConnectionRequest();

    }

    public static TypeReclamationService getInstance() {
        if (instance == null) {
            instance = new TypeReclamationService();
        }
        return instance;
    }

   public boolean addTypeReclamation(String type) {
    String url = Statics.BASE_URL + "/add_type_reclamation/" + type;
       System.err.println(url);
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
public ArrayList<TypeReclamation> getAllTypeReclamation() {
            String url = Statics.BASE_URL + "/Json/getReclamationTypes";
        ConnectionRequest req = new ConnectionRequest(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    typeReclamations = parseTypeReclamation(new String(req.getResponseData()));
                } catch (IOException | ParseException ex) {
                    System.err.println("Error parsing type reclamations: " + ex.getMessage());
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return typeReclamations;
    }

    public static ArrayList<TypeReclamation> parseTypeReclamation(String jsonText) throws IOException, ParseException {
        ArrayList<TypeReclamation> typeReclamations = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Map<String, Object> typesJson = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) typesJson.get("root");
        for (Map<String, Object> obj : list) {
            int id = (int) Float.parseFloat(obj.get("id").toString());
            String nom = obj.get("nom").toString();
            TypeReclamation tr = new TypeReclamation(id, nom);
            typeReclamations.add(tr);
        }
        return typeReclamations;
    }

}
