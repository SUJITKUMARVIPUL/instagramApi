package com.sujit.Instagram.controller;

import com.sujit.Instagram.model.User;
import com.sujit.Instagram.model.dto.SignInDto;
import com.sujit.Instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("user/signup")
    public String userSignUp(@RequestBody User user){
        return userService.userSignUp(user);
    }

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String token)
    {
        return userService.userSignOut(email,token);
    }

    @PostMapping("user/signIn")
    public String userSignIN(@RequestBody SignInDto signIn){
        return userService.userSignIn(signIn);
    }
}
