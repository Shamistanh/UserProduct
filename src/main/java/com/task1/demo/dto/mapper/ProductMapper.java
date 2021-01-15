package com.task1.demo.dto.mapper;

import com.task1.demo.dto.ProductDTO;
import com.task1.demo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO productToProductDTO(Product product);
}
