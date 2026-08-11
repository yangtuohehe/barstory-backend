package com.example.barstorybackend.service.impl;

import com.example.barstorybackend.entity.User;
import com.example.barstorybackend.mapper.UserMapper;
import com.example.barstorybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User syncCasdoorUser(OidcUser oidcUser) {
        String ssoUserId = oidcUser.getSubject();
        User user = userMapper.findBySsoUserId(ssoUserId);
        LocalDateTime now = LocalDateTime.now();

        if (user != null) {
            // 老用户：更新信息
            user.setUsername(oidcUser.getFullName());
            user.setEmail(oidcUser.getEmail());
            user.setUpdatedAt(now);
            user.setLastLoginAt(now);

            userMapper.update(user);
        } else {
            // 新用户：自动注册
            user = new User();
            user.setSsoUserId(ssoUserId);
            user.setUsername(oidcUser.getFullName());
            user.setEmail(oidcUser.getEmail());
            user.setRole("USER");
            user.setIsActive(true);
            user.setCreatedAt(now);
            user.setUpdatedAt(now);
            user.setLastLoginAt(now);

            userMapper.insert(user);
        }
        return user;
    }

    @Override
    public User manuallyAddUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }

        if (user.getSsoUserId() == null) {
            user.setSsoUserId("manual_" + System.currentTimeMillis());
        }

        LocalDateTime now = LocalDateTime.now();

        if (user.getRole() == null) {
            user.setRole("USER");
        }
        if (user.getIsActive() == null) {
            user.setIsActive(true);
        }

        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setLastLoginAt(null);

        userMapper.insert(user);

        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        // 执行物理删除，deleteById 会返回数据库受影响的行数
        int affectedRows = userMapper.deleteById(id);

        // 如果受影响的行数大于 0，说明真正删除了数据；等于 0 说明库里压根没这个 ID
        return affectedRows > 0;
    }
}