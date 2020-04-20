package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AdminController {
    @GetMapping("admin")
    public String adminPage(){
        return "admin/index";
    }

    @GetMapping("confirm")
    public String confirmataion(){
        return "admin/confirm";
    }

    @GetMapping("about")
    public String about(){
        return "admin/about";
    }
}
