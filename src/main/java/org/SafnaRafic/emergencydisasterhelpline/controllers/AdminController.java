package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("admin")
@Controller
public class AdminController {
    @GetMapping("index")
    public String adminIndex(){
        return "admin/index";}
}
