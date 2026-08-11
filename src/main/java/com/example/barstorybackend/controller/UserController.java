package com.example.barstorybackend.controller;

import com.example.barstorybackend.entity.User;
import com.example.barstorybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    // 重点：这里注入的是 UserService 接口，而不是具体的实现类
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal OidcUser oidcUser) {
        Map<String, Object> response = new HashMap<>();

        if (oidcUser == null) {
            response.put("code", 401);
            response.put("message", "用户未登录或Token已过期");
            return response;
        }

        // 依然直接调用接口定义的方法
        User localUser = userService.syncCasdoorUser(oidcUser);

        response.put("code", 200);
        response.put("message", "获取当前用户信息成功");
        response.put("data", localUser);

        return response;
    }

    @PostMapping("/add")
    public Map<String, Object> addUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 调用接口定义的方法
            User createdUser = userService.manuallyAddUser(user);

            response.put("code", 200);
            response.put("message", "用户创建成功");
            response.put("data", createdUser);
        } catch (IllegalArgumentException e) {
            response.put("code", 400);
            response.put("message", e.getMessage());
        } catch (Exception e) {
            response.put("code", 500);
            response.put("message", "服务器内部错误，创建失败");
        }

        return response;
    }

    /**
     * 3. 逻辑删除（注销）用户
     * 例如调用: DELETE /api/user/delete/10
     */
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = userService.deleteUser(id);
            if (success) {
                response.put("code", 200);
                response.put("message", "用户注销成功");
            } else {
                response.put("code", 404);
                response.put("message", "用户不存在或已被注销");
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("message", "服务器内部错误，注销失败");
        }

        return response;
    }

}