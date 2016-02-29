package com.uww.messaging.controller;

import com.uww.messaging.contract.UserService;
import com.uww.messaging.factory.UserRoleFactory;
import com.uww.messaging.mapper.UserRegistrationMapper;
import com.uww.messaging.model.User;
import com.uww.messaging.display.UserRegistration;
import com.uww.messaging.validator.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by horvste on 1/18/16.
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public ModelAndView register(Principal principal) {
        if (principal == null) {
            ModelAndView modelAndView = new ModelAndView("register");
            UserRegistration userRegistration = new UserRegistration();
            modelAndView.addObject("userRegistration", userRegistration);
            return modelAndView;
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerUser(Principal principal,
                                     @ModelAttribute("userRegistration") UserRegistration userRegistration) {
        if (principal == null) {
            if (!UserRegistrationValidator.validate(userRegistration)) {
                throw new RuntimeException("Unable to register");
            }
            User user = UserRegistrationMapper.mapToUser(userRegistration);
            userService.save(user, UserRoleFactory.getWebsiteUserRole());
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("redirect:/");
    }
}
