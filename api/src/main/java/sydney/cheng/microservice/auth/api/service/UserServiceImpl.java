package sydney.cheng.microservice.auth.api.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sydney.cheng.microservice.commons.exceptions.feign.FeignErrorException;
import sydney.cheng.microservice.feignclient.user.ECUserMicroserviceFeignClient;
import sydney.cheng.microservice.models.user.dto.AuthUser;
import sydney.cheng.models.auth.dto.AuthUserDetails;

import static sydney.cheng.microservice.auth.api.constant.ErrorMessages.ERROR_FEIGN_GET_AUTH_USER_DETAILS;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ECUserMicroserviceFeignClient userClient;

    @Override
    public AuthUserDetails getAuthUserDetailsByUsername(String username) {
        ResponseEntity<AuthUser> response = userClient.getAuthUserByUsername(username);
        if (HttpStatus.OK.equals(response.getStatusCode()) && response.hasBody()) {
            return new AuthUserDetails(response.getBody());
        }
        throw new FeignErrorException(ERROR_FEIGN_GET_AUTH_USER_DETAILS, (HttpStatus) response.getStatusCode());
    }
}
