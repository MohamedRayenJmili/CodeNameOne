/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Jmili
 */
public class EvenementForm extends BaseForm{

    public EvenementForm(Resources res) {
                super("Evenement", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
                tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        setTitle("Listes des Evenements");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
    }
    
}
