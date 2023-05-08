/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.AjoutEvenementForm;
import com.mycompany.myapp.gui.AjoutReservationForm;
import com.mycompany.myapp.gui.ListEvenementForm;
import com.mycompany.myapp.gui.ListReservationForm;
import gui.statForm;

/**
 *
 * @author ASUS
 */
public class Home extends Form{
Form current;
private Resources theme;
    public Home(Form previous,Resources res) {
        current=this; //Back 
        add(new Label("Home"));
        setTitle(" --Home-- ");
        setLayout(BoxLayout.y());
        
    Button BUTAdd = new Button("Add Event");
    Button BUTShow = new Button("Show Evenet");
     Button BUTAddR = new Button("Add Reservation");
    Button BUTShowR = new Button("Show Reservation");
     Button BUTStatR = new Button("Statistique");
   
   BUTAdd.addActionListener((evt) -> new AjoutEvenementForm(current).show());
   
    BUTShow.addActionListener((evt) -> new ListEvenementForm(current).show());
    //    BUTAddR.addActionListener((evt) -> new AjoutReservationForm(current).show());
   
    BUTShowR.addActionListener((evt) -> new ListReservationForm(current).show());
    BUTStatR.addActionListener((evt) -> new statForm(current).show());
       
    addAll(BUTAdd,BUTShow,BUTAddR,BUTShowR,BUTStatR);
    
    
    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        previous.showBack();
        });}
    }
