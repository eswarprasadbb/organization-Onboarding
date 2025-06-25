package com.example.organizationservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.*;
import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "users", indexes = {
        @jakarta.persistence.Index(name = "idx_users_email", columnList = "email", unique = true),
        @jakarta.persistence.Index(name = "idx_users_status", columnList = "status")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Personal
    @Column(nullable = false, length = 100)
    @Builder.Default
    private String firstName = "";

    @Column(nullable = false, length = 100)
    @Builder.Default
    private String lastName = "";

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String phone;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    // Auth
    @Column(name = "password_hash", nullable = false, length = 255)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;

    @Builder.Default
    private Boolean emailVerified = false;

    @Builder.Default
    private String emailVerificationToken = UUID.randomUUID().toString();

    @Builder.Default
    private Instant emailVerifiedAt = null;

    // Security
    @Builder.Default
    private Boolean twoFactorEnabled = false;

    @Builder.Default
    private String twoFactorSecret = "";

    @Builder.Default
    private Instant lastLoginAt = null;

    @Builder.Default
    private Integer loginAttempts = 0;

    @Builder.Default
    private Instant lockedUntil = null;

    // Profile
    @Builder.Default
    private String avatarUrl = "";
    @Builder.Default
    private String jobTitle = "";
    @Builder.Default
    private String department = "";

    // Preferences
    @Builder.Default
    private String timezone = "UTC";

    @Builder.Default
    private String language = "en";

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "notification_preferences")
    @Builder.Default
    private Map<String, Object> preferences = new HashMap<>();

    @CreationTimestamp
    @Builder.Default
    private Instant createdAt = null;

    @UpdateTimestamp
    @Builder.Default
    private Instant updatedAt = null;

    /**
     * Convenience accessor returning ROLE_ and permission names so legacy code
     * that previously relied on plain-string authorities continues to work.
     */
    public Set<String> getAuthorities() {
        java.util.Set<String> auths = new java.util.HashSet<>();
        for (Role r : roles) {
            auths.add("ROLE_" + r.getName());
            if (r.getPermissions() != null) {
                r.getPermissions().forEach(p -> auths.add(p.getName()));
            }
        }
        return auths;
    }

    public enum UserStatus { ACTIVE, INACTIVE, SUSPENDED, DELETED }
}
