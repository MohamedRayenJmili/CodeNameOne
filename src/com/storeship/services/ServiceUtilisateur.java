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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.storeship.entities.User;
import com.storeship.utils.Statics;
import com.storeship.gui.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Bejaoui
 */
public class ServiceUtilisateur {
    
    
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField nom,TextField prenom,TextField adresse,TextField age,TextField ville,TextField genre,
            TextField phone,TextField image,
            TextField password,TextField email,TextField confirmPassword, ComboBox<String> roles , Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/registerJson?email="+email.getText().toString()+"&password="+password.getText().toString()+"&roles="+roles.getSelectedItem().toString()
                +"&nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&adresse="+adresse.getText().toString()+
                "&phone="+phone.getText().toString()+"&genre="+genre.getText().toString()+"&ville="+ville.getText().toString()+
                        "&age="+age.getText().toString()+"&image="+image.getText().toString()
                ;
        
        System.out.println(url);
               
                
        
        req.setUrl(url);
       
        //Control saisi
        if(password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        req.addResponseListener((e)-> {
         
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    
    //SignIn
    
    public boolean signin(TextField email,TextField password, Resources rs ) {
        
        List<Boolean> connectedList = new ArrayList<>();
        connectedList.add(false);
        String url = Statics.BASE_URL+"/loginJson?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent e) {
                JSONParser j = new JSONParser();
                String json1 = new String(req.getResponseData()) + "";
                try {
                    if (!(e.getResponseCode()== 200)) {
                        Dialog.show("Echec d'authentification","email ou mot de passe éronné","OK",null);
                        
                    } else {
                        connectedList.set(0,true);
                        System.out.println("data ==" + json1);
                        Map<String,Object> user = j.parseJSON(new CharArrayReader(json1.toCharArray()));
                        System.out.println("user ==== "+user);
                        System.out.println("user email ===="+user.get("email"));
                      //   Set the user id and email in the session
                       // String email = (String)user.get("email");
                        User userinfo=new User();
                        userinfo.setId((int) Float.parseFloat(user.get("id").toString()));
                        userinfo.setRoles(String.valueOf(user.get("roles")));
                        userinfo.setEmail(String.valueOf(user.get("email")));
                        userinfo.setAge((int)Float.parseFloat(user.get("age").toString()));
                        userinfo.setNom(String.valueOf(user.get("nom")));
                        userinfo.setPrenom(String.valueOf(user.get("prenom")));
                        userinfo.setPhone(String.valueOf(user.get("phone")));
                                                userinfo.setImage(String.valueOf(user.get("phone")));
                                                userinfo.setAdresse(String.valueOf(user.get("adresse")));

                        SessionManager.setUser(userinfo);
                        
                     //   SessionManager.setEmail(email);
                    }
                }catch(IOException | NumberFormatException ex) {
                    ex.printStackTrace();
                }

            }
        });
    
        NetworkManager.getInstance().addToQueueAndWait(req);
        return connectedList.get(0);
    }
    

    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
    
    public boolean updateNomPrenomUser(User user){
                List<Boolean> connectedList = new ArrayList<>();
        connectedList.add(false);

          String url = Statics.BASE_URL+"/user/updateNomPrenom?id="+SessionManager.getUser().getId()+"&Nom="+SessionManager.getUser().getNom()+"&Prenom="+SessionManager.getUser().getPrenom();
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        req.addResponseListener((e) ->{
            JSONParser j = new JSONParser();
             json = new String(req.getResponseData()) + "";
            try {
              if (req.getResponseCode()!=200)
              {
                        Dialog.show("Erreur","Modification Error","OK",null);                  
              }


              else{
                  
                                          Dialog.show("Success","Modification Completed","OK",null);
                                          SessionManager.getUser().setPrenom(user.getPrenom());
                                          SessionManager.getUser().setNom(user.getNom());
                                                                  connectedList.set(0,true);

              }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
            return connectedList.get(0);

    }

}
