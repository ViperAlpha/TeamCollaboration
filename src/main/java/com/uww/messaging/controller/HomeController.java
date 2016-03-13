package com.uww.messaging.controller;

import com.uww.messaging.mapper.RoleMapper;
import com.uww.messaging.model.UserRole;
import com.uww.messaging.security.AuthenticationUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index(Principal principal) {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(Principal principal, Authentication authentication) {
        if (principal == null)
            return "login";
        UserRole userRole  = AuthenticationUtil.authenticationToRole(authentication);
        String redirect = RoleMapper.mapRoleToRoute(userRole.getAuthority());
        return "redirect:/" + redirect;
    }



}
