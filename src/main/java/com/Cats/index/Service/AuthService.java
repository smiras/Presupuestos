package com.Cats.index.Service;

import com.Cats.index.Entity.User;
import com.Cats.index.Enum.Role;
import com.Cats.index.Repository.UserRepository;
import com.Cats.index.Request.LoginRequest;
import com.Cats.index.Request.RegisterRequest;
import com.Cats.index.Response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        User usuario = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        String mensaje;
        switch (usuario.getRole()) {
            case SUPERADMIN :
                mensaje = "admin/control.html";
                break;
            case ADMIN, USER:
                mensaje = "user/index.html";
                break;
            default: mensaje = "user";
                break;
        }
        return AuthResponse.builder()
                .token(token)
                .rol(usuario.getRole())
                .active(usuario.getActive())
                .empresa(usuario.getEmpresa())
                .mensaje(mensaje)
                .userId(usuario.getId())
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .name(request.getName())
                .role(request.getRol())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
