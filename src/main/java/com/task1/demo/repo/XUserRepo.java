package com.task1.demo.repo;

import com.task1.demo.dto.XUserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface XUserRepo extends MongoRepository<XUserDTO, String> {
    Optional<XUserDTO> findByUsername(String username);
}

