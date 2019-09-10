package com.project.freeq.config.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER, PARTNER, ADMIN;

    @Override
    public String getAuthority()
    {
        return name();
    }
}
