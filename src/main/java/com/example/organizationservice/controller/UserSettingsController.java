package com.example.organizationservice.controller;

import com.example.organizationservice.dto.UserSettingsDto;
import com.example.organizationservice.service.UserSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/settings")
@RequiredArgsConstructor
@Tag(name = "User Settings")
public class UserSettingsController {

    private final UserSettingsService service;

    @GetMapping
    @Operation(summary = "Get user settings")
    public UserSettingsDto get(@PathVariable("userId") UUID userId) {
        return service.get(userId);
    }

    @PutMapping
    @Operation(summary = "Update (upsert) user settings")
    public UserSettingsDto update(@PathVariable("userId") UUID userId, @RequestBody UserSettingsDto dto) {
        return service.upsert(userId, dto);
    }
}
