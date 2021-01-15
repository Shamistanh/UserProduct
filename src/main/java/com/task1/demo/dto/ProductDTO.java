package com.task1.demo.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProductDTO {

    @Id
    String id = System.currentTimeMillis() + "";
    String userId;
    String name;
    int amount;
    float price;
}
