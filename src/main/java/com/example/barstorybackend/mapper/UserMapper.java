package com.example.barstorybackend.mapper;

import com.example.barstorybackend.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM barstory_user WHERE sso_user_id = #{ssoUserId}")
    User findBySsoUserId(String ssoUserId);

    // 根据主键 ID 查询用户 (后面注销接口会用到)
    @Select("SELECT * FROM barstory_user WHERE id = #{id}")
    User findById(Long id);

    @Insert("INSERT INTO barstory_user(sso_user_id, username, email, role, is_active, created_at, updated_at, last_login_at) " +
            "VALUES(#{ssoUserId}, #{username}, #{email}, #{role}, #{isActive}, #{createdAt}, #{updatedAt}, #{lastLoginAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE barstory_user SET username=#{username}, email=#{email}, updated_at=#{updatedAt}, last_login_at=#{lastLoginAt} " +
            "WHERE id=#{id}")
    void update(User user);

    // ================= 新增：逻辑删除用户 =================
    @Delete("DELETE FROM barstory_user WHERE id = #{id}")
    int deleteById(Long id);
}