package com.task1.demo.controller;

import com.task1.demo.model.XUser;
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
        log.info("GET -> /login");
        return "login";
    }


    @GetMapping("landing")
    public String landingPage(Model model) {
        log.info("GET -> /landing");
        model.addAttribute("username",userService.loggedUser().getName());
        return "landing";
    }

    @GetMapping("register")
    public String registerPage() {
        log.info("GET -> /register");
        return "register";
    }
    @PostMapping("register")
    public String registration(@RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password
                                ) {


        final XUser user = XUser.builder().userId("1").name(name).surname(surname).username(username).password(password).build();
        log.info("POST -> register");
        userService.addUserToDb(user);
        return "register";
    }

}
