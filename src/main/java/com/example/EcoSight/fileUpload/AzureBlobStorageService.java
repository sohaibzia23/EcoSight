package com.example.EcoSight.fileUpload;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.PublicAccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Stream;

@Primary
@RequiredArgsConstructor
@Service
public class AzureBlobStorageService implements StorageService {
    private final BlobServiceClient blobServiceClient;
    private final String containerName;

    @Autowired
    public AzureBlobStorageService(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${azure.storage.container-name}") String containerName) {
        this.blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        this.containerName = containerName;
    }

    @Override
    public void init() {
        // Create container if it doesn't exist
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        if (!containerClient.exists()) {
            containerClient.create();
            // Set container access policy to allow public access to blobs
            containerClient.setAccessPolicy(PublicAccessType.BLOB, new ArrayList<>());
        }
    }

    @Override
    public void store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }

        try {
            String blobName = String.format("sightings/%s-%s",
                    UUID.randomUUID().toString(),
                    file.getOriginalFilename());

            BlobClient blobClient = blobServiceClient
                    .getBlobContainerClient(containerName)
                    .getBlobClient(blobName);

            blobClient.upload(file.getInputStream(), file.getSize(), true);
        } catch (IOException e) {
            throw new StorageException("Failed to store file in Azure.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        // Return list of blob URLs instead of paths
        return blobServiceClient
                .getBlobContainerClient(containerName)
                .listBlobs()
                .stream()
                .map(blobItem -> Paths.get(blobItem.getName()));
    }

    @Override
    public Path load(String filename) {
        // Return the blob URL instead of a path
        String blobUrl = blobServiceClient
                .getBlobContainerClient(containerName)
                .getBlobClient(filename)
                .getBlobUrl();
        return Paths.get(blobUrl);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            BlobClient blobClient = blobServiceClient
                    .getBlobContainerClient(containerName)
                    .getBlobClient(filename);

            if (blobClient.exists()) {
                return new UrlResource(blobClient.getBlobUrl());
            } else {
                throw new StorageFileNotFoundException("File not found: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("File not found: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        // Be careful with this one - maybe add additional safety checks
        blobServiceClient
                .getBlobContainerClient(containerName)
                .listBlobs()
                .forEach(blobItem ->
                        blobServiceClient
                                .getBlobContainerClient(containerName)
                                .getBlobClient(blobItem.getName())
                                .delete());
    }

    // Additional method for getting URL directly
    public String getFileUrl(String filename) {
        return blobServiceClient
                .getBlobContainerClient(containerName)
                .getBlobClient(filename)
                .getBlobUrl();
    }
}
