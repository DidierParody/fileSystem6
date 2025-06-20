package org.example;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;

public class ListObjectsManager extends AbstractObjectManager {

    @Override
    public void management() throws Exception {
        if (bucketName == null || bucketName.isEmpty()) {
            System.out.println("The bucket name was not specified.");
            return;
        }

        Page<Blob> blobs = storage.list(bucketName);

        System.out.println("Objetos en el bucket \"" + bucketName + "\":");
        boolean empty = true;

        for (Blob blob : blobs.iterateAll()) {
            System.out.println("🔹 " + blob.getName());
            empty = false;
        }

        if (empty) {
            System.out.println("The bucket is empty.");
        }
    }
}

