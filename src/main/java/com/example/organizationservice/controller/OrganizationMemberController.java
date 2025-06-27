package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationMemberDto;
import com.example.organizationservice.model.OrganizationMember;
import com.example.organizationservice.service.OrganizationMemberService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizations/{orgId}/members")
@RequiredArgsConstructor
@Tag(name = "Organization Members")
public class OrganizationMemberController {

    private final OrganizationMemberService memberService;

    @GetMapping
    @PreAuthorize("hasAuthority('ORG_MEMBER_READ')")
    public List<OrganizationMemberDto> list(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId) {
        return memberService.listByOrganization(orgId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ORG_MEMBER_INVITE')")
    public ResponseEntity<OrganizationMemberDto> invite(
            @Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
            @RequestParam("userId") UUID userId,
            @RequestParam("role") OrganizationMember.Role role,
            @RequestParam(name = "invitedBy", required = false) UUID invitedBy) {
        OrganizationMemberDto dto = memberService.invite(orgId, userId, role, invitedBy);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping("/{memberId}/roles/{roleId}")
    @PreAuthorize("hasAuthority('ORG_MEMBER_ROLE_MANAGE')")
    public ResponseEntity<Void> addRole(@PathVariable("orgId") UUID orgId,
                                        @PathVariable("memberId") UUID memberId,
                                        @PathVariable("roleId") UUID roleId) {
        memberService.addRole(memberId, roleId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{memberId}")
    @PreAuthorize("hasAuthority('ORG_MEMBER_REMOVE')")
    public ResponseEntity<Void> remove(@PathVariable("orgId") UUID orgId, @PathVariable("memberId") UUID memberId) {
        memberService.remove(memberId);
        return ResponseEntity.noContent().build();
    }
}
