package com.example.organizationservice.controller;

import com.example.organizationservice.dto.ApiCallLogDto;
import com.example.organizationservice.model.ApiCallLog;
import com.example.organizationservice.service.ApiCallLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/api-call-logs")
@RequiredArgsConstructor
@Tag(name = "API Call Logs")
public class ApiCallLogController {

    private final ApiCallLogService service;

    @GetMapping
    @Operation(summary = "Search API call logs")
    public Page<ApiCallLogDto> search(@RequestParam(name = "userId", required = false) UUID userId,
                                      @RequestParam(name = "organizationId", required = false) UUID organizationId,
                                      @RequestParam(name = "method", required = false) String method,
                                      @RequestParam(name = "path", required = false) String path,
                                      @RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
                                      @RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to,
                                      Pageable pageable) {
        Specification<ApiCallLog> spec = Specification.where(null);
        if (userId != null) {
            spec = spec.and((r, q, cb) -> cb.equal(r.get("user").get("id"), userId));
        }
        if (organizationId != null) {
            spec = spec.and((r, q, cb) -> cb.equal(r.get("organization").get("id"), organizationId));
        }
        if (method != null) {
            spec = spec.and((r, q, cb) -> cb.equal(r.get("method"), method));
        }
        if (path != null) {
            spec = spec.and((r, q, cb) -> cb.like(r.get("path"), path + "%"));
        }
        if (from != null) {
            spec = spec.and((r, q, cb) -> cb.greaterThanOrEqualTo(r.get("createdAt"), from));
        }
        if (to != null) {
            spec = spec.and((r, q, cb) -> cb.lessThanOrEqualTo(r.get("createdAt"), to));
        }
        return service.search(spec, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get API call log by id")
    public ApiCallLogDto get(@PathVariable("id") UUID id) {
        return service.get(id);
    }
}
