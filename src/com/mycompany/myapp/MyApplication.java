package com.mycompany.myapp;

import com.codename1.ui.*;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.GUI.AddReclamation;
import com.mycompany.GUI.AddTypeReclamation;
import com.mycompany.GUI.AdminReclamation;
import com.mycompany.GUI.ReclamationStat;
import com.mycompany.GUI.ReclamationsForm;
public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);
        // Pro only feature
        // Log.bindCrashProtection(true);
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }

      //new AddReclamation().show();
       //new AdminReclamation().show();
      //new AddTypeReclamation().show();
      //new ReclamationsForm().show();
      new ReclamationStat().show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }

}
