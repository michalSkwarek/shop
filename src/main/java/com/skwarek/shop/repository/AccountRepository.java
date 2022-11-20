package com.skwarek.shop.repository;

import com.skwarek.shop.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String username);

    Optional<Account> findOptionalByEmail(String username);

}
