package com.uww.messaging.controller;

import com.uww.messaging.contract.TeamService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by horvste on 1/19/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "")
    public String index(Authentication authentication, Model model) {
        User currentUser = userService.userByAuthentication(authentication);
        model.addAttribute("user", currentUser);
        model.addAttribute("teams", teamService.findTeamsByUserId(currentUser.getUserId()));
        return "User/index";
    }
}
