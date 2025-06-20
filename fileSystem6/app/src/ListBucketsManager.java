package org.example;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Bucket;

public class ListBucketsManager extends AbstractBucketManager {

    @Override
    public void management() throws Exception {
        if (storage == null) {
            System.out.println("The Storage client could not be initialized.");
            return;
        }

        Page<Bucket> buckets = storage.list();

        System.out.println("Buckets in the project \"" + projectId + "\":");
        boolean empty = true;

        for (Bucket bucket : buckets.iterateAll()) {
            System.out.println("🔹 " + bucket.getName());
            empty = false;
        }

        if (empty) {
            System.out.println("There are no buckets in this project.");
        }
    }
}
