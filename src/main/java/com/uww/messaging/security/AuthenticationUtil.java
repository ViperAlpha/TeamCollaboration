package com.uww.messaging.security;

import com.uww.messaging.model.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by horvste on 1/19/16.
 */
public class AuthenticationUtil {
    public static UserRole authenticationToRole(Authentication authentication) {
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        Object[] authorityArr = grantedAuthorities.toArray();
        if (authorityArr.length > 1) {
            throw new RuntimeException("We only permit one role at this time");
        }
        Object authority = authorityArr[0];
        if (!(authority instanceof UserRole)) {
            throw new RuntimeException("We only permit UserRole class as GrantedAuthority at this time");
        }
        return (UserRole) authority;
    }
}
