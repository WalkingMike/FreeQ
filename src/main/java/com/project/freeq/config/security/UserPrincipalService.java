package com.project.freeq.config.security;

import com.project.freeq.model.Partner;
import com.project.freeq.model.User;
import com.project.freeq.service.PartnerService;
import com.project.freeq.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserPrincipalDetailsService")
@AllArgsConstructor
public class UserPrincipalService implements UserDetailsService {
    @Autowired
    private final UserService userService;

    @Autowired
    private final PartnerService partnerService;

    private static String adminLogin = "adminLog";

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(adminLogin);
        User user = userService.getUserByPhone(s);
        if (user != null) {
            return UserPrincipal.build(user);
        }
        Partner partner = partnerService.getPartnerByLogin(s);
        if (partner != null) {
            return UserPrincipal.build(partner);
        }
        if (s.equals(adminLogin)) {
            return UserPrincipal.build();
        }
        return null;
    }
}
