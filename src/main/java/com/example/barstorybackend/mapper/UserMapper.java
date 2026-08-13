package com.example.barstorybackend.mapper;

import com.example.barstorybackend.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findBySsoUserId(String ssoUserId);

    User findById(Long id);

    void insert(User user);

    void update(User user);

    int deleteById(Long id);
}