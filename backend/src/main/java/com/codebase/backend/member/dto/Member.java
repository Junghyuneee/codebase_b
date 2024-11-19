package com.codebase.backend.member.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {
    private int id;

    private String name;

    private String email;

    private String password;

    // true: admin, false user
    private boolean role;

    private String addr;

    private String postcode;

    private String tel;

    private LocalDate createdAt;

    private int projectCount;


    // Foreign Key
    // private Project

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role ? "ROLE_ADMIN" : "ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify logic if needed
    }
}
