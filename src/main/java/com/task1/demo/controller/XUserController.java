package com.task1.demo.controller;

import com.task1.demo.dto.XUserDTO;
import com.task1.demo.service.XUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Log4j2
@Controller
@RequestMapping("/")
public class XUserController {


    private final XUserService userService;

    public XUserController(XUserService userService) {
        this.userService = userService;

    }

    @GetMapping("login")
    public String loginPage() {
        log.info("Initial login page is being showed to user");
        return "login";
    }


    @GetMapping("landing")
    public String landingPage(Model model) {
        log.info("Landing page is started to show up");
        model.addAttribute("username",userService.loggedUser().getName());
        return "landing";
    }

    @GetMapping("register")
    public String registerPage() {
        log.info("Register page is starte to showing to user");
        return "register";
    }

    @PostMapping("register")
    public String registration(XUserDTO user) {

     log.info("User"+user+ " is registered");
        userService.addUserToDb(user);
        return "register";
    }

}
