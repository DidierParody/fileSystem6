package org.example;

public class GeneralManagement {

    private String bucketName;
    private String objectName;
    private String filePath;
    private String nameUser;
    private final String projectId = "projectId";


    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

  
    public void createBucket() throws Exception {
        CreateBucket manager = new CreateBucket();
        manager.setProjectId(projectId);
        manager.setBucketName("bucket-" + nameUser); 
        manager.management();
    }

  
    public void deleteBucket() throws Exception {
        DeleteBucket manager = new DeleteBucket();
        manager.setProjectId(projectId);
        manager.setBucketName(bucketName);
        manager.management();
    }


    public void uploadObject() throws Exception {
        UploadManager manager = new UploadManager();
        manager.setProjectId(projectId);
        manager.setBucketName(bucketName);
        manager.setObjectName(objectName);
        manager.setFilePath(filePath);
        manager.management();
    }


    public void deleteObject() throws Exception {
        DeleteManager manager = new DeleteManager();
        manager.setProjectId(projectId);
        manager.setBucketName(bucketName);
        manager.setObjectName(objectName);
        manager.management();
    }


    public void listObjects() throws Exception {
        ListObjectsManager manager = new ListObjectsManager();
        manager.setProjectId(projectId);
        manager.setBucketName(bucketName);
        manager.management();
    }


    public void listBuckets() throws Exception {
        ListBucketsManager manager = new ListBucketsManager();
        manager.setProjectId(projectId);
        manager.management();
    }
}
