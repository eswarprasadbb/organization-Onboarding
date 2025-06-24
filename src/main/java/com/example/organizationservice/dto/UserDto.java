package com.example.organizationservice.dto;

import com.example.organizationservice.model.User;
import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean emailVerified;
    private String avatarUrl;
    private String jobTitle;
    private String department;
    private String timezone;
    private String language;
    private Map<String, Object> notificationPreferences;
    private Instant createdAt;
    private Instant updatedAt;
    private User.UserStatus status;
}
