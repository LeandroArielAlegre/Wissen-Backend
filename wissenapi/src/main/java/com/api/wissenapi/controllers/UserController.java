package com.api.wissenapi.controllers;

import com.api.wissenapi.Auth.AuthResponse;
import com.api.wissenapi.Auth.LoginRequest;
import com.api.wissenapi.Auth.RegisterRequest;
import com.api.wissenapi.services.AuthService;
import com.api.wissenapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    /*
    @GetMapping(path ="/getAllUsers")
    public ArrayList<UserModel> getUsers() {
        return this.userService.getUsers();
    }*/

    @PostMapping(path ="/register")
    public ResponseEntity<AuthResponse> saveUser(@RequestBody RegisterRequest request) {
        try {
            //UserModel savedUser = this.userService.saveUser(request);
            return ResponseEntity.ok(authService.register(request));
        }catch (IllegalArgumentException e) {
            return  ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new AuthResponse(e.getMessage()));

        }

    }

    @PostMapping(path ="/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request) {
        try {
            //UserModel loginUser = this.userService.loginUser(request);
            return ResponseEntity.ok(authService.login(request));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new AuthResponse(e.getMessage()));
        }

    }
    /*
    @GetMapping(path ="/getUserById{id}")
    public Optional<UserModel>  getUserById(@PathVariable long id) {
        return this.userService.getUserById(id);
    }

    @PutMapping(path = "/updateUserById{id}")
    public UserModel updateUserById(@RequestBody UserModel request ,@PathVariable Long id){
        return this.userService.updateByID(request, id);
    }*/



}
