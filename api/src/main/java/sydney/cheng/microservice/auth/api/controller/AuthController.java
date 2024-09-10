package sydney.cheng.microservice.auth.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sydney.cheng.microservice.auth.api.service.AuthServiceImpl;
import sydney.cheng.microservice.auth.api.service.JwtService;
import sydney.cheng.models.auth.dto.AuthToken;
import sydney.cheng.models.auth.request.LoginRequest;

import static sydney.cheng.microservice.auth.api.constant.ErrorMessages.ERROR_AUTH_HEADER_NOT_FOUND;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthToken> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<AuthToken> validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (StringUtils.isBlank(token)) {
            log.warn(ERROR_AUTH_HEADER_NOT_FOUND);
            return ResponseEntity.ok(AuthToken.builder().valid(false).build());
        } else {
            if (token.startsWith("Bearer ")) {
                return ResponseEntity.ok(jwtService.validateToken(token.substring(7)));
            } else {
                return ResponseEntity.ok(AuthToken.builder().token(token).valid(false).build());
            }
        }
    }
}
