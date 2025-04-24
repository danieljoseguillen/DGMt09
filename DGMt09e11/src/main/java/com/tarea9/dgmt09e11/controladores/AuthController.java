package com.tarea9.dgmt09e11.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarea9.dgmt09e11.Repositorios.UsuarioRepository;
import com.tarea9.dgmt09e11.domain.Usuario;
import com.tarea9.dgmt09e11.dto.JwtResponseDto;
import com.tarea9.dgmt09e11.dto.LoginDTO;
import com.tarea9.dgmt09e11.dto.MessageResponse;
import com.tarea9.dgmt09e11.dto.SignUpDTO;
import com.tarea9.dgmt09e11.modelos.Rol;
import com.tarea9.dgmt09e11.security.JwtUtils;
import com.tarea9.dgmt09e11.security.UserDetailsImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDto) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getNombre(), loginDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    String rol = userDetails.getAuthorities().stream().findFirst().map(a -> a.getAuthority()).orElse("ERROR");

    return ResponseEntity.ok(new JwtResponseDto(jwt, "Bearer",
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getCorreo(),
        rol));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDTO signUpRequest) {
    if (usuarioRepository.existsByNombre(signUpRequest.getNombre())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Ya existe un usuario con ese nombre"));
    }

    if (usuarioRepository.existsByCorreo(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Ya existe un usuario con ese email"));
    }

    // Create new user's account
    Usuario user = new Usuario(null, signUpRequest.getNombre(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()),
        Rol.valueOf(signUpRequest.getRol()), new ArrayList<>());
    usuarioRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente"));
  }
}
