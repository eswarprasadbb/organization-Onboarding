package com.example.organizationservice.config;

import com.example.organizationservice.model.Permission;
import com.example.organizationservice.model.Role;
import com.example.organizationservice.repository.PermissionRepository;
import com.example.organizationservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Seeds canonical permission rows and the global SYSTEM_ADMIN role on application startup.
 * Runs idempotently – safe to execute on every boot.
 */
@Slf4j
@Component
@Order(1)
@RequiredArgsConstructor
public class PermissionDataSeeder implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    private static final List<String> CANONICAL_PERMISSIONS = List.of(
            // Organization core
            "ORG_READ","ORG_CREATE","ORG_UPDATE","ORG_DELETE",
            // Role & permission admin
            "ROLE_READ","ROLE_CREATE","ROLE_UPDATE","ROLE_DELETE","ROLE_PERMISSION_MANAGE",
            // Org members
            "ORG_MEMBER_READ","ORG_MEMBER_INVITE","ORG_MEMBER_REMOVE","ORG_MEMBER_ROLE_MANAGE",
            // User settings
            "USER_SETTINGS_READ","USER_SETTINGS_UPDATE",
            // System settings
            "SYSTEM_SETTING_READ","SYSTEM_SETTING_UPDATE",
            // Rate-limit rules
            "RATE_LIMIT_RULE_READ","RATE_LIMIT_RULE_CREATE","RATE_LIMIT_RULE_UPDATE","RATE_LIMIT_RULE_DELETE",
            // Customers
            "CUSTOMER_READ","CUSTOMER_CREATE","CUSTOMER_UPDATE","CUSTOMER_DELETE",
            // Onboarding step templates
            "ONBOARDING_STEP_TEMPLATE_READ","ONBOARDING_STEP_TEMPLATE_CREATE","ONBOARDING_STEP_TEMPLATE_UPDATE","ONBOARDING_STEP_TEMPLATE_DELETE",
            // OAuth providers
            "OAUTH_PROVIDER_READ","OAUTH_PROVIDER_CREATE","OAUTH_PROVIDER_UPDATE","OAUTH_PROVIDER_DELETE",
            // Outbox events (read-only)
            "OUTBOX_EVENT_READ",
            // Audit & API call logs
            "AUDIT_LOG_READ","API_CALL_LOG_READ",
            // Organization settings
            "ORG_SETTING_READ","ORG_SETTING_UPDATE",
            // Organization API keys
            "ORG_API_KEY_READ","ORG_API_KEY_CREATE","ORG_API_KEY_DELETE",
            // Organization addresses
            "ORG_ADDRESS_READ","ORG_ADDRESS_CREATE","ORG_ADDRESS_UPDATE","ORG_ADDRESS_DELETE",
            // Organization divisions
            "ORG_DIVISION_READ","ORG_DIVISION_CREATE","ORG_DIVISION_UPDATE","ORG_DIVISION_DELETE"
    );


    private static final String SYSTEM_ADMIN = "SYSTEM_ADMIN";

    @Override
    public void run(String... args) {
        seedPermissions();
        seedSystemAdminRole();
    }

    private void seedPermissions() {
        for (String permName : CANONICAL_PERMISSIONS) {
            if (!permissionRepository.existsByName(permName)) {
                permissionRepository.save(Permission.builder()
                        .name(permName)
                        .description(permName.replace('_', ' ').toLowerCase())
                        .build());
                log.info("Seeded permission {}", permName);
            }
        }
    }

    @org.springframework.transaction.annotation.Transactional
    private void seedSystemAdminRole() {
        Role sysRole = roleRepository.findByOrganizationIsNullAndNameFetchPermissions(SYSTEM_ADMIN)
                .orElseGet(() -> {
                    Role r = Role.builder()
                            .name(SYSTEM_ADMIN)
                            .description("Global system administrator")
                            .permissions(new HashSet<>())
                            .build();
                    return roleRepository.save(r);
                });

        // attach every permission to sysadmin role
        Set<Permission> allPerms = new HashSet<>(permissionRepository.findAll());
        if (!sysRole.getPermissions().containsAll(allPerms)) {
            sysRole.getPermissions().addAll(allPerms);
            roleRepository.save(sysRole);
            log.info("Updated SYSTEM_ADMIN role with {} permissions", allPerms.size());
        }
    }
}
