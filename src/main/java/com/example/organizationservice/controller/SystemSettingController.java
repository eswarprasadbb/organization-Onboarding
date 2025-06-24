package com.example.organizationservice.controller;

import com.example.organizationservice.dto.SystemSettingDto;
import com.example.organizationservice.service.SystemSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/system-settings")
@RequiredArgsConstructor
@Tag(name = "System Settings")
public class SystemSettingController {

    private final SystemSettingService service;

    @GetMapping
    @Operation(summary = "List all system settings")
    public List<SystemSettingDto> list() {
        return service.list();
    }

    @PostMapping
    @Operation(summary = "Create or update a system setting")
    public SystemSettingDto upsert(@RequestBody SystemSettingDto dto) {
        return service.upsert(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a system setting")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
