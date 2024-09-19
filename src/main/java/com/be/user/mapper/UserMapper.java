package com.be.user.mapper;


import com.be.user.dto.res.UserRegisterResDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public int insert(UserRegisterResDto userRegisterResDto);

}
