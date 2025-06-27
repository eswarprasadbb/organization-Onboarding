package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationMemberDto;
import com.example.organizationservice.mapper.OrganizationMemberMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationMember;
import com.example.organizationservice.model.User;
import com.example.organizationservice.repository.OrganizationMemberRepository;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.repository.UserRepository;
import com.example.organizationservice.repository.RoleRepository;
import com.example.organizationservice.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationMemberService {

    private final OrganizationMemberRepository memberRepository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OrganizationMemberMapper mapper;

    public List<OrganizationMemberDto> listByOrganization(UUID orgId) {
        return memberRepository.findByOrganizationId(orgId).stream().map(mapper::toDto).toList();
    }

    @Transactional
    public OrganizationMemberDto invite(UUID orgId, UUID userId, OrganizationMember.Role seat, UUID invitedBy) {
        if (memberRepository.existsByOrganizationIdAndUserId(orgId, userId)) {
            throw new IllegalStateException("User already a member");
        }

        Organization org = organizationRepository.findById(orgId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        User inviter = invitedBy != null ? userRepository.findById(invitedBy).orElse(null) : null;

        // --- persist OrganizationMember row ---
        OrganizationMember member = OrganizationMember.builder()
                .organization(org)
                .user(user)
                .role(seat)
                .status(OrganizationMember.Status.INVITED)
                .invitedBy(inviter)
                .build();
        memberRepository.save(member);

        // --- ensure security Role entity is attached to the user ---
        Role roleEntity = roleRepository.findByOrganization_IdAndName(orgId, seat.name())
                .orElseThrow(() -> new IllegalStateException("Role entity '" + seat + "' not found for org " + orgId));

        if (user.getRoles().stream().noneMatch(r -> r.getId().equals(roleEntity.getId()))) {
            user.getRoles().add(roleEntity);
            userRepository.save(user); // creates user_roles link
        }

        return mapper.toDto(member);
    }

    @Transactional
    public void addRole(UUID memberId, UUID roleId) {
        OrganizationMember member = memberRepository.findById(memberId).orElseThrow();
        Role role = roleRepository.findById(roleId).orElseThrow();
        // verify both belong to same organization
        if (role.getOrganization() != null && !role.getOrganization().getId().equals(member.getOrganization().getId())) {
            throw new IllegalArgumentException("Role does not belong to the same organization");
        }
        User user = member.getUser();
        if (user.getRoles().add(role)) {
            userRepository.save(user); // creates user_roles link
        }
    }

    public void remove(UUID memberId) {
        memberRepository.deleteById(memberId);
    }
}
