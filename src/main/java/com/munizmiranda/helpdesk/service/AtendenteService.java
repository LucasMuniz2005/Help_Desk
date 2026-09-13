package com.munizmiranda.helpdesk.service;

import com.munizmiranda.helpdesk.dto.AtendenteRequestDTO;
import com.munizmiranda.helpdesk.dto.AtendenteResponseDTO;
import com.munizmiranda.helpdesk.exception.EmailJaCadastradoException;
import com.munizmiranda.helpdesk.mapper.AtendenteMapper;
import com.munizmiranda.helpdesk.model.Atendente;
import com.munizmiranda.helpdesk.repository.AtendenteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AtendenteService {

    private final AtendenteRepository atendenteRepository;
    private final AtendenteMapper atendenteMapper;
    private final PasswordEncoder passwordEncoder;

    public AtendenteService(AtendenteRepository atendenteRepository, AtendenteMapper atendenteMapper, PasswordEncoder passwordEncoder) {
        this.atendenteRepository = atendenteRepository;
        this.atendenteMapper = atendenteMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public AtendenteResponseDTO cadastrar(AtendenteRequestDTO dto) {
        if (atendenteRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailJaCadastradoException(dto.email());
        }

        Atendente atendente = atendenteMapper.toEntity(dto);
        atendente.setSenha(passwordEncoder.encode(dto.senha()));

        Atendente salvo = atendenteRepository.save(atendente);
        return atendenteMapper.toResponseDTO(salvo);
    }
}