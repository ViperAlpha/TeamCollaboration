package com.uww.messaging.controller;

import com.google.gson.Gson;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by horvste on 3/5/16.
 */
@Controller
@RequestMapping(value = "/user/invitation")
public class UserInvitationController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/autocomplete-user/list", method = RequestMethod.GET)
    @ResponseBody
    public String autocompleteUserList(@RequestParam("usernameToAuto") String usernameToAuto) {
        List<User> users = userService.findUsersStartingWith(usernameToAuto);
        List<String> usernames = new ArrayList<>();
        users.forEach(u -> {
           usernames.add(u.getUsername());
        });
        return new Gson().toJson(usernames);
    }
}
