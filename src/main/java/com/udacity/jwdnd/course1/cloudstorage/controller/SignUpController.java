package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/signup")
public class SignUpController {
    private SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }
    @GetMapping
    public String signUp() {
        return "signup";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User userModel, Model model) {

        int cnt = signUpService.create(userModel);
        if (cnt != 0) {
            model.addAttribute("success", "SignUp uccessfully!");
        } else {
            model.addAttribute("error", "SignUp failed");
        }
        return "redirect:/login";
    }
}
