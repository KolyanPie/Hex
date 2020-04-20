package ru.edu.kolyanpie.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.kolyanpie.server.model.User;
import ru.edu.kolyanpie.server.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public boolean registration(@RequestBody User user) {
        return userService.addUser(user);
    }

}
