package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.model.User;
import com.trustify.darktransfertdata.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @PostMapping("/add")
    public User save(@RequestBody User user) {
        return this.userService.save(user);
    }

    @GetMapping()
    public User findUserByUsernameAndPassword(
            @RequestParam String username,
            @RequestParam String password
    ) {
        return this.userService.findUserByUsernameAndPassword(username, password);
    }

    @GetMapping("/")
    public Iterable<User> findAll() {
        return this.userService.findAll();
    }
}
