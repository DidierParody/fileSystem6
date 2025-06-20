package org.example;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.InputStream;

public abstract class AbstractBucketManager {

    protected String projectId = "projectId";
    protected String bucketName;
    protected Storage storage;

    public AbstractBucketManager() {
        try {
            initializeStorage();
        } catch (Exception e) {
            throw new RuntimeException("error initializing credentials: " + e.getMessage());
        }
    }

    public void initializeStorage() throws Exception {
        // Carga del archivo credentials.json desde src/main/resources
        //InputStream sirve para leer datos
        InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("credentials.json");

        if (credentialsStream == null) {
            throw new RuntimeException("The credentials.json file was not found in resources.");
        }

        // Inicializa el cliente de Google Cloud Storage
        this.storage = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(ServiceAccountCredentials.fromStream(credentialsStream))
                .build()
                .getService();

    }

    // Setters y Getters
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public Storage getStorage() {
        return storage;
    }

   
    public abstract void management() throws Exception;
}
