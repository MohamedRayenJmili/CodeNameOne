/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.storeship.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.storeship.entities.Reservation_entite;
import com.storeship.gui.SessionManager;
import com.storeship.utils.Statics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceReservation{
    
    //singleton 
    public static ServiceReservation instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceReservation getInstance() {
        if(instance == null )
            instance = new ServiceReservation();
        return instance ;
    }
    
    
    
    public ServiceReservation() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutReservation(Reservation_entite t) {
        
      String client=String.valueOf(SessionManager.getUser().getId());
        String url =Statics.BASE_URL+"/AddReservationJSON?nbPlaces="+t.getNbr_place()+"&idEvent="+t.getEv().getIdEvenement()+"&client="+client;
        System.out.println("url Reservation "+ url);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Reservation_entite>affichageReservation() {
        ArrayList<Reservation_entite> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/AllReservationJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCours = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCours.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reservation_entite re = new Reservation_entite();
                    float id = Float.parseFloat(obj.get("id").toString());
                    float nb = Float.parseFloat(obj.get("nbPlaces").toString());

                    // Parse the date string from the JSON response and set it on the Reservation object
                    
                    re.setId_reservation((int) id);
                    re.setNbr_place((int) nb);
                    String dateString = obj.get("date").toString();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = formatter.parse(dateString);
                    re.setDate(date);


                    result.add(re);
                }
                         //Date
                 //  String Date =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("2") ,obj.get("date").toString().lastIndexOf("T"));
                    
     
                 //  re.setDate(Date);
                     //Date
                  //  String DateConverter =  obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("2") ,obj.get("Date").toString().lastIndexOf("T"));
                    
     
                  //  re.setDateCreation(DateConverter);
                    
                        
//Date 
                       //String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("T"));
                        
                        
                       
                          //re.setDateCreation(DateConverter);
                        
                        //insert data into ArrayList result
                        
                       
                    
                    
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        System.out.println(result);
        return result;
        
        
    }
    
     public ArrayList<Reservation_entite>UserReservation() {
        ArrayList<Reservation_entite> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/UserReservationJSON?user="+SessionManager.getUser().getId();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCours = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCours.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Reservation_entite re = new Reservation_entite();
                    float id = Float.parseFloat(obj.get("id").toString());
                    float nb = Float.parseFloat(obj.get("nbPlaces").toString());

                    // Parse the date string from the JSON response and set it on the Reservation object
                    
                    re.setId_reservation((int) id);
                    re.setNbr_place((int) nb);
                    String dateString = obj.get("date").toString();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = formatter.parse(dateString);
                    re.setDate(date);


                    result.add(re);
                }
                         //Date
                 //  String Date =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("2") ,obj.get("date").toString().lastIndexOf("T"));
                    
     
                 //  re.setDate(Date);
                     //Date
                  //  String DateConverter =  obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("2") ,obj.get("Date").toString().lastIndexOf("T"));
                    
     
                  //  re.setDateCreation(DateConverter);
                    
                        
//Date 
                       //String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("T"));
                        
                        
                       
                          //re.setDateCreation(DateConverter);
                        
                        //insert data into ArrayList result
                        
                       
                    
                    
                    
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
    public boolean deleteReservation (int id ) {
        String url = Statics.BASE_URL +"/DeleteReservationJSON/"+id;
        
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
    public boolean modifierReservation (Reservation_entite  t) {
        String url = Statics.BASE_URL +"/UpdateReservationJSON/"+t.getId_reservation()+"?&nbPlaces="+t.getNbr_place();
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