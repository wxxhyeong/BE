package com.be.user.mapper;


import com.be.user.domain.User;
import com.be.user.dto.res.UserRegisterResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    int insert(UserRegisterResDto userRegisterResDto);

    User selectOneByUserID(String userID);

    User selectOneByUserEmail(String userEmail);

}
