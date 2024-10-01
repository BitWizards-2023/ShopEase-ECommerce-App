package com.example.shopease.models;

import okhttp3.MultipartBody;

public class UploadRequest {

    private MultipartBody.Part image;

    public UploadRequest(MultipartBody.Part image) {
        this.image = image;
    }

    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }
}
