package com.storeship.Reclamation;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.storeship.entities.Reclamation;
import com.storeship.services.ServiceReclamation;
import java.util.ArrayList;

public class ReclamationsForm extends Form {
    
    private GridLayout gridLayout;

    public ReclamationsForm() {
        super(new BorderLayout());
        
        // Retrieve the reclamations using the serviceReclamation class
        ArrayList<Reclamation> reclamations = ServiceReclamation.getInstance().getReclamationsById(8);
        
        // Check if the reclamations list is empty
        if (reclamations.isEmpty()) {
            // Display a message to the user
            System.out.println("no reclamations found");
        } else {
            // Create a container with a grid layout
            gridLayout = new GridLayout(reclamations.size(), 4);
            Container gridContainer = new Container(gridLayout);

            // Add the reclamations to the grid container
            for (Reclamation reclamation : reclamations) {
                gridContainer.add(new Label(String.valueOf(reclamation.getIdReclamation())));
                gridContainer.add(new Label(reclamation.getEtat()));
                gridContainer.add(new Label(reclamation.getDate().toString()));
                gridContainer.add(new Label(reclamation.getType().getTypeName()));
            }

            // Add the grid container to the form
            add(BorderLayout.CENTER, gridContainer);

        }
        
    }
    
}
