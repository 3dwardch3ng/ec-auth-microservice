package sydney.cheng.microservice.auth.api.service;

import sydney.cheng.models.auth.dto.AuthToken;

public interface JwtService {
    String generateToken(String username);
    AuthToken validateToken(String token);
}
