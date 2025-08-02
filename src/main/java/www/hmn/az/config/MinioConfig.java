package www.hmn.az.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient createMinioClient() {
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("ACCESS_KEY", "SECRET_KEY")
                .build();
    }
}
