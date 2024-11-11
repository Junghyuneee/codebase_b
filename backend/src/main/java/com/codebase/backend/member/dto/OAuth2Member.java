package com.codebase.backend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class OAuth2Member implements OAuth2User, UserDetails {

    @Getter
    MemberDTO member;
    Map<String, Object> attributeMap;


    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributeMap;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->this.member.isRole()?"ROLE_ADMIN":"ROLE_USER");
    }

    @Override
    public String getName() {
        return member.getName();
    }
}
