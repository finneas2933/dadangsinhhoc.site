package site.dadangsinhhoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// API for UI (vue.js)
@Controller
public class UIController {
    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}