package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.enums.RequestType;
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
    public String users(ModelMap model) {
        List<User> users = usersService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "edit";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(required = false, name = "id") Long id,
                          @RequestParam("action") String action) {

        switch (RequestType.valueOf(action)) {
            case SAVE -> usersService.save(user);
            case UPDATE -> usersService.update(id, user);
            case DELETE -> usersService.delete(id);
        }

        return "redirect:/users";
    }
}
