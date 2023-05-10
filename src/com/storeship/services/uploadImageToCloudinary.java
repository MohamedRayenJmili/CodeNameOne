/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.storeship.services;
   import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import java.io.IOException;

/**
 *
 * @author Plop
 */
public class uploadImageToCloudinary {
    
 
public void uploadImageToCloudinary(String imagePath) {
    // Cloudinary account credentials
    String cloudName = "djkojmpgl";
    String apiKey = "319991599758181";
    String apiSecret = "DZnOmLGLmRjyAHWrn-N1zGf3NJ4";

    // Cloudinary upload URL
    String uploadUrl = "https://api.cloudinary.com/v1_1/" + cloudName + "/image/upload";

    // Create the multipart request
    MultipartRequest request = new MultipartRequest();
    request.setUrl(uploadUrl);

    // Set the Cloudinary account credentials as request headers
    request.setPost(true);
    request.addRequestHeader("X-Requested-With", "CodenameOne");
    request.addRequestHeader("Authorization", "Basic " + com.codename1.util.Base64.encodeNoNewline((apiKey + ":" + apiSecret).getBytes()));

    // Add the image file to the request
    try {
        request.addData("file", imagePath, "image/jpeg");
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }

    // Perform the request
    NetworkManager.getInstance().addToQueueAndWait(request);

    // Handle the response
    if (request.getResponseCode() == 200) {
        System.out.println("Pathing Request from CLoudinary"+ request.getResponseCode());
       
                
        
    } else {
        String response = new String(request.getResponseData());
        System.out.println("Error: " + response);
    }
}

}
