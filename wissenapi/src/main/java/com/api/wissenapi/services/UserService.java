package com.api.wissenapi.services;

import com.api.wissenapi.models.User.UserModel;
import com.api.wissenapi.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    MailService mailService;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user) {
        if (userRepository.existsByEmail((user.getEmail()))) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        try {
            mailService.sendMessageUser(
                    user.getEmail(),
                    "Registro exitoso, Bienvenido a Wissen"
            );
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo enviar el correo. Registro cancelado.");
        }
    }
    public UserModel loginUser(UserModel user) {
        UserModel existingUsers = userRepository.findFirstByEmail(user.getEmail());
        if (existingUsers == null) {
            throw new IllegalArgumentException("El usuario no existe");

        }
        if (!user.getPassword().equals(existingUsers.getPassword())) {
            throw new IllegalArgumentException("Constraseña Incorrecta");
        }
        return existingUsers;
    }

    public Optional<UserModel> getUserById(long id) {
        return userRepository.findById(id);
    }
    public UserModel updateByID(UserModel request, Long id ){
        UserModel userModel = userRepository.findById(id).get();
        userModel.setName(request.getName());
        userModel.setEmail(request.getEmail());
        userRepository.save(userModel);
        return userModel;
    }
}
