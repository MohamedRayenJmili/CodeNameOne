/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;

import com.mycompany.myapp.utils.Statics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceEvenement{
    
    //singleton 
    public static ServiceEvenement instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceEvenement getInstance() {
        if(instance == null )
            instance = new ServiceEvenement();
        return instance ;
    }
    
    
    
    public ServiceEvenement() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutEvenement(Evenement t) {
        
        String url =Statics.BASE_URL+"AddEvenementJSON?lieuEv="+t.getLieuEV()+"&DescEv="+t.getDescEv()+"&titreEv="+t.getTitreEv()+"&nbMax="+t.getNbMax()+"&imageEv="+t.getImageFile();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Evenement>affichageEvenement() {
        ArrayList<Evenement> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"AllEvenementJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapArena = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapArena.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Evenement re = new Evenement();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String lieuEv = obj.get("lieuEv").toString();
                        String DescEv = obj.get("DescEv").toString();
                        String titreEv = obj.get("titreEv").toString();
                        float nbMax = Float.parseFloat(obj.get("nbMax").toString());
//                         String dateStringD = obj.get("date_debut").toString();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                    Date date = formatter.parse(dateStringD);
                   
//                     String dateStringF = obj.get("date_fin").toString();
                    SimpleDateFormat formatterF = new SimpleDateFormat("yyyy-MM-dd");
//                    Date dateF = formatter.parse(dateStringF);
                   
                        re.setId((int)id);
                        re.setLieuEV(lieuEv);
                          re.setDescEv(DescEv);
                      re.setTitreEv(titreEv);
                      re.setNbMax((int)nbMax);
//                      re.setDatedebut(date);
//                     re.setDatefin(dateF);
                         //Date
                   // String DateConverter =  obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("2") ,obj.get("Date").toString().lastIndexOf("T"));
                    
     
                   // re.setDateC(DateConverter);
                     //Date
                  //  String DateConverter =  obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("2") ,obj.get("Date").toString().lastIndexOf("T"));
                    
     
                  //  re.setDateCreation(DateConverter);
                    
                        
//Date 
                       // String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        
                        //re.setDateC(DateConverter);
                          //re.setDateCreation(DateConverter);
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        System.out.println(result);
        return result;
        
        
    }
    
    
    
  
    
    //Delete 
    public boolean deleteEvenement (int id ) {
        String url = Statics.BASE_URL +"DeleteEvenementJSON/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierEvenement (Evenement  t) {
        String url = Statics.BASE_URL +"UpdateEvenementJSON/"+t.getId()+"?lieuEv="+t.getLieuEV()+"&DescEv="+t.getDescEv()+"&titreEv="+t.getTitreEv()+"&nbMax="+t.getNbMax();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    

    
}