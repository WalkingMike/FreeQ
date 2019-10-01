package com.project.freeq.config.security;

import com.project.freeq.model.Partner;
import com.project.freeq.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private static String adminLogin = "adminLog";

    private static String adminPassword = "adminPass";

    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal build(User user) {
        List<GrantedAuthority> authorities = Stream.of
                (new SimpleGrantedAuthority(Role.USER.getAuthority()))
                .collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getPhone(),
                user.getPassword(),
                authorities
        );
    }

    public static UserPrincipal build(Partner partner) {
        List<GrantedAuthority> authorities = Stream.of
                (new SimpleGrantedAuthority(Role.PARTNER.getAuthority()))
                .collect(Collectors.toList());

        return new UserPrincipal(
                partner.getId(),
                partner.getLogin(),
                partner.getPassword(),
                authorities
        );
    }

    public static UserPrincipal build() {
        List<GrantedAuthority> authorities = Stream.of
                (new SimpleGrantedAuthority(Role.ADMIN.getAuthority()))
                .collect(Collectors.toList());

        return new UserPrincipal(
                Long.parseLong("-1"),
                adminLogin,
                (new BCryptPasswordEncoder(16)).encode(adminPassword),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }
}
