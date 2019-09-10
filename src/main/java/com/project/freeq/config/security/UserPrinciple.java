package com.project.freeq.config.security;

import com.project.freeq.model.Partner;
import com.project.freeq.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class UserPrinciple implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = Stream.of
                (new SimpleGrantedAuthority(Role.USER.getAuthority()))
                .collect(Collectors.toList());

        return new UserPrinciple(
                user.getId(),
                user.getPhone(),
                user.getPassword(),
                authorities
        );
    }

    public static UserPrinciple build(Partner partner) {
        List<GrantedAuthority> authorities = Stream.of
                (new SimpleGrantedAuthority(Role.PARTNER.getAuthority()))
                .collect(Collectors.toList());

        return new UserPrinciple(
                partner.getId(),
                partner.getLogin(),
                partner.getPassword(),
                authorities
        );
    }

    public static UserPrinciple buildAdmin() {
        List<GrantedAuthority> authorities = Stream.of
                (new SimpleGrantedAuthority(Role.ADMIN.getAuthority()))
                .collect(Collectors.toList());

        return new UserPrinciple(
                Long.parseLong("-1"),
                "admin",
                "admin",
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

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
