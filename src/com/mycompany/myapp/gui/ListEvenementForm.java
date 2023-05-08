/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.mycompany.myapp.utils.Statics;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;

import java.util.ArrayList;

/**z
 *
 * @author bhk
 */
public class ListEvenementForm extends Form {
Form current;

      
    public ListEvenementForm(Form previous) {
        
        setTitle("List Evenements");
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

           ArrayList<Evenement>list = ServiceEvenement.getInstance().affichageEvenement();
           for(Evenement a:list){
               //  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
             // Image i=(URLImage.createToStorage(placeholder,a.getImageFile(),Statics.BASE_URL+"/uploads/"));
                 TableModel model=new DefaultTableModel(new String[]
              
               
              {"nbMax","lieuEv","DescEv","titreEv","date_debut","date_fin","imageEv","type","reservations"},new Object[][]{
                  
                   {a.getNbMax(),a.getLieuEV(),a.getDescEv(),a.getTitreEv(),a.getDatedebut(),a.getDatefin(),a.getImageFile(),a.getType(),a.getReservations()}
                       
               }){
                   public boolean isCellEditable(int row,int col){
                    return col!=0;   
               }
                       };
               Table tab=new Table (model);
               add(tab);
                  
           //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cette Commande ?","Oui","Annuler")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceEvenement.getInstance().deleteEvenement(a.getId())) {
                    new ListEvenementForm(previous).show();
                }
           
        });
        add(lSupprimer);

    
    
    //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new ModifierEvenementForm(a,previous).show();
        });
       
        
        add(lModifier);
       //Resr
       
       Button bb=new Button("reserver");
       bb.addActionListener(e->{
       new AjoutReservationForm(this,a).show();
           
       
       });
       
      add(bb);
    }
      
    }
    
       
}

