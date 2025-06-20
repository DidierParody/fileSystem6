package org.example;

import com.google.cloud.storage.Bucket;

public class DeleteBucket extends AbstractBucketManager {

    @Override
    public void management() throws Exception {
        Bucket bucket = storage.get(bucketName);

        if (bucket == null) {
            System.out.println("The Bucket " + bucketName + " not foud.");
            return;
        }

        bucket.delete();
        System.out.println("Bucket removed: " + bucket.getName());
    }
}


