package com.uww.messaging.controller.admin;

import com.uww.messaging.contract.UserService;
import com.uww.messaging.factory.UserRoleFactory;
import com.uww.messaging.model.user.User;
import com.uww.messaging.model.user.UserRole;
import com.uww.messaging.security.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by horvste on 1/19/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    public String index(Model model, Authentication authentication) {
        UserRole userRole = AuthenticationUtil.authenticationToRole(authentication);
        User currentUser = userService.findUserById(userRole.getUserId());
        model.addAttribute("user", currentUser);
        model.addAttribute("userRole", userRole);
        return "Admin/index";
    }

    @RequestMapping(value = "/deleteAllUsers")
    public String deleteAllUsers() {
        userService.deleteAll(UserRoleFactory.USER);
        return "Admin/index";
    }
}
