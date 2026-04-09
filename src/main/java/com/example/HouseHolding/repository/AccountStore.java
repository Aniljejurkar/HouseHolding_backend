package com.example.HouseHolding.repository;

import com.example.HouseHolding.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // ✅ ADD THIS

public interface AccountStore extends JpaRepository<Account , Long> {
    Optional<Account> findByEmail(String email);
}
