package com.task1.demo.repo;

import com.task1.demo.dto.ProductDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<ProductDTO, String> {
    List<ProductDTO> findAllByUserId(String userId);

    void removeById(String id);
}
