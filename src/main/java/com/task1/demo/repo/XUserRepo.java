package com.task1.demo.repo;

import com.task1.demo.model.XUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface XUserRepo extends MongoRepository<XUser, String> {
    Optional<XUser> findByUsername(String username);
}
