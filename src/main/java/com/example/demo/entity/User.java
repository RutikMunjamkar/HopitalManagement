package com.example.demo.entity;

import com.example.demo.type.AuthProviderType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "APP_USER",indexes = {@Index(name="idx_provider_id_auth_provider_type",columnList = "providerId, authProviderType")})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String providerId;

    @Enumerated(EnumType.STRING)
    private AuthProviderType authProviderType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}