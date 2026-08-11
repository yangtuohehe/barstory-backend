package com.example.barstorybackend.entity;

import java.time.LocalDateTime;

public class User {

    private Long id;

    // 对应 sso_user_id (Casdoor传过来的唯一ID)
    private String ssoUserId;

    // 对应 username
    private String username;

    // 对应 email
    private String email;

    // 对应 role (默认 'USER')
    private String role;

    // 对应 is_active (默认 true)
    private Boolean isActive;

    // 对应 created_at
    private LocalDateTime createdAt;

    // 对应 updated_at
    private LocalDateTime updatedAt;

    // 对应 last_login_at
    private LocalDateTime lastLoginAt;

    // --- 以下是 Getter 和 Setter ---
    // (如果你的项目中引入了 Lombok，可以直接在类名上加 @Data 注解，省略这些冗长的代码)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSsoUserId() { return ssoUserId; }
    public void setSsoUserId(String ssoUserId) { this.ssoUserId = ssoUserId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getLastLoginAt() { return lastLoginAt; }
    public void setLastLoginAt(LocalDateTime lastLoginAt) { this.lastLoginAt = lastLoginAt; }
}