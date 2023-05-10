
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
import java.util.List;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.storeship.Produit.AfficherProduit;
import com.storeship.Store.DetaileStoreForm;
import com.storeship.Store.NewStoreForm;
import com.storeship.entities.Categorie_store;
import com.storeship.entities.Store;
import com.storeship.gui.SessionManager;
import com.storeship.utils.Statics;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Plop
 */
public class ServiceStore {

    public ArrayList<Store> stores;
    public Store store;
    private ConnectionRequest req;
    public static ServiceStore instance = null;

    public static ServiceStore getInstance() {
        if (instance == null) {
            instance = new ServiceStore();
        }
        return instance;
    }

    private ServiceStore() {
        req = new ConnectionRequest();
    }

    public ArrayList<Store> getliststores(String jsontext) {
        try {
            stores = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> StoresListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) StoresListJson.get("root");
            System.out.println("Global List " + list);
            for (Map<String, Object> obj : list) {
                Store s = new Store();
                float id = Float.parseFloat(obj.get("id").toString());                                             
                System.out.println("id variable == " + id);
//                
             
                s.setNameSt(obj.get("nom").toString());
                s.setLocation(obj.get("location").toString());
                s.setPhoto(obj.get("photo").toString());
                s.setCategorie((Categorie_store) obj.get("Categorie"));
                // add more according to the GSON response
                s.setId((int) id);
                stores.add(s);
            }

        } catch (Exception e) {
    e.printStackTrace();
        }
        return stores;

    }
    
    
    public Store getidStore(String jsontext) {
                    Store store =new Store();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> StoresListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            System.out.println("Global List " + StoresListJson);
                float id = Float.parseFloat(StoresListJson.get("id").toString());
                store.setId((int) id);
        } catch (Exception e) {
            System.out.println("Error Inside the Service_Commande Line 94 \n" + e.getMessage());
        }
        return store;
    }
    
//    public User getidUser (String jsontext) {
//                    User user =new User();
//        try {
//            JSONParser j = new JSONParser();
//            Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
//            float id = Float.parseFloat(userListJson.get("id").toString());
//              user.setId((int) id);
//             } catch (Exception e) {
//                     System.out.println("Error Inside the Service_Commande Line 94 \n" + e.getMessage());
//             }
//        return user;
//    }

    public ArrayList<Store> getAllStore() {

        String url = Statics.BASE_URL + "/ListGsonStore";

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("Listening Correct");
                stores = getliststores(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("Retunring Stores +++++" + stores);
        return stores;
    }

    
// public void addStore(Store s) {
//    String nom = s.getNameSt();
//    String location = s.getLocation();
//    int user_id = 7;
//    String photo = s.getPhoto();
//    int Categorie = 6;
//
//    String url = Statics.URL_COMMUN + "newGsonStore?nom=" + nom + "&location=" + location + "&user=" + user_id + "&photo=" + photo + "&Categorie=" + Categorie;
//    System.out.println(url);
//    
//    req.setUrl(url);
//    req.setPost(true); // Changed to setPost(true)
//    req.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//            req.removeResponseListener(this);
//        }
//    });
//    NetworkManager.getInstance().addToQueueAndWait(req);
//}
public void addStore(Store s) {
    String nom = s.getNameSt();
    String location = s.getLocation();
    int user_id = 7;
    String photo = s.getPhoto();
    int Categorie = 6;

    String url = "http://127.0.0.1:8000/newGsonStore?nom=" + nom + "&location=" + location + "&user=" + SessionManager.getUser().getId() + "&photo=" + photo + "&Categorie=" + Categorie;;
    System.out.println(url);
    
    req.setUrl(url);
    req.setPost(true);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}



//    public void addDetail_Commande(Produit produit,int quantite,Commande c){
//        
//        String etatcommande = c.getEtat();
//        String destinationcommande =  c.getDestination();
//        Float prixcommande =c.getPrix();
//        String paymentcommande =c.getPayment();
//                Commande commande=new Commande();
//       //////////// ul 2 
//        String url = Statics.URL_COMMUN+ "gson/AddDetail?commande="+c.getId()+"&etat=Pending&quantite="+quantite+"&produit="+produit.getId()+"";
//        System.out.println(url);
//        //ConnectionRequest.setCertificateValidation(false);
//
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                Commande = getidCommande(new String(req.getResponseData())) ; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//
//        
//    
//    }
public Store getOneStore(int user_id) {
    String user=String.valueOf(SessionManager.getUser().getId());
    String url = Statics.BASE_URL + "/showJsonStore?user=" + user;
    System.out.println(url);

    req.setUrl(url);
    System.out.println("1");
    req.setPost(false);
    System.out.println("2");
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            System.out.println("3");
            if (req.getResponseCode()==200)
            {
                System.out.println("4");
                String response = new String(req.getResponseData());
                System.out.println("5");
            store = getStore(response); // Assign the result of getStore() to the store variable
                System.out.println("6");
            }
            else{
              store = null;  
            }
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return store;
}

public Store getStore(String jsontext) {
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> StoresListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) StoresListJson.get("root");
        System.out.println("Global List " + list);

        if (list != null && !list.isEmpty()) { // Check if the list is not empty
            Map<String, Object> obj = list.get(0); // Get the first store in the list

            Store store = new Store();
            float id = Float.parseFloat(obj.get("id").toString());
            System.out.println("id variable == " + id);

            try {
                // Handle any specific logic inside the try-catch block if needed
            } catch (Exception e) {
                e.printStackTrace();
            }

            store.setNameSt(obj.get("nom").toString());
            store.setLocation(obj.get("location").toString());
            store.setPhoto(obj.get("photo").toString());
            store.setCategorie((Categorie_store) obj.get("Categorie"));
            // add more according to the GSON response
            store.setId((int) id);

            return store;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null; // Return null if no store was found or an error occurred
} 
public void addRating(int rate,int store_id,int user_id) {
    String url = "http://127.0.0.1:8000/newGsonRating?rate=" + rate + "&store=" + store_id + "&user_id=" + user_id;
    System.out.println(url);
    
    req.setUrl(url);
    req.setPost(true);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            req.removeResponseListener(this);
            sendMail();
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}
private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

//private Slider createStarRankSlider() {
//    Slider starRank = new Slider();
//    starRank.setEditable(true);
//    starRank.setMinValue(0);
//    starRank.setMaxValue(10);
//    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
//            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
//    Style s = new Style(0xffff33, 0, fnt, (byte)0);
//    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
//    s.setOpacity(100);
//    s.setFgColor(0);
//    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
//    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
//    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
//    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
//    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
//    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
//    return starRank;
//}
 private Slider createStarRankSlider(int storeId, int userId) {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    
    starRank.addDataChangedListener((int oldValue, int newValue) -> {
        addRating(newValue, storeId, userId);
    });
    
    return starRank;
}

//public Container StoreinsideContainer(Store s, int userId) {
//    // ...
//
//    Button btn = new Button("Details");
//    btn.getAllStyles().setBgColor(0xFAA276);
//    btn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 14));
//    btn.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            actionButtonClicked(userId);
//        }
//    });
//
//    StoreContainer.add(BorderLayout.EAST, btn);
//    SecondContainer.add(FlowLayout.encloseCenter(createStarRankSlider(s.getId(), userId)));
//    StoreContainer.add(BorderLayout.CENTER, SecondContainer);
//    
//    return StoreContainer;
//}

    
    public Container StoreinsideContainer(Store s, int userId) {
    Container StoreContainer = new Container(new BorderLayout());
    StoreContainer.getAllStyles().setBorder(Border.createLineBorder(2, 0x000000));
    StoreContainer.getAllStyles().setMarginUnit(Style.UNIT_TYPE_DIPS);
    StoreContainer.getAllStyles().setMarginBottom(3);
    StoreContainer.getAllStyles().setBgColor(0xFAE1B2);

    Container SecondContainer = new Container(BoxLayout.y());
    // First Line in Container
    Container idnomContainer = new Container(BoxLayout.x());
    idnomContainer.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 14));
    idnomContainer.add(new Label("Id :"));
    idnomContainer.add(new Label(String.valueOf(s.getId())));
    idnomContainer.add(new Label(" Store Name :"));
    idnomContainer.add(new Label(s.getNameSt()));

    // Second Line in Container
    Container locationContainer = new Container(BoxLayout.y());
    locationContainer.add(new Label("Store Location : "));
    locationContainer.add(new Label(s.getLocation()));
    
//       locationContainer.add(new Label("Store Categorie : "));
//    locationContainer.add(new Label(s.getCategorie().getNom()));

    SecondContainer.add(idnomContainer);
    SecondContainer.add(locationContainer);

        Button btn = new Button("Details");
    btn.getAllStyles().setBgColor(0xFAA276);
    btn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 14));
    btn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            actionButtonClicked(userId);
        }
    });

    StoreContainer.add(BorderLayout.EAST, btn);
    SecondContainer.add(FlowLayout.encloseCenter(createStarRankSlider(s.getId(), userId)));
    StoreContainer.add(BorderLayout.CENTER, SecondContainer);
    
    return StoreContainer;
}



private void actionButtonClicked(int user_id) {
  
       new AfficherProduit().show();
   
        }
public void sendMail() {
        try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
             
            Session session = Session.getInstance(props,null); 
            
            
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("Creation nouveau store <melekbejaoui29@gmail.com>"));
            msg.setRecipients(Message.RecipientType.TO, "mohamedrayenjmili@gmail.com");
            msg.setSubject("Application nom  : Confirmation du ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
           String txt = "A new store has been created,please verify the partners products";
           
           
           msg.setText(txt);
           
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
            
          st.connect("smtp.gmail.com",465,"melekbejaoui29@gmail.com","aivwdyvhfopmgjyg");
           
          st.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+st.getLastServerResponse());
          
        }catch(Exception e ) {
            e.printStackTrace();
        }
    }


}

