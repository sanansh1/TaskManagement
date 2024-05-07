package com.api.mvctaskmgmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Default Controller for handling CRUD operations of user
 */
@Controller
public class LoginController {

    @GetMapping(value = "/")
    public String login(ModelMap model){
        return "redirect:home";
    }

}
