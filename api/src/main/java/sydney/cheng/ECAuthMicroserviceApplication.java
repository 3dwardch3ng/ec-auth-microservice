package sydney.cheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ECAuthMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECAuthMicroserviceApplication.class, args);
    }
}
