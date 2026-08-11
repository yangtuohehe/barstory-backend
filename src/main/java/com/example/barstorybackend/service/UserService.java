package com.example.barstorybackend.service;

import com.example.barstorybackend.entity.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

/**
 * 用户业务逻辑接口
 */
public interface UserService {

    /**
     * 处理 Casdoor 登录成功后的用户信息同步
     * @param oidcUser Casdoor 返回的用户信息
     * @return 数据库中的用户实体
     */
    User syncCasdoorUser(OidcUser oidcUser);

    /**
     * 手动新增用户
     * @param user 前端传入的用户信息
     * @return 创建成功的用户实体
     */
    User manuallyAddUser(User user);

    /**
     * 逻辑删除（注销）用户
     * @param id 用户主键ID
     * @return 成功返回 true，用户不存在或已注销返回 false
     */
    boolean deleteUser(Long id);


}