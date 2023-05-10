/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.storeship.services;

/**
 *
 * @author Plop
 */
import com.google.gson.*;
import com.storeship.entities.Commande;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javaapplication1.GUI.Controllers.HolderpageController;
public class PaymentProcessor {

    private static final String STRIPE_SECRET_KEY = "sk_test_51Mf0S6FwJ7wXIwXewSc2z6FyXoFWAJZFy0Iuk4OZxzTVzLENEvBnnqug21baEIiV0MEDXTYl0y4Ajnp2LDWRZtC300mrwZe2j2";

    public Commande processPayment(String cardNumber, String expirationMonth, String expirationYear, String cvc, Commande commande) {
        boolean paymentstatus = false;
        String chargeId = "";
        try {
            // Set the API key
            Stripe.apiKey = STRIPE_SECRET_KEY;
            // Create a charge
            /*   HashMap<String, Object> cardParams = new HashMap<String, Object>();
            cardParams.put("object", "card");
            cardParams.put("number", cardNumber);
            cardParams.put("exp_month", expirationMonth);
            cardParams.put("exp_year", expirationYear);
            cardParams.put("cvc", cvc);
         
           HashMap<String, Object> chargeParams = new HashMap<String, Object>();
                chargeParams.put("amount", 2000);
                chargeParams.put("currency", "eur");
                chargeParams.put("description","My First Test Charge (created for API docs at https://www.stripe.com/docs/api)");
                chargeParams.put("source", cardParams);
             */
            Map<String, Object> customerParams = new HashMap<String, Object>();
            // integre melek
            //customerParams.put("email", commande.getUser().getEmail());
            customerParams.put("email", "User1@gmail.com");

            Customer payer = Customer.create(customerParams);

            Map<String, Object> retrieveParams = new HashMap<String, Object>();

            List<String> expandList = new ArrayList<String>();

            expandList.add("sources");

            retrieveParams.put("expand", expandList);

            Customer customer = Customer.retrieve(payer.getId(), retrieveParams, null); //add customer id here : it will start with cus_

            HashMap<String, Object> cardParam = new HashMap<String, Object>();
            cardParam.put("number", cardNumber);
            cardParam.put("exp_month", expirationMonth);
            cardParam.put("exp_year", expirationYear);
            cardParam.put("cvc", cvc);

            Map<String, Object> tokenParam = new HashMap<String, Object>();

            tokenParam.put("card", cardParam);

            Token token = Token.create(tokenParam); // create a token

            Map<String, Object> source = new HashMap<String, Object>();

            source.put("source", token.getId()); //add token as source

            Card card = (Card) customer.getSources().create(source); // add the customer details to which card is need to link

            String cardDetails = card.toJson();

            System.out.println("Card Details : " + cardDetails);

            customer = Customer.retrieve(payer.getId());//change the customer id or use to get customer by id.

            System.out.println("After adding card, customer details : " + customer);

//                PaymentMethod paymentMethod = PaymentMethod.create(cardParam);
            System.out.println(customer.getId());

            //Use the payment method to make a charge
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            int amount = (int) commande.getPrix() * 100;
            chargeParams.put("amount", amount);

            chargeParams.put("currency", "eur");

            //chargeParams.put("description", "Example charge");
            //chargeParams.put("source", token.getId());
            chargeParams.put("customer", customer.getId());

            Charge charge = Charge.create(chargeParams);

            if (charge.getPaid()) {
                chargeId = charge.getId();
            }
        } catch (StripeException e) {

            e.printStackTrace();
        }
        commande.setPayment(chargeId);

        return commande;
    }

}
