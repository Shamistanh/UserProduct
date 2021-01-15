package com.task1.demo.controller;


import com.task1.demo.dto.ProductDTO;
import com.task1.demo.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/products")
public class ProductContrller {

    private final ProductService productService;

    public ProductContrller(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/getAll")
    public String getAllProducts(Model model) {
        List<ProductDTO> allByUserId = productService.findAllProductsOfCurrentUser();
        model.addAttribute("products", allByUserId);

        return "productTable";
    }

    @GetMapping("/add")
    public String landingScreen() {
        return "/landing";
    }

    @PostMapping("/add")
    public String addProduct(ProductDTO productDTO,
                      Model model) {
        log.info(productDTO+ "added");
        productService.addProductToDB(productDTO, model);
        return "landing";

    }

    @GetMapping("/removeById/{id}")
    public RedirectView removeProductById(@PathVariable String id) {
        log.info("Product with id " + id+ " is removed");
        productService.deleteProductById(id);
        return new RedirectView("/products/getAll");
    }

}
