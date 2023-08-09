package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/credentials")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping
    public String save(@ModelAttribute("credentials") CredentialModel credentials, Model model, Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUserByUserNm(userName);
        credentials.setUserId(user.getUserId());
        int cnt = credentialService.save(credentials);
        if(cnt != 0) {
            model.addAttribute("success", "Credential were saved successfully .");
        } else {
            model.addAttribute("error", "Credential save failed");
        }
        return "result";
    }

    @GetMapping("/delete")
    public String deleteCredential(@RequestParam Integer credentialId, Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userService.getUserByUserNm(userName);
        int cnt = credentialService.deleteCredential(credentialId, user.getUserId());
        if(cnt != 0) {
            model.addAttribute("success", "Credential were delete successfully .");
        } else {
            model.addAttribute("error", "Credential delete failed");
        }
        return "result";
    }
}
