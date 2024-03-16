package com.motherbrain.enhancer;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

public class GoogleCloudStorageReader {
  private String projectId;
  private String bucketName;

  public void readFromGCS(String bucketName, String objectName) {
    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    Bucket bucket = storage.get(bucketName);
    Blob blob = bucket.get(objectName);

    try (ReadableByteChannel readerChannel = blob.reader()) {
      // Read data from the channel and print it to the console
      Channels.newInputStream(readerChannel).transferTo(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void fetchFilesFromBucket(String bucketName) {
    // Get a list of all object names in the specified bucket
    List<String> objectNames = getObjects(bucketName);

    // Iterate through the object names and fetch the data for each object
    for (String objectName : objectNames) {
      readFromGCS(bucketName, objectName);
    }
  }

  private List<String> getObjects(String bucketName) {
    // This method should fetch a list of all object names in the specified bucket
    // You can implement this method using Google Cloud Storage client library or any other
    // mechanism
    // For simplicity, let's assume a dummy implementation that returns a list of sample object
    // names
    return List.of("object1.txt", "object2.txt", "object3.txt");
  }
}
