package com.mycompany.GUI;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.util.ArrayList;

public class AdminReclamation extends Form {

    private ServiceReclamation service = ServiceReclamation.getInstance();
    private ArrayList<Reclamation> reclamations = service.getAllReclamations();

    public AdminReclamation() {
        super("Reclamations", BoxLayout.y());
        if (reclamations != null && !reclamations.isEmpty()) {
            for (Reclamation r : reclamations) {
                addItem(r);
            }
        } else {
            add("No reclamations found.");
        }
    }

private void addItem(Reclamation r) {
    MultiButton mb = new MultiButton(Integer.toString(r.getIdReclamation()));
    mb.setTextLine2(r.getContenu());
    mb.setTextLine3("Etat: " + r.getEtat());
    

    Button acceptButton = new Button("Accepter");
    // define the action listener for the accept button
    ActionListener acceptListener = evt -> {
        r.setEtat("Accepted");
        if (service.updateReclamation(r)) {
            Dialog.show("Success", "Reclamation accepted.", "OK", null);
            mb.setTextLine3("Etat: " + r.getEtat());
        } else {
            Dialog.show("Error", "Failed to update reclamation.", "OK", null);
        }
        // close the dialog window
        ((Dialog) acceptButton.getComponentForm()).dispose();
    };
    acceptButton.addActionListener(acceptListener);

    Button refuseButton = new Button("Refuser");
    // define the action listener for the refuse button
    ActionListener refuseListener = evt -> {
        r.setEtat("Refused");
        if (service.updateReclamation(r)) {
            Dialog.show("Success", "Reclamation refused.", "OK", null);
            mb.setTextLine3("Etat: " + r.getEtat());
        } else {
            Dialog.show("Error", "Failed to update reclamation.", "OK", null);
        }
        // close the dialog window
        ((Dialog) refuseButton.getComponentForm()).dispose();
    };
    refuseButton.addActionListener(refuseListener);

    ActionListener detailsListener = evt -> {
        Dialog dialog = new Dialog("Reclamation Details");
        dialog.setLayout(BoxLayout.y());
        dialog.add(new MultiButton("Date: " + r.getDate()));
        dialog.add(new MultiButton("Contenu: " + r.getContenu()));
        dialog.add(new MultiButton("Etat: " + r.getEtat()));
        dialog.add(acceptButton);
        dialog.add(refuseButton);
        dialog.show();
    };
    mb.addActionListener(detailsListener);

    add(mb);
}

}
