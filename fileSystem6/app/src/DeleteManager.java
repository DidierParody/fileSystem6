package org.example;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;

public class DeleteManager extends AbstractObjectManager {

    @Override
    public void management() throws Exception {
        Blob blob = storage.get(bucketName, objectName);

        if (blob == null) {
            System.out.println("Object " + objectName + " was noy foud in bucket " + bucketName);
            return;
        }

        BlobId idWithGeneration = blob.getBlobId();
        storage.delete(idWithGeneration);

        System.out.println("✅ Object " + objectName + " permanently deleted from " + bucketName);
    }
}

