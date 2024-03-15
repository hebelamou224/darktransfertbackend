package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.User;
import com.trustify.darktransfertdata.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @PostMapping("/add")
    public User save(User user) {
        return this.userService.save(user);
    }

    @GetMapping
    public Iterable<User> findAll() {
        return this.userService.findAll();
    }
}
