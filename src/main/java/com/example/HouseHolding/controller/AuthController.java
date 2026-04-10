package com.example.HouseHolding.controller;

import com.example.HouseHolding.dto.LoginPayload;
import com.example.HouseHolding.dto.RegisterPayload;
import com.example.HouseHolding.entity.Account;
import com.example.HouseHolding.service.LoginManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final LoginManager loginManager;

    public AuthController(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    // ✅ REGISTER API
    @PostMapping("/register")
    public Account register(@RequestBody RegisterPayload payload) {
        return loginManager.register(payload);
    }

    // ✅ LOGIN API
    @PostMapping("/login")
    public Account login(@RequestBody LoginPayload payload) {
        return loginManager.validateUser(payload);
    }
}
