package com.example.HouseHolding.service;

import com.example.HouseHolding.dto.LoginPayload;
import com.example.HouseHolding.dto.RegisterPayload;
import com.example.HouseHolding.entity.Account;
import com.example.HouseHolding.repository.AccountStore;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {
    private final AccountStore accountStore;
    private final PasswordEncoder passwordEncoder;

    public LoginManager(AccountStore accountStore, PasswordEncoder passwordEncoder) {
        this.accountStore = accountStore;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ REGISTER
    public Account register(RegisterPayload payload) {

        Account acc = new Account();
        acc.setName(payload.getName());
        acc.setEmail(payload.getEmail());
        acc.setPassword(passwordEncoder.encode(payload.getPassword()));
        acc.setRole("customer");

        return accountStore.save(acc);
    }

    // ✅ LOGIN
    public Account validateUser(LoginPayload payload) {

        Account acc = accountStore.findByEmail(payload.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(payload.getPassword(), acc.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return acc;
    }
}