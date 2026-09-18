package com.example.demo.entity;

import com.example.demo.security.RolePermissionMapping;
import com.example.demo.type.AuthProviderType;
import com.example.demo.type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<RoleType> roles=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return roles.stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet());
        Set<SimpleGrantedAuthority>authorities=new HashSet<>();
        roles.forEach(role->{
            Set<SimpleGrantedAuthority>permission=RolePermissionMapping.getAuthoritiesForRole(role);
            authorities.addAll(permission);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        }
        );
        return authorities;
    }
}