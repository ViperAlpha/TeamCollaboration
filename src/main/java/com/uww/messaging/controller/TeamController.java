package com.uww.messaging.controller;

import com.uww.messaging.contract.TeamService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by horvste on 2/19/16.
 */
@RequestMapping(value = "user/team")
@Controller
public class TeamController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public String createTime(Authentication authentication, @RequestParam("teamName") String teamName, @RequestParam("teamDescription") String teamDescription) {
        User currentUser = userService.userByAuthentication(authentication);
        teamService.save(currentUser.getUserId(), teamName, teamDescription);
        return "redirect:/user";
    }
}
