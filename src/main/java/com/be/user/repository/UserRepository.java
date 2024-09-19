package com.be.user.repository;

import com.be.user.dto.res.UserRegisterResDto;
import com.be.user.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public UserRepository(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public int insertUser(UserRegisterResDto userRegisterResDto) {
        return sqlSessionTemplate.getMapper(UserMapper.class).insert(userRegisterResDto);
    }

}
