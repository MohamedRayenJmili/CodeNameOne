/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.services.TypeReclamationService;
public class AddTypeReclamation extends Form {

    private TextField typeField;

    public AddTypeReclamation() {
        setTitle("Add Type Reclamation");

        Label typeLabel = new Label("Type:");
        typeField = new TextField();

        Button addButton = new Button("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String type = typeField.getText();
                if (type != null && !type.isEmpty()) {
                    TypeReclamationService.getInstance().addTypeReclamation(type);
                    // Clear the text field
                    typeField.setText("");
                }
            }
        });

        add(typeLabel);
        add(typeField);
        add(addButton);
    }
}
