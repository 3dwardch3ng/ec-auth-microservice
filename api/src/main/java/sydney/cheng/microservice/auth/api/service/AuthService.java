package sydney.cheng.microservice.auth.api.service;

import sydney.cheng.models.auth.dto.AuthToken;
import sydney.cheng.models.auth.request.LoginRequest;

public interface AuthService {
    AuthToken login(LoginRequest request);
}
