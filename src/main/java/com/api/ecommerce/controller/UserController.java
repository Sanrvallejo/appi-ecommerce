package com.api.ecommerce.controller;

import com.api.ecommerce.entities.User;
import com.api.ecommerce.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

  private final Logger LOG = LoggerFactory.getLogger(UserController.class);
  @Autowired
  private UserServiceImpl userService;

  @GetMapping("/register")
  public String createUser(){
    return "user/register";
  }

  @PostMapping("/save-user")
  public String saveUser (User user) {
    LOG.info("User saved: {}", user);
    user.setTipo("USER");

    userService.save(user);
    return "redirect:/";
  }
}
