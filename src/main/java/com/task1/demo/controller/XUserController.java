package com.task1.demo.controller;

import com.task1.demo.model.Product;
import com.task1.demo.model.XUser;
import com.task1.demo.repo.XUserRepo;
import com.task1.demo.service.XUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;


@Log4j2
@Controller
@RequestMapping("/")
public class XUserController {


    private final XUserService userService;
    private final XUserRepo userRepo;

    public XUserController(XUserService userService, XUserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping("login")
    public String handle_get1() {
        log.info("GET -> /login");
        Product product = new Product();

        return "login";
    }
//    @PostMapping("login")
//    public String handle_post(@RequestParam("username")String username, @RequestParam("password")String password) {
//
//        log.info("POST -> /login "+username+" "+password);
//        if (userService.validated(username,password)){
//           return "landing";
//        }
//        return "login";
//    }


    @GetMapping("landing")
    public String handle_get_landing(Model model) {
        log.info("GET -> /landing");
        model.addAttribute("username",userService.logged_user().getName());
        return "landing";
    }

    @GetMapping("register")
    public String register_get() {
        log.info("GET -> /register");
        return "register";
    }
    @PostMapping("register")
    public String register_post(@RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password
                                ) {


        final XUser user = XUser.builder().userId("1").name(name).surname(surname).username(username).password(password).build();
        log.info("POST -> register");
        userRepo.save(user);
        return "register";
    }

}
