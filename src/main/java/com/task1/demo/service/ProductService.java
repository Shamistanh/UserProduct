package com.task1.demo.service;

import com.task1.demo.model.Product;
import com.task1.demo.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> findAllUsersById(String userId) {
        return productRepo.findAllByUserId(userId);
    }

    public void addProductToDB(Product product) {
        productRepo.save(product);
    }

    public void deleteProductById(String id) {
        productRepo.removeById(id);
    }
}
