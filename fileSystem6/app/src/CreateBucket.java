package org.example;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.StorageClass;

public class CreateBucket extends AbstractBucketManager {

    @Override
    public void management() throws Exception {
        StorageClass storageClass = StorageClass.COLDLINE; //tipo de almacenamiento
        String location = "ASIA";

        Bucket bucket = storage.create(
                BucketInfo.newBuilder(bucketName)
                        .setStorageClass(storageClass)
                        .setLocation(location)
                        .build());

        System.out.println("Bucket created: " + bucket.getName() + " in " + bucket.getLocation() +
                " with storage class " + bucket.getStorageClass());
    }
}
