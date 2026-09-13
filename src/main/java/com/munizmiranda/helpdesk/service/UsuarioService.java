package com.munizmiranda.helpdesk.service;

import com.munizmiranda.helpdesk.dto.UsuarioRequestDTO;
import com.munizmiranda.helpdesk.dto.UsuarioResponseDTO;
import com.munizmiranda.helpdesk.exception.EmailJaCadastradoException;
import com.munizmiranda.helpdesk.mapper.UsuarioMapper;
import com.munizmiranda.helpdesk.model.Usuario;
import com.munizmiranda.helpdesk.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailJaCadastradoException(dto.email());
        }

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));

        Usuario salvo = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(salvo);
    }
}