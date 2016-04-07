package com.uww.messaging.controller.admin;

import com.google.gson.Gson;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.display.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    public ResponseEntity deleteUser(@RequestBody Map<String, String> requestMap) {
        try {
            Integer userId = Integer.parseInt(requestMap.get("userId"));
            if (!userService.exists(userId))
                return new ResponseEntity(HttpStatus.NOT_FOUND);

            userService.delete(userId);

            return new ResponseEntity(HttpStatus.OK);
        } catch (NumberFormatException numberFormatException) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
