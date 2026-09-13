package com.munizmiranda.helpdesk.mapper;

import com.munizmiranda.helpdesk.dto.UsuarioRequestDTO;
import com.munizmiranda.helpdesk.dto.UsuarioResponseDTO;
import com.munizmiranda.helpdesk.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}