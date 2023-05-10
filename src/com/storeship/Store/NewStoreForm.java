/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.storeship.Store;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.storeship.entities.Categorie_store;
import com.storeship.entities.Store;
import com.storeship.gui.BaseForm;
import com.storeship.gui.SessionManager;
import com.storeship.services.ServiceStore;


/**
 *
 * @author Jmili
 */
public class NewStoreForm extends BaseForm{
    private TextField nameField;
    private TextField locationField;
    private TextField ownerField;
    private TextField photoField;
    private TextField categoryField;
    private Button saveButton;
    public NewStoreForm(Resources res) {
        
                super("New Store", new FlowLayout());
        Toolbar tb = new Toolbar(false);
        setToolbar(tb);
        tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        setTitle("Create a new Store");
        getContentPane().setScrollVisible(false);
              Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        tb.addSearchCommand(e -> {});
// Create the form fields
        nameField = new TextField();
        locationField = new TextField();
        ownerField = new TextField();
        photoField = new TextField();
        categoryField = new TextField();
        saveButton = new Button("Save");
        
        // Set the layout of the form
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        // Add form labels and fields

        add(new Label("Name:"));
        add(nameField);
        add(new Label("Location:"));
        add(locationField);
//        add(new Label("Owner:"));
//        add(ownerField);
        add(new Label("Photo:"));
        add(photoField);
        add(new Label("Category:"));
        add(categoryField);
        
        // Add save button
        add(saveButton);
        
        // Set the command behavior for the save button
        saveButton.addActionListener(e -> {
            // Retrieve the values from the form fields
            String nameSt = nameField.getText();
            String location = locationField.getText();
//            String owner = ownerField.getText();
            String photo = photoField.getText();
//            float rating = Float.parseFloat(ratingField.getText());
            String category = categoryField.getText();
            
            // Create a new instance of the Store entity with the retrieved values
            Store store = new Store();
//            store.setId(id);
            store.setNameSt(nameSt);
            store.setLocation(location);
//            store.setOwner(owner);
            store.setPhoto(photo);
            store.setCategorie(new Categorie_store(category));
            ServiceStore.getInstance().addStore(store);
            // Save the store entity or perform any other desired operations
            if (ServiceStore.getInstance().getOneStore(SessionManager.getUser().getId()) != null)
                new DetaileStoreForm(ServiceStore.getInstance().getOneStore(SessionManager.getUser().getId()).getId()).show();
            // Clear the form fields
            nameField.clear();
            locationField.clear();
            ownerField.clear();
            photoField.clear();
            categoryField.clear();
        });
    }
}
//
//import com.codename1.ui.*;
//import com.codename1.ui.layouts.*;
//import com.codename1.ui.spinner.*;
//import com.codename1.ui.util.Resources;
//
//public class StoreForm extends Form {
//
//    private TextField nameField;
//    private TextField locationField;
//    private ComboBox<User> ownerComboBox;
//    private TextField photoField;
//    private Spinner ratingSpinner;
//    private ComboBox<Categorie_store> categorieComboBox;
//    private Button saveButton;
//
//    public StoreForm(Resources theme) {
//        super("Edit Store");
//        setLayout(new BorderLayout());
//
//        // Create input fields
//        nameField = new TextField();
//        locationField = new TextField();
//        ownerComboBox = new ComboBox<>();
//        photoField = new TextField();
//        ratingSpinner = new Spinner();
//        categorieComboBox = new ComboBox<>();
//        saveButton = new Button("Save");
//
//        // Populate owner combo box with users
//        ownerComboBox.setModel(new DefaultListModel<>(getAllUsers()));
//
//        // Populate category combo box with categories
//        categorieComboBox.setModel(new DefaultListModel<>(getAllCategories()));
//
//        // Set initial values for input fields
//        nameField.setText("");
//        locationField.setText("");
//        ownerComboBox.setSelectedIndex(0);
//        photoField.setText("");
//        ratingSpinner.setValue(0.0f);
//        categorieComboBox.setSelectedIndex(0);
//
//        // Add input fields to form
//        Container inputContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//        inputContainer.addComponent(new Label("Name"));
//        inputContainer.addComponent(nameField);
//        inputContainer.addComponent(new Label("Location"));
//        inputContainer.addComponent(locationField);
//        inputContainer.addComponent(new Label("Owner"));
//        inputContainer.addComponent(ownerComboBox);
//        inputContainer.addComponent(new Label("Photo"));
//        inputContainer.addComponent(photoField);
//        inputContainer.addComponent(new Label("Rating"));
//        inputContainer.addComponent(ratingSpinner);
//        inputContainer.addComponent(new Label("Category"));
//        inputContainer.addComponent(categorieComboBox);
//        add(BorderLayout.CENTER, inputContainer);
//
//        // Add save button to form
//        add(BorderLayout.SOUTH, saveButton);
//    }
//
//    public void setStore(Store store) {
//        // Set input fields with values from store object
//        nameField.setText(store.getNameSt());
//        locationField.setText(store.getLocation());
//        ownerComboBox.setSelectedItem(store.getOwner());
//        photoField.setText(store.getPhoto());
//        ratingSpinner.setValue(store.getRating());
//        categorieComboBox.setSelectedItem(store.getCategorie());
//    }
//
//    public Store getStore() {
//        // Create a new store object with values from input fields
//        Store store = new Store();
//        store.setNameSt(nameField.getText());
//        store.setLocation(locationField.getText());
//        store.setOwner(ownerComboBox.getSelectedItem());
//        store.setPhoto(photoField.getText());
//        store.setRating((Float) ratingSpinner.getValue());
//        store.setCategorie(categorieComboBox.getSelectedItem());
//        return store;
//    }
//
//    private User[] getAllUsers() {
//        // TODO: Implement method to fetch all users from database
//        return new User[] {new User(), new User(), new User()};
//    }
//
//    private Categorie_store[] getAllCategories() {
//        // TODO: Implement method to fetch all categories from database
//        return new Categorie_store[] {new Categorie_store(), new Categorie_store(), new Categorie_store()};
//    }
//
//}
