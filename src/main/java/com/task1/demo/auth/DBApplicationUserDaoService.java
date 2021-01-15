package com.task1.demo.auth;


import com.task1.demo.dto.XUserDTO;
import com.task1.demo.repo.XUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository("db")
public class DBApplicationUserDaoService implements ApplicationUserDao {

    private final XUserRepo xUserRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBApplicationUserDaoService(XUserRepo xUserRepo, PasswordEncoder passwordEncoder) {
        this.xUserRepo = xUserRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }


    private List<ApplicationUser> getApplicationUsers() {
        List<String> roles = xUserRepo.findAll().stream().map(XUserDTO::getRoles).collect(Collectors.toList());

        HashSet<GrantedAuthority> authorities = new HashSet<>(roles.size());
        for (String role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        List<ApplicationUser> applicationUsers =xUserRepo.findAll()
                .stream().map(e-> new ApplicationUser(e.getUsername(), passwordEncoder.encode(e.getPassword()), authorities,true,true,true,true )).collect(Collectors.toList());

        return applicationUsers;
    }

}