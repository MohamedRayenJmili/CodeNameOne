/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.storeship.Evenement;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;

    public HomeForm() {
        current = this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.yCenter());

        Container ctn = new Container(new FlowLayout(CENTER, CENTER));

        Label lb = new Label("Bienvenue dans notre Application ");
        ctn.add(lb);
        current.add(ctn);
        getToolbar().addCommandToSideMenu("home", null, ((evt) -> {
        }));
        getToolbar().addCommandToSideMenu("Gestion des Events ", null, (e) -> {
          //  new AjoutCommandeForm(current).show();

        });


    }

}
