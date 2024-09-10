package sydney.cheng.microservice.client.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import sydney.cheng.models.auth.dto.AuthToken;
import sydney.cheng.models.auth.request.LoginRequest;

@FeignClient(name = "ec-auth-microservice", path = "/v1/auth")
public interface ECAuthMicroserviceFeignClient {
    @PostMapping("/login")
    ResponseEntity<AuthToken> login(@RequestBody LoginRequest request);
    @GetMapping("/validate")
    ResponseEntity<AuthToken> validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
