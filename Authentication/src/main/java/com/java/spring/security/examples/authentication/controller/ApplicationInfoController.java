package com.java.spring.security.examples.authentication.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController(value = "applicationInfoController")
@RequestMapping(path = "info")
public class ApplicationInfoController {

    @GetMapping(path = "/public/ping")
    public String ping(){
        return "Ok public !";
    }

    @GetMapping(path = "/guest/ping")
    public String usersPing(){
        return "Ok users and admins!";
    }

    @GetMapping(path = "/admin/ping")
    public String adminPing(){
        return "Ok admins !";
    }
    @GetMapping("/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
}
