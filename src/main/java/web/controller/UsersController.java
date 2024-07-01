package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UsersService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String showAllUsers(ModelMap model) {
        List<User> users = usersService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "user";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "edit";
    }

    @GetMapping("/new")
    public String showCreateUsersForm(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        usersService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        usersService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        usersService.delete(id);
        return "redirect:/users";
    }
}
