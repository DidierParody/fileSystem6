package org.example;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.InputStream;

public abstract class AbstractObjectManager {

    protected String projectId = "projectId";
    protected String bucketName;
    protected String objectName;
    protected String filePath;
    protected Storage storage;

    public AbstractObjectManager() {
        try {
            initializeStorage();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing credentials: " + e.getMessage());
        }
    }

    public void initializeStorage() throws Exception {
        InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("credentials.json");

        if (credentialsStream == null) {
            throw new RuntimeException("The credentials.js file was not found in resources");
        }

        this.storage = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(ServiceAccountCredentials.fromStream(credentialsStream))
                .build()
                .getService();
    }

    // Getters y Setters
    public String getProjectId() {
        return projectId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Storage getStorage() {
        return storage;
    }

    public abstract void management() throws Exception;
}
