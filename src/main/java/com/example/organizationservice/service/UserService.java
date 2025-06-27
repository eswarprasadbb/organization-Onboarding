package com.example.organizationservice.service;

import com.example.organizationservice.dto.UserDto;
import com.example.organizationservice.mapper.UserMapper;
import com.example.organizationservice.dto.UserAuthoritiesDto;
import com.example.organizationservice.model.User;
import com.example.organizationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserDto get(UUID id) {
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow();
    }

    @Transactional
    public UserDto create(UserDto dto, String rawPassword) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User entity = userMapper.toEntity(dto);
        // Use email as username; ensure it fits DB column (255)
        String username = entity.getEmail();
        if (username.length() > 255) {
            username = username.substring(0, 255);
        }
        entity.setUsername(username);
        entity.setPassword(passwordEncoder.encode(rawPassword));
        return userMapper.toDto(userRepository.save(entity));
    }

    @Transactional
    public UserDto update(UUID id, UserDto dto) {
        User existing = userRepository.findById(id).orElseThrow();
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setPhone(dto.getPhone());
        existing.setAvatarUrl(dto.getAvatarUrl());
        existing.setJobTitle(dto.getJobTitle());
        existing.setDepartment(dto.getDepartment());
        existing.setTimezone(dto.getTimezone());
        existing.setLanguage(dto.getLanguage());
        existing.setPreferences(dto.getNotificationPreferences());
        return userMapper.toDto(userRepository.save(existing));
    }

    public UserAuthoritiesDto getAuthorities(UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        UserAuthoritiesDto dto = new UserAuthoritiesDto();
        dto.setUserId(id);
        dto.setRoles(user.getRoles().stream().map(r -> "ROLE_" + r.getName()).toList());
        dto.setPermissions(user.getRoles().stream()
                .filter(role -> role.getPermissions() != null)
                .flatMap(role -> role.getPermissions().stream())
                .map(permission -> permission.getName())
                .distinct()
                .toList());
        return dto;
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
