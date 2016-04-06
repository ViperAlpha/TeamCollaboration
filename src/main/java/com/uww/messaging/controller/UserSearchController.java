package com.uww.messaging.controller;

import com.google.gson.Gson;
import com.uww.messaging.contract.TeamService;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.UserDisplay;
import com.uww.messaging.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by horvste on 4/6/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserSearchController {
    private UserService userService;
    private TeamService teamService;

    @Autowired
    public UserSearchController(UserService userService, TeamService teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    /**
     * For the autocomplete feature.
     *
     * @param authentication
     * @param query
     * @return
     */
    @RequestMapping(value = "/search")
    @ResponseBody
    public String search(Authentication authentication, @RequestParam("q") String query) {
        User loggedInUser = userService.userByAuthentication(authentication);
        List<UserDisplay> userNamesLackingInvites =
                userService.findUsersLackingInvitationsStartingWith(loggedInUser.getUserId(), query);
        return new Gson().toJson(userNamesLackingInvites);
    }
}
