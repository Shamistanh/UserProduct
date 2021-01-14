package com.task1.demo.service;


import com.task1.demo.model.Product;
import com.task1.demo.model.XUser;
import com.task1.demo.repo.XUserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class XUserService {

    private final XUserRepo userRepo;

    public XUserService(XUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUserToDb(XUser user) {
        userRepo.save(user);
    }



    public XUser loggedUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();


        final Optional<XUser> currentUser = userRepo.findByUsername(username);
        if (currentUser.isPresent()) {
            return currentUser.get();
        } else {
            log.error("Logged user not found");
        }
        return null;

    }

    public List<Product> getMyProducts() {
        return loggedUser().getProducts();
    }
}
