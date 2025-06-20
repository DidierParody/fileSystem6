package org.example;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import java.nio.file.Paths;

public class UploadManager extends AbstractObjectManager {

    @Override
    public void management() throws Exception {
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        Storage.BlobWriteOption precondition;
        if (storage.get(bucketName, objectName) == null) {
            precondition = Storage.BlobWriteOption.doesNotExist();
        } else {
            precondition = Storage.BlobWriteOption.generationMatch(
                    storage.get(bucketName, objectName).getGeneration());
        }

        storage.createFrom(blobInfo, Paths.get(filePath), precondition);
        System.out.println("File" + filePath + " uploaded to " + bucketName + " as " + objectName);
    }
}

