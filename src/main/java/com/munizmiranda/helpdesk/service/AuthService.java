package com.munizmiranda.helpdesk.service;

import com.munizmiranda.helpdesk.dto.LoginRequestDTO;
import com.munizmiranda.helpdesk.dto.LoginResponseDTO;
import com.munizmiranda.helpdesk.exception.CredenciaisInvalidasException;
import com.munizmiranda.helpdesk.model.Atendente;
import com.munizmiranda.helpdesk.model.Usuario;
import com.munizmiranda.helpdesk.repository.AtendenteRepository;
import com.munizmiranda.helpdesk.repository.UsuarioRepository;
import com.munizmiranda.helpdesk.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AtendenteRepository atendenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository, AtendenteRepository atendenteRepository,
                        PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.atendenteRepository = atendenteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(CredenciaisInvalidasException::new);

        if (!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new CredenciaisInvalidasException();
        }

        String token = jwtUtil.gerarToken(usuario.getEmail());
        return new LoginResponseDTO(token);
    }

    public LoginResponseDTO loginAtendente(LoginRequestDTO dto) {
        Atendente atendente = atendenteRepository.findByEmail(dto.email())
                .orElseThrow(CredenciaisInvalidasException::new);

        if (!passwordEncoder.matches(dto.senha(), atendente.getSenha())) {
            throw new CredenciaisInvalidasException();
        }

        String token = jwtUtil.gerarToken(atendente.getEmail());
        return new LoginResponseDTO(token);
    }
}