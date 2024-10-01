package com.example.shopease.models;

public class UploadResponse {
    private String fileUrl;
    private String blobName;

    // Getters and setters for fileUrl and blobName
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getBlobName() {
        return blobName;
    }

    public void setBlobName(String blobName) {
        this.blobName = blobName;
    }
}
