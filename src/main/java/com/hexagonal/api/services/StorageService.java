package com.hexagonal.api.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class StorageService {
    private static String bucket;
    private static long urlExpiration;
    private static String key;
    private static String secret;
    private static String region;
    private final AmazonS3 amazonS3;

    @Autowired
    public StorageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public static URL get(String filename) {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(key, secret);
        AmazonS3 aws = AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        Date expiration = new Date();
        long expTimeMillis = Instant.now().toEpochMilli();
        expTimeMillis += 1000 * 60 * urlExpiration;
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, filename)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        return aws.generatePresignedUrl(generatePresignedUrlRequest);
    }

    @Value("${storage.aws.s3.bucket}")
    public void setBucket(String bucket) {
        StorageService.bucket = bucket;
    }

    @Value("${storage.aws.s3.url.expiration}")
    public void setBucket(long expiration) {
        StorageService.urlExpiration = expiration;
    }

    @Value("${storage.aws.s3.access.key.id}")
    public void setKey(String key) {
        StorageService.key = key;
    }

    @Value("${storage.aws.s3.secret.access.key}")
    public void setSecret(String secret) {
        StorageService.secret = secret;
    }

    @Value("${storage.aws.s3.default.region}")
    public void setRegion(String region) {
        StorageService.region = region;
    }

    public String save(String directory, MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        String path = bucket + "/" + directory;
        String fileName = file.getOriginalFilename();

        try {
            amazonS3.putObject(path, fileName, file.getInputStream(), metadata);
        } catch (AmazonServiceException | IOException e) {
            throw new RuntimeException("Could not save the file");
        }

        return directory + "/" + fileName;
    }

    public String save(String directory, MultipartFile file, String fileName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        String path = bucket + "/" + directory;
        String extension = this.getExtensionByStringHandling(file.getOriginalFilename()).orElseThrow(() -> new RuntimeException("Invalid file extension"));
        String filePath = fileName + "." + extension;

        try {
            amazonS3.putObject(path, filePath, file.getInputStream(), metadata);
        } catch (AmazonServiceException | IOException e) {
            throw new RuntimeException("Could not save the file");
        }

        return directory + "/" + filePath;
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
