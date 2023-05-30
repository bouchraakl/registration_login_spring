package com.project.registerSpring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users", schema = "public")
@Audited
@AuditTable(value = "users_audit", schema = "audit")
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails {

    @Getter
    @Setter
    @Column(name = "first_name", nullable = false, length = 30)
    @NotNull(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name cannot exceed 30 characters")
    public String firstName;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = false, length = 30)
    @NotNull(message = "Last name is required")
    @Size(min = 2, max = 30, message = "Last name cannot exceed 30 characters")
    public String lastName;

    @Getter
    @Setter
    @Column(name = "username", nullable = false, unique = true, length = 20)
    @NotNull(message = "Username is required")
    @Size(min = 2, max = 20, message = "Username cannot exceed 20 characters")
    public String userName;

    @Getter
    @Setter
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Invalid email address")
    @NotNull(message = "Email is required")
    public String email;

    @Getter
    @Setter
    @Column(name = "password", nullable = false, length = 15)
    @NotNull(message = "Password is required")
    @Size(min = 2, max = 15, message = "Password cannot exceed 15 characters")
    public String password;

    @Getter
    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    public Role role;

    @Getter
    @Setter
    @Column(name = "locked")
    public boolean locked;

    @Getter
    @Setter
    @Column(name = "enabled")
    public boolean enabled;

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}

