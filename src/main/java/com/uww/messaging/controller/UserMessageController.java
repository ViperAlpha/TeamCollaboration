package com.uww.messaging.controller;

import com.google.gson.Gson;
import com.uww.messaging.model.TeamMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by horvste on 3/1/16.
 */
@RequestMapping(value = "/user/message")
public class UserMessageController {
    @RequestMapping(value = "/team-messages/list") //add parameters
    @ResponseBody
    public String listTeamMessages() {
        //should return a json list of team messages depending on GET parameter
        throw new RuntimeException("not implemeneted");
        //Gson gson = new Gson();
        //String json = gson.toJson(new Object());
        return "";
    }

    @RequestMapping(value = "/team-messages/insert") //add parameters
    @ResponseBody
    public String insertTeamMessage() {
        //should insert based on PUT
        //TeamMessage
        throw new RuntimeException("not implemeneted");
        //Gson gson = new Gson();
        //String json = gson.toJson(new Object());
        return "";
    }

    @RequestMapping(value="/individual-message/list")
    @ResponseBody
    public String listIndividualMessages(){
        //should return a json list of individaul messages depending on GET parameter
        throw new RuntimeException("not implemeneted");
        return "";
    }
    @RequestMapping(value="/individual-message/insert")
    @ResponseBody
    public String insertIndivdualMessage(){
        //should insert based on PUT
        throw new RuntimeException("not implemeneted");
        return "";
    }



}
