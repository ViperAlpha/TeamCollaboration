package com.uww.messaging.controller.user;

import com.google.gson.Gson;
import com.uww.messaging.contract.UserService;
import com.uww.messaging.contract.WikiService;
import com.uww.messaging.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by horvste on 4/9/16.
 */
@Controller
@RequestMapping(value = "/user/content/api")
public class UserContentApiController {
    private WikiService wikiService;
    private UserService userService;

    @Autowired
    public UserContentApiController(WikiService wikiService, UserService userService) {
        this.wikiService = wikiService;
        this.userService = userService;
    }

    @RequestMapping(value = "/wiki", method = RequestMethod.GET)
    @ResponseBody
    public String wiki() {
        return new Gson().toJson(wikiService.findWikiOutputByTimeStampAsc());
    }

    @RequestMapping(value = "/wiki", method = RequestMethod.PUT)
    public ResponseEntity pushChange(Authentication authentication, @RequestBody Map<String, String> jsonMap) {
        String content = jsonMap.get("content");
        if (content == null)
            throw new RuntimeException("content parameter is required");
        int loggedInUserId = userService.getLoggedInUser(authentication).getUserId();
        wikiService.pushWiki(loggedInUserId, content);
        return new ResponseEntity(HttpStatus.OK);
    }
}