package com.task1.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "product")
public class Product {
    @Id
    String id;
    String userId;
    String name;
    int amount;
    float price;


    XUser xuser;



}
