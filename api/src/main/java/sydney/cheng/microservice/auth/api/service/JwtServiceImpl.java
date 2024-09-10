package sydney.cheng.microservice.auth.api.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sydney.cheng.models.auth.dto.AuthToken;
import sydney.cheng.models.auth.dto.AuthUserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private static String SECRET;
    @Value("${jwt.expire}")
    private static long tokenExpireMillis;

    private final UserService userService;

    @Override
    public String generateToken(String username) {
        AuthUserDetails userDetails = userService.getAuthUserDetailsByUsername(username);
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    @Override
    public AuthToken validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
            return AuthToken.builder().token(token).valid(false).build();
        }
        return AuthToken.builder().token(token).valid(true).build();
    }

    private String createToken(Map<String, Object> claims, AuthUserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuer(userDetails.getAuthorities().iterator().next().getAuthority())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenExpireMillis))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
