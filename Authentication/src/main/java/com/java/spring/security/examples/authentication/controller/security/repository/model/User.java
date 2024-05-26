package com.java.spring.security.examples.authentication.controller.security.repository.model;

import jakarta.persistence.*;

@Entity(name = "User")
@Table(name = "security_users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "security_users_user_id_sq_generator")
    @SequenceGenerator(
            name = "security_users_user_id_sq_generator",
            initialValue = 1,
            sequenceName = "security_users_user_id_sq",
            allocationSize = 1)
    private Integer id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
