package com.example.leaveapi.dto;



import com.example.leaveapi.entity.User;
import com.example.leaveapi.enums.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;



public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();

    public UserDTO() {
        super();
    }

    public UserDTO(Long id, String username, String email, Set<String> roles) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public UserDTO(User User) {
        super();
        this.id = User.getId();
        this.username = User.getUsername();
        this.email = User.getEmail();
        this.setRoles(User.getRoles());
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles.stream().map(r -> r.getDescription()).collect(Collectors.toSet());
    }

}
