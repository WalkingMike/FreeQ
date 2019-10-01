package com.project.freeq.config.security;

import com.project.freeq.model.Partner;
import com.project.freeq.model.User;
import com.project.freeq.service.PartnerService;
import com.project.freeq.service.TicketService;
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
    private final TicketService ticketService;

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

    public Boolean isSame(UserPrincipal user1, Long user2Id){
        UserPrincipal user2 = UserPrincipal.build(userService.getUserByID(user2Id));
        return user1.equals(user2);
    }

    public Boolean isSameWithTicket(UserPrincipal user1, Long ticketId){
        Long user2Id = ticketService.getOneById(ticketId).getUserId();
        return isSame(user1, user2Id);
    }

    //TODO implement
    public Boolean isSameWithService(UserPrincipal user1, Long ticketId){
        return false;
    }

    //TODO implement
    public Boolean isSameWithQueue(UserPrincipal user1, Long ticketId){
        return false;
    }

    //TODO implement
    public Boolean isSameWithBranch(UserPrincipal user1, Long ticketId){
        return false;
    }

}
