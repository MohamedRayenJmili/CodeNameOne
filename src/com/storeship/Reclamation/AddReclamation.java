package com.storeship.Reclamation;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.storeship.entities.Reclamation;
import com.storeship.entities.TypeReclamation;
import com.storeship.services.ServiceReclamation;
import com.storeship.services.TypeReclamationService;
import com.storeship.services.uploadImageToCloudinary;
import java.io.IOException;


import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class AddReclamation extends Form {
    private ArrayList<TypeReclamation> types;
String image;
String imageFilePath = ""; // Variable to store the selected image file path


    public AddReclamation() {
        super("Add Reclamation", new BorderLayout());

        // Create a container for the input components
        Container inputContainer = new Container(BoxLayout.y());

        // Create a text field with a placeholder
        TextField textField = new TextField("", "Saisir vote description");
                inputContainer.add(textField);

        // Create two radio buttons for selecting type
           Label typeLabel = new Label("Selectionnez le type de reclamation");
           inputContainer.add(typeLabel);
        types = TypeReclamationService.getInstance().getAllTypeReclamation();
        System.out.println(types);
        ButtonGroup buttonGroup = new ButtonGroup();
        for (TypeReclamation type : types) {
            RadioButton radioButton = new RadioButton(type.getTypeName());
            buttonGroup.add(radioButton);
            inputContainer.add(radioButton);
            
        }

        // Add the input components to the container

        // Create a container for the submit button
        Container buttonContainer = new Container(new FlowLayout(Component.CENTER));

        // Create a submit button
        Button submitButton = new Button("Submit");
        // Create a button for adding an image
Button addImageButton = new Button("Add Image");
addImageButton.addActionListener(e -> {
    // Show the file chooser dialog
    Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
        if (ev != null && ev.getSource() != null) {
            // Get the selected file path
            String filePath = (String) ev.getSource();
            imageFilePath = filePath;
            
            // Copy the selected image file to the resource directory
            try {
                String fileName = FileSystemStorage.getInstance().getAppHomePath() + "selected_image"+filePath.substring(filePath.lastIndexOf("/")+1)+".jpg";
                Util.copy(FileSystemStorage.getInstance().openInputStream(filePath), FileSystemStorage.getInstance().openOutputStream(fileName));
              
                // Create EncodedImage from the copied file path
                EncodedImage encodedImage = EncodedImage.create("selected_image"+filePath.substring(filePath.lastIndexOf("/")+1)+".jpg");
                
                // Use the encodedImage in your application as needed
                
                // For example, you can create a new Image component and add it to the container:
                Image image = encodedImage.scaled(getWidth(), getHeight());
                Label imageLabel=new Label();

                imageLabel = new Label(image);
                inputContainer.add(imageLabel);
                inputContainer.revalidate();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }, Display.GALLERY_IMAGE);
});

inputContainer.add(addImageButton);

        // Add an action listener to the submit button
       submitButton.addActionListener(e -> {
    // Retrieve the input data
    String contenu = textField.getText();
    
    // Check if any radio button is selected
    if (buttonGroup.isSelected()) {
        // Get the selected type
        TypeReclamation type = types.get(buttonGroup.getSelectedIndex());
        
        // Check if the input text has at least 3 words
        boolean hasEnoughWords = true;
        if (contenu.trim().isEmpty()) {
            hasEnoughWords = false;
        } else {
            int count = 0;
            for (int i = 0; i < contenu.length(); i++) {
                if (contenu.charAt(i) == ' ') {
                    count++;
                }
            }
            if (count < 2) {
                hasEnoughWords = false;
            }
        }

        if (hasEnoughWords) {
            // Create a new Reclamation object with the input data
            Reclamation reclamation = new Reclamation(0, 8, 0, "pending",image, new Date(), contenu, 0, 0, type);
            // Call the service to add the new reclamation
            boolean success = ServiceReclamation.getInstance().addReclamation(reclamation);
            if (success) {
                // Show a success message
                Dialog.show("Success", "Reclamation added successfully", "OK", null);
            } else {
                // Show an error message
                Dialog.show("Error", "Failed to add reclamation", "OK", null);
            }
        } else {
            // Show an error message
            Dialog.show("Error", "Please enter at least 3 words", "OK", null);
            // Add a hint to the text field
            textField.setHint("Enter 3 words");
        }
    } else {
        // Show an error message if no radio button is selected
        Dialog.show("Error", "Please select a type", "OK", null);
    }
});


        // Add the submit button to the button container
        buttonContainer.add(submitButton);

        // Add the input and button containers to the form
        

        add(BorderLayout.CENTER, inputContainer);
        add(BorderLayout.SOUTH, buttonContainer);
    }
}
