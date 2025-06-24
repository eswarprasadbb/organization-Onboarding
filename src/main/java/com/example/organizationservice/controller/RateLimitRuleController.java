package com.example.organizationservice.controller;

import com.example.organizationservice.dto.RateLimitRuleDto;
import com.example.organizationservice.service.RateLimitRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rate-limit-rules")
@RequiredArgsConstructor
@Tag(name = "Rate Limit Rules")
public class RateLimitRuleController {

    private final RateLimitRuleService service;

    @GetMapping
    @Operation(summary = "List rate limit rules")
    public List<RateLimitRuleDto> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get rate limit rule by id")
    public RateLimitRuleDto get(@PathVariable("id") UUID id) {
        return service.get(id);
    }

    @PostMapping
    @Operation(summary = "Create rate limit rule")
    public ResponseEntity<RateLimitRuleDto> create(@RequestBody RateLimitRuleDto dto) {
        RateLimitRuleDto created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/rate-limit-rules/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update rate limit rule")
    public RateLimitRuleDto update(@PathVariable("id") UUID id, @RequestBody RateLimitRuleDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete rate limit rule")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
