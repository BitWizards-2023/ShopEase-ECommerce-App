/*
 * File: UploadResponse.java
 * Description: This class represents the response model for an upload request, containing the file URL and the blob name of the uploaded file.
 * Author: Senula Nanayakkara
 * Date: 2024/09/29
 */

package com.example.shopease.models;

public class UploadResponse {

    private String fileUrl;
    private String blobName;

    // Getter for fileUrl
    public String getFileUrl() {
        return fileUrl;
    }

    // Setter for fileUrl
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    // Getter for blobName
    public String getBlobName() {
        return blobName;
    }

    // Setter for blobName
    public void setBlobName(String blobName) {
        this.blobName = blobName;
    }
}
