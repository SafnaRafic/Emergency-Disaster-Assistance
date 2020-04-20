package org.SafnaRafic.emergencydisasterhelpline.controllers;

import org.SafnaRafic.emergencydisasterhelpline.models.Contact;
import org.SafnaRafic.emergencydisasterhelpline.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("contact")
public class ContactController {

    @Autowired
    private MailService mailService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute(new Contact());
        return "contact/index";
    }
    @RequestMapping(value="send", method = RequestMethod.POST)
    public String send(@ModelAttribute("contact") @Valid Contact contact, Model model){
        try {
            String content = " Name : " + contact.getName();
            content += "<br> Email : " + contact.getEmail();
            content += "<br> Phone number : "+ contact.getPhone();
            content += "<br> Comments : "+ contact.getContent();

            mailService.send(contact.getEmail(), "ayisha4chat@gmail.com", contact.getSubject(), content);
            model.addAttribute("msg","Thanks for contacting us! We will be in touch with you shortly.");
        }catch (Exception e){
            model.addAttribute("msg",e.getMessage());
        }
        return "contact/index";
    }
}
