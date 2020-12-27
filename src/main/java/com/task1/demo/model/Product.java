package com.task1.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "product")
public class Product {
    @Id
    String id;
    String userId;
    String name;
    int amount;
    float price;

    public Product(String userId, String name, int amount, float price, XUser xuser) {
        this.userId = userId;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.xuser = xuser;
    }

    XUser xuser;

}
