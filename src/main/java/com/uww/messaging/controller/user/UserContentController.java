package com.uww.messaging.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 4/9/16
 *
 * @author reinaldo
 */
@Controller
@RequestMapping(value = "/user/content" )
public class UserContentController {

	@RequestMapping(value = "")
	public String index(){
		return "index";
	}

	@RequestMapping(value = "/wiki")
	public String wiki(){
		return "User/wiki";
	}
}
