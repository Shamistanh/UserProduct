package com.task1.demo.model;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Document(collection = "user")
public class XUser implements Serializable {
    @Id
    String userId;

    String name;
    String surname;
    String username;
    String password;
    transient String roles;
    transient List<Product> products;
}
