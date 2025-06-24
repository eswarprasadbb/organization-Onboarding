package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OAuthProviderDto;
import com.example.organizationservice.service.OAuthProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/oauth-providers")
@RequiredArgsConstructor
@Tag(name = "OAuth Providers")
public class OAuthProviderController {

    private final OAuthProviderService service;

    @GetMapping
    @Operation(summary = "List OAuth providers")
    public List<OAuthProviderDto> list() {
        return service.list();
    }

    @PostMapping
    @Operation(summary = "Create or update a provider")
    public OAuthProviderDto save(@RequestBody OAuthProviderDto dto) {
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a provider")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
