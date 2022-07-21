package com.officersofttest.services;

import com.officersofttest.core.excepetions.WrongParameter;
import com.officersofttest.models.entities.Usuario;
import com.officersofttest.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    public Usuario save(Usuario usuario) {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            throw new WrongParameter("Já está cadastrado alguém com este usuário");
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario edit(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);

    }

    public Page<Usuario> findAll(int pageNumber, int PageSize) {
        if (PageSize > 10) PageSize = 10;
        Pageable page = PageRequest.of(pageNumber, PageSize);
        return usuarioRepository.findAll(page);
    }

    public void deleteByid(Long id) {
        usuarioRepository.deleteById(id);
    }
}