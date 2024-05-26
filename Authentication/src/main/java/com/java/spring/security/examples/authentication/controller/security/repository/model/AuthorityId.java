package com.java.spring.security.examples.authentication.controller.security.repository.model;

import java.io.Serializable;

public class AuthorityId implements Serializable {

    private Integer userId;
    private String authority;

    public AuthorityId() {
    }

    public AuthorityId(Integer userId, String authority) {
        this.userId = userId;
        this.authority = authority;
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
}
