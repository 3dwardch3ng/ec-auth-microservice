package sydney.cheng.microservice.auth.api.service;

import sydney.cheng.models.auth.dto.AuthUserDetails;

public interface UserService {
    AuthUserDetails getAuthUserDetailsByUsername(String username);
}
