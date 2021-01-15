package com.task1.demo.dto;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class XUserDTO {
    @Id
    String userId = System.currentTimeMillis()+"";

    String name;
    String surname;
    String username;
    String password;
    String roles;
    List<ProductDTO> products;
}
