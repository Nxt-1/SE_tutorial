package be.uantwerpen.fti.se.tutorial.controller;

import be.uantwerpen.fti.se.tutorial.model.User;
import be.uantwerpen.fti.se.tutorial.service.RoleService;
import be.uantwerpen.fti.se.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(final ModelMap model) {
        model.addAttribute("allUsers",
                userService.findAll());
        return "users-list";
    }

    @RequestMapping(value = "/users/put", method = RequestMethod.GET)
    public String viewCreateUser(final ModelMap model) {
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("user", new User("", ""));
        return "users-manage";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String viewEditUser(@PathVariable Long id, final ModelMap model) {
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("user", userService.findById(id).orElse(null));
        return "users-manage";
    }

    @RequestMapping(value = {"/users/", "/users/{id}"},
            method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result,
                          final ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("allRoles", roleService.findAll());
            return "users-manage";
        }
        userService.saveSomeAttributes(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, final ModelMap
            model) {
        userService.delete(id);
        model.clear();
        return "redirect:/users";
    }
}

