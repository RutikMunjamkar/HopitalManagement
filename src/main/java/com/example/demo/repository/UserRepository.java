    package com.example.demo.repository;

    import com.example.demo.entity.User;
    import com.example.demo.type.AuthProviderType;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.Optional;

    public interface UserRepository extends JpaRepository<User,Long> {
        Optional<UserDetails> findByUsername(String username);


        boolean existsByUsername(String email);

       Optional<User> findByProviderIdAndAuthProviderType(String providerId, AuthProviderType authProviderType);
    }
