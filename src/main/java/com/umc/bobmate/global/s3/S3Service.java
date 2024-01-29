package com.umc.bobmate.global.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.umc.bobmate.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static com.umc.bobmate.global.apiPayload.code.status.ErrorStatus.*;

@Service
@Component
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, DirName dirName) {
        try {
            File uploadFile = convert(multipartFile);
            return upload(uploadFile, dirName);
        } catch (IOException e) {
            throw new GeneralException(IO_EXCEPTION);
        }
    }

    private String upload(File uploadFile, DirName dirName) {
        String fileName = dirName.getDirName() + "/" + UUID.randomUUID() + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        deleteFile(uploadFile);
        return uploadImageUrl;
    }

    private File convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        convertFile.createNewFile();

        final FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    private String putS3(File uploadFile, String fileName) {
        try {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3Client.getUrl(bucket, fileName).toString();
        } catch (AmazonS3Exception e) {
            System.out.println(e);
            throw new GeneralException(S3_UPLOAD_ERROR);
        }
    }

    private void deleteFile(File targetFile) {
        try {
            targetFile.delete();
        } catch (AmazonServiceException e) {
            throw new GeneralException(DELETE_FILE_ERROR);
        }
    }

    public void deleteFileFromS3(String fileUrl) {
        try {
            String splitStr = ".com/";
            String fileName = fileUrl.substring(fileUrl.lastIndexOf(splitStr) + splitStr.length());
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
        } catch (AmazonServiceException e) {
            throw new GeneralException(DELETE_FILE_ERROR);
        }
    }
}
