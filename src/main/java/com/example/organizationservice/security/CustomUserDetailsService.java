package com.example.organizationservice.security;

import com.example.organizationservice.model.User;
import com.example.organizationservice.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Collection<? extends GrantedAuthority> authorities =
                user.getRoles().stream()
                        .flatMap(role -> {
                            java.util.stream.Stream<GrantedAuthority> roleAuth = java.util.stream.Stream.of(
                                    new SimpleGrantedAuthority("ROLE_" + role.getName()));
                            java.util.stream.Stream<GrantedAuthority> permAuth = role.getPermissions() == null ?
                                    java.util.stream.Stream.empty() :
                                    role.getPermissions().stream()
                                            .map(p -> new SimpleGrantedAuthority(p.getName()));
                            return java.util.stream.Stream.concat(roleAuth, permAuth);
                        })
                        .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}
