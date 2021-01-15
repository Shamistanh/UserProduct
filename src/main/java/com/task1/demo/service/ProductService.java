package com.task1.demo.service;

import com.task1.demo.dto.ProductDTO;
import com.task1.demo.repo.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final XUserService userService;

    public ProductService(ProductRepo productRepo, XUserService userService) {
        this.productRepo = productRepo;
        this.userService = userService;
    }

    public List<ProductDTO> findAllProductsById (String userId) {
        return productRepo.findAllByUserId(userId);
    }

    public void addProductToDB(ProductDTO productDTO, Model model) {
        model.addAttribute("username", userService.loggedUser().getName());
        productRepo.save(productDTO);
    }

    public void deleteProductById(String id) {
        productRepo.removeById(id);
    }

    public List<ProductDTO> findAllProductsOfCurrentUser() {
        String currentUserId = userService.loggedUser().getUserId();
        return findAllProductsById(currentUserId);
    }
}
