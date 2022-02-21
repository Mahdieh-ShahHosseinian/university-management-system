package com.example.sm.model;

import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Set;

@MappedSuperclass
public class ApplicationUser extends RepresentationModel<ApplicationUser> implements UserDetails {

    @Setter(AccessLevel.PROTECTED)
    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;

    @Setter
    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Setter
    @Column(nullable = false, length = 60)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
