package com.task1.demo.service;


import com.task1.demo.model.Product;
import com.task1.demo.model.XUser;
import com.task1.demo.repo.XUserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XUserService {

    private final XUserRepo userRepo;

    public XUserService(XUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUserToDb(XUser user) {
        userRepo.save(user);
    }

    public List<Product> AllProductsOf(XUser user) {
        return user.getProducts();
    }

    public boolean validated(String username, String password) {
        if (userRepo.findByUsername(username).orElseThrow().getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public XUser logged_user() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        return userRepo.findByUsername(username).get();
    }

    public List<Product> getMyProducts() {
        return logged_user().getProducts();
    }
}
