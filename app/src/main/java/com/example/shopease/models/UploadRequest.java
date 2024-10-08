/*
 * File: UploadRequest.java
 * Description: This class represents the request model for uploading an image, encapsulating the image as a MultipartBody.Part.
 * Author: Senula Nanayakkara
 * Date: 2024/09/29
 */

package com.example.shopease.models;

import okhttp3.MultipartBody;

public class UploadRequest {

    private MultipartBody.Part image;

    // Constructor to initialize the image part
    public UploadRequest(MultipartBody.Part image) {
        this.image = image;
    }

    // Getter for image
    public MultipartBody.Part getImage() {
        return image;
    }

    // Setter for image
    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }
}
