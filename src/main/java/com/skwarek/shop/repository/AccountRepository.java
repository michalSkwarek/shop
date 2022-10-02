package com.skwarek.shop.repository;

import com.skwarek.shop.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByUsername(String username);

    Optional<Account> findOptionalByUsername(String username);

    void deleteByUsername(String username);

}
