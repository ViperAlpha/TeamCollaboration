package com.uww.messaging.controller.admin;

import com.google.gson.Gson;
import com.uww.messaging.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by horvste on 4/6/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminUserController {
    private UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users")
    @ResponseBody
    public String getUserJson() {
        Gson gson = new Gson();
        return gson.toJson(userService.getAllUsers());
    }
}
