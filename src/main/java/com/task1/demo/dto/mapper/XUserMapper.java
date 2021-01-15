package com.task1.demo.dto.mapper;


import com.task1.demo.dto.XUserDTO;
import com.task1.demo.model.XUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface XUserMapper {
    XUserMapper INSTANCE = Mappers.getMapper(XUserMapper.class);
    XUserDTO xuserToXUserDTO(XUser xUser);
}
