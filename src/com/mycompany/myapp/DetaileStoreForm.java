/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Jmili
 */
public class DetaileStoreForm extends BaseForm {

    public DetaileStoreForm() {
                super("Detaile Store", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
                tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        //Form previous = Display.getInstance().getCurrent();
        //tb.setBackCommand("", e -> previous.showBack());
        setTitle("Detaile d'un store");
        tb.addSearchCommand(e -> {});
    }
    
    
}
