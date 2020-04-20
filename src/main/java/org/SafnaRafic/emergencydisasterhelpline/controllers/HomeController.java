package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
    @RequestMapping("")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("admin")
    public String adminPage(){
        return "admin/index";
    }
}
