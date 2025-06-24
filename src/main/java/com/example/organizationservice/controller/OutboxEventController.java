package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OutboxEventDto;
import com.example.organizationservice.model.OutboxStatus;
import com.example.organizationservice.service.OutboxEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/outbox-events")
@RequiredArgsConstructor
@Tag(name = "Outbox Events")
public class OutboxEventController {

    private final OutboxEventService service;

    @GetMapping
    @Operation(summary = "List outbox events by status (max 100)")
    public List<OutboxEventDto> list(@RequestParam(defaultValue = "PENDING") OutboxStatus status) {
        return service.list(status);
    }
}
