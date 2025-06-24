package com.example.organizationservice.controller;

import com.example.organizationservice.dto.AuditLogDto;
import com.example.organizationservice.model.AuditAction;
import com.example.organizationservice.model.AuditLog;
import com.example.organizationservice.service.AuditLogService;
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
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
@Tag(name = "Audit Logs")
public class AuditLogController {

    private final AuditLogService service;

    @GetMapping
    @Operation(summary = "Search audit logs")
    public Page<AuditLogDto> search(@RequestParam(name = "actorUserId", required = false) UUID actorUserId,
                                    @RequestParam(name = "entity", required = false) String entity,
                                    @RequestParam(name = "action", required = false) AuditAction action,
                                    @RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
                                    @RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to,
                                    Pageable pageable) {
        Specification<AuditLog> spec = Specification.where(null);
        if (actorUserId != null) {
            spec = spec.and((root, q, cb) -> cb.equal(root.get("actorUser").get("id"), actorUserId));
        }
        if (entity != null) {
            spec = spec.and((root, q, cb) -> cb.equal(root.get("entity"), entity));
        }
        if (action != null) {
            spec = spec.and((root, q, cb) -> cb.equal(root.get("action"), action));
        }
        if (from != null) {
            spec = spec.and((root, q, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), from));
        }
        if (to != null) {
            spec = spec.and((root, q, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), to));
        }
        return service.search(spec, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get audit log by id")
    public AuditLogDto get(@PathVariable("id") UUID id) {
        return service.get(id);
    }
}
