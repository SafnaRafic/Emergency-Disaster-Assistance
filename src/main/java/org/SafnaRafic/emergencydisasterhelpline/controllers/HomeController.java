package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/")
public class HomeController {
//    @RequestMapping("")
//    public String index(Model model) {
//        return "index";
//    }

    @GetMapping("login")
    public String login(){
        return "login";
    }


}
