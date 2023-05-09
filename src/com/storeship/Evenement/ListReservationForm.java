/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.storeship.Evenement;

import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.storeship.entities.Reservation_entite;
import com.storeship.services.ServiceReservation;

import java.util.ArrayList;

/**z
 *
 * @author bhk
 */
public class ListReservationForm extends Form {

    public ListReservationForm(Form previous) {
        setTitle("List Reservations");
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

           ArrayList<Reservation_entite>list = ServiceReservation.getInstance().affichageReservation();
           for(Reservation_entite a:list){
               TableModel model=new DefaultTableModel(new String[] {"nbPlaces","date"},new Object[][]{
                   {a.getNbr_place(),a.getDate()}
                       
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
            
            if(dig.show("Suppression","Vous voulez supprimer cette reservation ?","Oui","Annuler")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceReservation.getInstance().deleteReservation(a.getId_reservation())) {
                    new ListReservationForm(previous).show();
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
            new ModifierReservationForm(a,previous).show();
        });
       
        
        add(lModifier);
    }
    }
}

