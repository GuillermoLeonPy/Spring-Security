package com.java.spring.security.examples.authentication.controller.security.repository.model;

import jakarta.persistence.*;

@Entity(name = "Authority")
@Table(name = "security_authorities")
@IdClass(AuthorityId.class)
public class Authority {

    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Id
    @Column(name = "authority", nullable = false)
    private String authority;

    public Authority() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "userId=" + userId +
                ", authority='" + authority + '\'' +
                '}';
    }
}
