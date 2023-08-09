package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/notes")
public class NoteController {
    private NoteService noteService;
    private UserService userService;
    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String createNote(@ModelAttribute("notes") NoteModel noteModel, Model model, Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUserByUserNm(userName);
        noteModel.setUserId(user.getUserId());

        int cnt = noteService.save(noteModel);
        if(cnt != 0) {
            model.addAttribute("success", "Note were saved successfully .");
        } else {
            model.addAttribute("error", "Note save failed");
        }
        return "result";
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam Integer  noteId, Model model) {

        int cnt = 0;
        cnt = noteService.deleteNote(noteId);
        if (cnt != 0) {
            model.addAttribute("success", "Note were delete successfully .");
        } else {
            model.addAttribute("error", "Note delete failed");
        }
        return "result";
    }
}
