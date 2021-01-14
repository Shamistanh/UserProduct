package com.task1.demo.controller;


import com.task1.demo.model.Product;
import com.task1.demo.service.ProductService;
import com.task1.demo.service.XUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/product")
public class ProductContrller {

    private final ProductService productService;
    private final XUserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    public ProductContrller(ProductService productService, XUserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    @GetMapping("/getAll")
    public String getAllProducts(Model model) {
        String userId = userService.loggedUser().getUserId();
        final List<Product> allByUserId = productService.findAllUsersById(userId);
        model.addAttribute("products", allByUserId);
        return "productTable";
    }

    @GetMapping("/add")
    public String getLanding() {
        return "/landing";
    }

    @PostMapping("/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("amount") int amount,
                      @RequestParam("price") float price,
                      Model model) {
        model.addAttribute("username", userService.loggedUser().getName());
        Product product = new Product(userService.loggedUser().getUserId(), name, amount, price, userService.loggedUser());
        productService.addProductToDB(product);
        return "landing";

    }

    @GetMapping("/removeById/{id}")
    public RedirectView remove(@PathVariable String id) {
        log.info("GET -> removeById: " + id);
        productService.deleteProductById(id);
        return new RedirectView("/product/getAll");
    }

}
