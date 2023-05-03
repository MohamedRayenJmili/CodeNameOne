
package com.mycompany.myapp;

import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;


public class ActivateForm extends BaseForm {

    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Toolbar");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("smily.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );
        
        TextField code = new TextField("", "Enter Code", 20, TextField.PASSWORD);
        code.setSingleLineTextArea(false);
        
        Button signUp = new Button("Sign Up");
        Button resend = new Button("Resend");
        resend.setUIID("CenterLink");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(code),
                createLineSeparator(),
                new SpanLabel("We've sent the confirmation code to your email. Please check your inbox", "CenterLabel"),
                resend,
                signUp,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signUp.requestFocus();
        signUp.addActionListener(e -> new ListStoresForm(res).show());
    }
    
}
