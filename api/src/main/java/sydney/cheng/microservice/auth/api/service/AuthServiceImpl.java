package sydney.cheng.microservice.auth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import sydney.cheng.microservice.commons.exceptions.auth.WrongCredentialsException;
import sydney.cheng.models.auth.dto.AuthToken;
import sydney.cheng.models.auth.request.LoginRequest;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;

    @Override
    public AuthToken login(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authenticate.isAuthenticated()) {
            return AuthToken.builder()
                    .token(jwtService.generateToken(request.getUsername()))
                    .valid(true)
                    .build();
        }
        throw new WrongCredentialsException("Wrong credentials");
    }
}
