package com.example.organizationservice.service;

import com.example.organizationservice.dto.OAuthProviderDto;
import com.example.organizationservice.mapper.OAuthProviderMapper;
import com.example.organizationservice.model.OAuthProvider;
import com.example.organizationservice.repository.OAuthProviderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OAuthProviderService {

    private final OAuthProviderRepository repository;
    private final OAuthProviderMapper mapper;

    public List<OAuthProviderDto> list() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public OAuthProviderDto save(OAuthProviderDto dto) {
        OAuthProvider entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
