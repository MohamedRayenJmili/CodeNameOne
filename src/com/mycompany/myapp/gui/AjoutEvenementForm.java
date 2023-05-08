/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ext.filechooser.FileChooser;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import com.codename1.io.FileSystemStorage;



/**
 *
 * @author bhk
 */
public class AjoutEvenementForm extends Form{

    public AjoutEvenementForm(Form previous) {
        setTitle("Add a new Evenement");
        setLayout(BoxLayout.y());
        
        TextField nom = new TextField("","Lieu");
        TextField adress= new TextField("", "Description");
        TextField surface= new TextField("", "Titre");
         TextField nb= new TextField("", "nbMax");
                               Button photoButton = new Button("Ajouter une Image");
        photoButton.setTextPosition(Label.BOTTOM);
        
        CheckBox multiSelect = new CheckBox("Multi-select");
    
   photoButton.addActionListener((ActionEvent e)->{
  if(FileChooser.isAvailable()){
    
     FileChooser.setOpenFilesInPlace(true);
   
     FileChooser.showOpenDialog(multiSelect.isSelected(),".jpg,.jpeg,.png/plain",(ActionEvent e2)->{
        if(e2==null || e2.getSource()==null){
            add("No file was selected");
            revalidate();
            return;
        }
        if(multiSelect.isSelected()){
          String[] paths=(String[]) e2.getSource();
          for (String path:paths){
            System.out.println(path);
            CN.execute(path);
          }
          return;
        }
         //menna 7atta el photoButton.setBadgeText(namePic); 9a3din ntal3ou fi esm taswira el 7a9ani 
         String file=(String) e2.getSource();
         
          System.out.println(file);
          String extension=null;
          if(file.lastIndexOf(".")>0) {
            extension = file.substring(file.lastIndexOf(".")+1);
            StringBuilder hi=new StringBuilder(file);
            
            if(file.startsWith("file://")){
            } else {
                hi.delete(0,7);
               }
            int lastIndexPeriod = hi.toString().lastIndexOf(".");
            Log.p(hi.toString());
            String ext = hi.toString().substring(lastIndexPeriod);
            String hmore = hi.toString().substring(0,lastIndexPeriod-1);
            try{
             String namePic = saveFileToDevice(file,ext);
             System.out.println(namePic);
              //cr.setFilename("file",namePic);//any unique name you want
            // photoButton.getIcon().setImageName(namePic);
              photoButton.setBadgeText(namePic); //7atit l'esm fl badge ta3 button bch najjam nesta3mlou el berra ml cha9lalla hadhi
            }catch(IOException ex){
            }
          }  
        if(file == null){
           add("No file was selected");
           revalidate();
        }else{
           Image logo;
           try{
             logo = Image.createImage(file).scaledSmallerRatio(256, 256);
             photoButton.setIcon(logo); //lenna bch tatla3lk taswira 9bal ma ta3ml submit
             //lenna nbdaw fl enregistrement ta3 taswira
             String imageFile=FileSystemStorage.getInstance().getAppHomePath()+photoButton.getBadgeText();
             try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)){
               //System.out.println(imageFile);
               ImageIO.getImageIO().save(logo,os,ImageIO.FORMAT_PNG,1);//3mlna save lel image fi wost file system storage
               System.out.println();
             }catch(IOException err){
             }
           }catch(IOException ex){
           }
             revalidate();
          
        }
     });
  }
  });
        Button btnValider = new Button("Add Evenement");
        Button btnAfficher = new Button("Afficher Evenement");
        btnAfficher.addActionListener((evt) -> {
            new ListEvenementForm(previous).show();
        });
       btnValider.addActionListener((e) -> {
            
            
            try {
                
                if(surface.getText().equals("") || adress.getText().equals("")|| nom.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    //njibo iduser men session (current user)
                    Evenement a = new Evenement(     Integer.parseInt(nb.getText()),String.valueOf(nom.getText()),
                                  String.valueOf(adress.getText()),
                                  String.valueOf(surface.getText()));
                    
                    System.out.println("data  Commande == "+a);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceEvenement.getInstance().ajoutEvenement(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListCommandeForm
                    new ListEvenementForm(previous).show();
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        addAll(nom,adress,surface,nb,btnValider,photoButton,btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
  protected String saveFileToDevice(String hi,String ext) throws IOException {
     ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest();
 URI uri;
 try{
   uri= new URI(hi);
   String path=uri.getPath();
   int index = hi.lastIndexOf("/");
   hi=hi.substring(index + 1);
   return hi ;
 }catch (URISyntaxException ex){
 }
 return "null";
}
  
}
