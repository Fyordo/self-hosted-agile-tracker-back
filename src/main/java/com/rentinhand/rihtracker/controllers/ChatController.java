package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.UserAuth;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@UserAuth
public class ChatController {

    @GetMapping("/ura")
    public String getChatsList() {
        return "ura";
    }


}
