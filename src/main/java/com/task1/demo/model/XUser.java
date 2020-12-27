package com.task1.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "user")
public class XUser implements Serializable {
    @Id
    String userId;

    String name;
    String surname;
    String username;
    String password;
    String roles;
    List<Product> products;
}
