package org.superbiz.moviefun.BlobStore;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.aspectj.util.FileUtil;
import org.superbiz.moviefun.BlobStore.Blob;

import java.io.*;
import java.util.Optional;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.apache.tika.Tika;


public class FileStore implements BlobStore{

    private final AmazonS3Client s3Client;
    private final String bucketName;
    private final Tika tika = new Tika();

    public  FileStore(AmazonS3Client s3client, String s3BucketName)
    {
        this.s3Client = s3client;
        this.bucketName = s3BucketName;

    }

    public void put(Blob blob) throws IOException
    {

        InputStream stream = blob.inputStream;
        //byte[] buffer = new byte[stream.available()];
        //stream.read(buffer);

//        File targetFile = new File(blob.name);
//        OutputStream outStream = new FileOutputStream(targetFile);
//        outStream.write(buffer);
        s3Client.putObject(bucketName,blob.name, stream, new ObjectMetadata());


    }

    public Optional<Blob> get(String name) throws IOException
    {
        try (S3Object object = s3Client.getObject(bucketName,name)) {

        S3ObjectInputStream content = object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(content);
        return Optional.of(new Blob(name, new ByteArrayInputStream(bytes), tika.detect(bytes)));
        }

    }



}


