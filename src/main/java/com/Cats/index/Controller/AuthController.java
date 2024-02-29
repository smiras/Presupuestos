package com.Cats.index.Controller;

import com.Cats.index.Enum.Role;
import com.Cats.index.Request.LoginRequest;
import com.Cats.index.Request.RegisterRequest;
import com.Cats.index.Response.AuthResponse;
import com.Cats.index.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/create")
    public ResponseEntity<AuthResponse> create(){
        RegisterRequest request = new RegisterRequest("admin","admin","Administrador", Role.ADMIN);
        return ResponseEntity.ok(authService.register(request));
    }

}
