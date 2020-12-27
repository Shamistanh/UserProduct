package com.task1.demo.controller;


import com.task1.demo.model.Product;
import com.task1.demo.model.XUser;
import com.task1.demo.repo.ProductRepo;
import com.task1.demo.service.XUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/product")
public class ProductContrller {

    private final ProductRepo productRepo;
    private final XUserService userService;

    @Autowired
    MongoTemplate mongoTemplate;

    public ProductContrller(ProductRepo productRepo, XUserService userService) {
        this.productRepo = productRepo;
        this.userService = userService;
    }


    @GetMapping("/products")
    public String getAll(Model model){
        String userId= userService.logged_user().getUserId();
        final List<Product> allByUserId = productRepo.findAllByUserId(userId);
        model.addAttribute("products", allByUserId);
        return "productTable";
    }

    @GetMapping("/add")
    public String getLanding(){
        return "/landing";
    }

    @PostMapping("/add")
    public String add(@RequestParam("name")String name,
                    @RequestParam("amount")int amount,
                    @RequestParam("price")float price,
                      Model model){
        model.addAttribute("username",userService.logged_user().getName());
//        productRepo.save(Product.builder().name(name).id("2")
//                .amount(amount).price(price)
//                .xuser(userService.logged_user())
//                .userId(userService.logged_user()
//                        .getUserId()).build());
        Product product = new Product(userService.logged_user().getUserId(),name,amount,price,userService.logged_user());
        productRepo.save(product);
        return "landing";

    }

    @GetMapping("/removeById/{id}")
    public RedirectView  remove(@PathVariable String id){
        log.info("GET -> removeById: "+id);
        productRepo.removeById(id);
        return new RedirectView("/product/products");
    }

//    @PostMapping("/update_name")
//    public void update(int old_amount, String new_amount){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("amount").is(old_amount));
//        Update update = new Update();
//        update.set("amount", new_amount);
//        XUser user = mongoTemplate.findAndModify(query, update, XUser.class);
//
//    }
}
