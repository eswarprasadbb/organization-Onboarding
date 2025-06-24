package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationMemberDto;
import com.example.organizationservice.mapper.OrganizationMemberMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationMember;
import com.example.organizationservice.model.User;
import com.example.organizationservice.repository.OrganizationMemberRepository;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.repository.UserRepository;
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
    private final OrganizationMemberMapper mapper;

    public List<OrganizationMemberDto> listByOrganization(UUID orgId) {
        return memberRepository.findByOrganizationId(orgId).stream().map(mapper::toDto).toList();
    }

    @Transactional
    public OrganizationMemberDto invite(UUID orgId, UUID userId, OrganizationMember.Role role, UUID invitedBy) {
        if (memberRepository.existsByOrganizationIdAndUserId(orgId, userId)) {
            throw new IllegalStateException("User already a member");
        }
        Organization org = organizationRepository.findById(orgId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        User inviter = invitedBy != null ? userRepository.findById(invitedBy).orElse(null) : null;
        OrganizationMember member = OrganizationMember.builder()
                .organization(org)
                .user(user)
                .role(role)
                .status(OrganizationMember.Status.INVITED)
                .invitedBy(inviter)
                .build();
        return mapper.toDto(memberRepository.save(member));
    }

    @Transactional
    public void remove(UUID memberId) {
        memberRepository.deleteById(memberId);
    }
}
