package com.uww.messaging.controller.Content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 4/9/16
 *
 * @author reinaldo
 */

@Controller
@RequestMapping(value = "/content")
public class ContentController {

	@RequestMapping(value = "/wiki")
	public String home(){
		return "wiki";
	}
}
