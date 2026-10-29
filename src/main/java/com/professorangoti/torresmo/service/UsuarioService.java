package com.professorangoti.torresmo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.professorangoti.torresmo.dto.RegistroForm;
import com.professorangoti.torresmo.model.Perfil;
import com.professorangoti.torresmo.model.Usuario;
import com.professorangoti.torresmo.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }

  // Chamado pelo Spring Security no login; o "username" aqui é o e-mail
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email.trim())
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

    return User.withUsername(usuario.getEmail())
        .password(usuario.getSenha())
        .roles(usuario.getPerfil().name())
        .build();
  }

  public boolean emailJaCadastrado(String email) {
    return usuarioRepository.existsByEmailIgnoreCase(email.trim());
  }

  public Usuario registrarCliente(RegistroForm form) {
    Usuario usuario = new Usuario();
    usuario.setNome(form.getNome().trim());
    usuario.setEmail(form.getEmail().trim().toLowerCase());
    usuario.setSenha(passwordEncoder.encode(form.getSenha()));
    usuario.setPerfil(Perfil.CLIENTE);
    return usuarioRepository.save(usuario);
  }
}
