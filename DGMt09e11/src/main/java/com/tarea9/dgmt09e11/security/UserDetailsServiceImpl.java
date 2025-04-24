package com.tarea9.dgmt09e11.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tarea9.dgmt09e11.Repositorios.UsuarioRepository;
import com.tarea9.dgmt09e11.domain.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UsuarioRepository usuarioRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByNombre(nombre)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + nombre));
    return UserDetailsImpl.build(usuario);
  }

}
