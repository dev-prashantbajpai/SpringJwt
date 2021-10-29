package com.springjwt.springjwt.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/welcome")
    public String welcome()
    {
        String text = "This is a private page. ";
        text += "This page is not allowed to unauthenticated users";
        return text;
    }

    @GetMapping("/getusers")
    public String getUser()
    {
        return "{\"name\":\"Prashant\"}";
    }

}
