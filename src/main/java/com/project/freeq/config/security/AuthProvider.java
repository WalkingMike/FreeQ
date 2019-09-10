package com.project.freeq.config.security;

import com.project.freeq.model.Partner;
import com.project.freeq.model.User;
import com.project.freeq.service.PartnerService;
import com.project.freeq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AuthProvider implements AuthenticationProvider
{
    @Autowired
    private UserService userService;

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();


        User user = userService.getUserByPhone(username);

        if(user != null && user.getPhone().equals(username))
        {
            if(!passwordEncoder.matches(password, user.getPassword()))
            {
                throw new BadCredentialsException("Wrong password");
            }

            Collection<? extends GrantedAuthority> authorities = Stream.of
                    (new SimpleGrantedAuthority(Role.USER.getAuthority()))
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        Partner partner = partnerService.getPartnerByPhone(username);
        if(partner != null && partner.getPhone().equals(username))
        {
            if(!passwordEncoder.matches(password, partner.getPassword()))
            {
                throw new BadCredentialsException("Wrong password");
            }

            Collection<? extends GrantedAuthority> authorities = Stream.of
                    (new SimpleGrantedAuthority(Role.PARTNER.getAuthority()))
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(partner, password, authorities);
        }
        if(username.equals("admin"))
        {
            if(!password.equals("admin"))
            {
                throw new BadCredentialsException("Wrong password");
            }

            Collection<? extends GrantedAuthority> authorities = Stream.of
                    (new SimpleGrantedAuthority(Role.ADMIN.getAuthority()))
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(partner, password, authorities);
        }

        throw new BadCredentialsException("Username not found");
    }

    public boolean supports(Class<?> arg)
    {
        return true;
    }
}
