package com.task1.demo.repo;

import com.task1.demo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String> {
    List<Product> findAllByUserId(String userId);

    void removeById(String id);
}
