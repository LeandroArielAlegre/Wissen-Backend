package com.api.wissenapi.services;

import com.api.wissenapi.Auth.AuthResponse;
import com.api.wissenapi.Auth.LoginRequest;
import com.api.wissenapi.Auth.RegisterRequest;
import com.api.wissenapi.models.User.Role;
import com.api.wissenapi.models.User.UserModel;
import com.api.wissenapi.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    IUserRepository userRepository;
    private final JwtService jwtService;
    @Autowired
    MailService mailService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = (UserDetails) userRepository.findByEmail(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail((request.getEmail()))) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        UserModel user =UserModel.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
        /*try {
            userRepository.save(user);
            mailService.sendMessageUser(
                    request.getEmail(),
                    "Registro exitoso, Bienvenido a Wissen"
            );

            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();

        } catch (Exception e) {
           // e.printStackTrace();
            userRepository.delete(user);
            throw new RuntimeException("No se pudo enviar el correo. Registro cancelado.");
        }*/
    }
}
