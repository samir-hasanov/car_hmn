package www.hmn.az.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileService {

        private final MinioClient minioClient;

        public FileService(MinioClient minioClient) {
            this.minioClient = minioClient;
        }


    public void uploadLog(String bucketName, String fileName, String logContent) {
        try {
            InputStream inputStream = new ByteArrayInputStream(logContent.getBytes());
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(inputStream, logContent.length(), -1)
                            .contentType("application/json")
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("MinIO-ya fayl yüklənmədi: " + e.getMessage());
        }
    }
    public String uploadToMinio(MultipartFile file,String bucket) throws Exception {
        String bucketName = bucket;
        String objectName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        return "http://localhost:9000/" + bucketName + "/" + objectName;
    }

    public void deleteFileFromMinio(String fileUrl,String bucket) throws Exception {
        // Fayl URL-sini ayıraraq faylın adı əldə et
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

        String bucketName = bucket;  // Faylın saxlandığı bucket adı

        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)  // Faylın adı
                .build());
    }



}


