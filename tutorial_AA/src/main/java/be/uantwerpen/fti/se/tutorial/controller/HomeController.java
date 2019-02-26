package be.uantwerpen.fti.se.tutorial.controller;

import be.uantwerpen.fti.se.tutorial.model.User;
import be.uantwerpen.fti.se.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @ModelAttribute("allUsers")
    public Iterable<User> populateUsers() {
        return this.userService.findAll();
    }

    @RequestMapping({"/","/home"})
    @PreAuthorize("hasAuthority('logon')")
    public String showHomepage(){
        return "homepage";
    }
}

