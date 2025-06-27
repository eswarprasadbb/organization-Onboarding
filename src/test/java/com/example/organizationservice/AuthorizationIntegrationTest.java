package com.example.organizationservice;

import com.example.organizationservice.model.Permission;
import com.example.organizationservice.model.Role;
import com.example.organizationservice.model.User;
import com.example.organizationservice.repository.PermissionRepository;
import com.example.organizationservice.repository.RoleRepository;
import com.example.organizationservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * VERY small smoke-test proving that a user with ORG_READ permission can access
 * /api/organizations while a user without it receives 403.  Executes against an
 * in-memory H2 database (spring.profiles.active=test).
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorizationIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    PermissionRepository permRepo;
    @Autowired
    PasswordEncoder encoder;

    String baseUrl;

    @BeforeEach
    void setup() {
        baseUrl = "http://localhost:" + port;
    }

    @Test
    void viewerWithOrgReadGets200() {
        // --- Arrange test data ------------------------------------------------
        Permission orgRead = permRepo.findByName("ORG_READ").orElseThrow();
        Role viewer = new Role();
        viewer.setName("VIEWER");
        viewer.setPermissions(Set.of(orgRead));
        roleRepo.save(viewer);

        User bob = new User();
        bob.setEmail("bob@test.io");
        bob.setUsername("bob");
        bob.setPhone("000");
        bob.setPassword(encoder.encode("pass"));
        bob.setStatus(User.UserStatus.ACTIVE);
        bob.setRoles(Set.of(viewer));
        userRepo.save(bob);

        // --- Login ------------------------------------------------------------
        ResponseEntity<LoginResp> login = rest.postForEntity(baseUrl + "/api/auth/login",
                new LoginReq("bob@test.io", "pass"), LoginResp.class);
        assertThat(login.getStatusCode()).isEqualTo(HttpStatus.OK);
        String acc = login.getBody().accessToken();

        // --- Act --------------------------------------------------------------
        HttpHeaders hdr = new HttpHeaders();
        hdr.setBearerAuth(acc);
        ResponseEntity<String> res = rest.exchange(baseUrl + "/api/organizations",
                HttpMethod.GET, new HttpEntity<>(hdr), String.class);

        // --- Assert -----------------------------------------------------------
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void noPermissionGets403() {
        // user with no roles
        User eve = new User();
        eve.setEmail("eve@test.io");
        eve.setUsername("eve");
        eve.setPhone("001");
        eve.setPassword(encoder.encode("pass"));
        eve.setStatus(User.UserStatus.ACTIVE);
        userRepo.save(eve);

        ResponseEntity<LoginResp> login = rest.postForEntity(baseUrl + "/api/auth/login",
                new LoginReq("eve@test.io", "pass"), LoginResp.class);
        String acc = login.getBody().accessToken();

        HttpHeaders hdr = new HttpHeaders();
        hdr.setBearerAuth(acc);
        ResponseEntity<String> res = rest.exchange(baseUrl + "/api/organizations",
                HttpMethod.GET, new HttpEntity<>(hdr), String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    // DTOs for auth
    record LoginReq(String email, String password) {}
    record LoginResp(String accessToken, String refreshToken, String tokenType) {}
}
