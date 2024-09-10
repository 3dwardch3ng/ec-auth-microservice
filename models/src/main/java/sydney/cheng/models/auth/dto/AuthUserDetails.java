package sydney.cheng.models.auth.dto;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sydney.cheng.microservice.models.user.dto.AuthUser;
import sydney.cheng.microservice.models.user.enums.Active;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class AuthUserDetails implements UserDetails {
    private final AuthUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(user.getRole())
                .map(x -> new SimpleGrantedAuthority("ROLE_" + x.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return Active.ACTIVE.equals(user.getActive());
    }

    @Override
    public boolean isAccountNonLocked() {
        return Active.ACTIVE.equals(user.getActive());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Active.ACTIVE.equals(user.getActive());
    }

    @Override
    public boolean isEnabled() {
        return Active.ACTIVE.equals(user.getActive());
    }
}
