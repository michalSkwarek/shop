package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.AccountNotFoundException;
import com.skwarek.shop.model.user.Account;
import com.skwarek.shop.model.user.Role;
import com.skwarek.shop.repository.AccountRepository;
import com.skwarek.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Not found account with username: " + username));
    }

    @Override
    public Account create(Account accountRequest) {
        Account newAccount = new Account();
        newAccount.setUsername(accountRequest.getUsername());
        newAccount.setPassword(accountRequest.getPassword());
        newAccount.setEmail(accountRequest.getEmail());
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setUpdatedAt(null);
        newAccount.setEnabled(true);
        newAccount.setRole(Role.USER);

        return accountRepository.save(newAccount);
    }

    @Override
    public Account update(String username, Account accountRequest) {
        Optional<Account> accountDb = accountRepository.findByUsername(username);

        if (accountDb.isPresent()) {
            Account oldAccount = accountDb.get();
            oldAccount.setUsername(accountRequest.getUsername());
            oldAccount.setPassword(accountRequest.getPassword());
            oldAccount.setEmail(accountRequest.getEmail());
            oldAccount.setCreatedAt(accountRequest.getCreatedAt());
            oldAccount.setUpdatedAt(LocalDateTime.now());
            oldAccount.setEnabled(accountRequest.getEnabled());
            oldAccount.setRole(accountRequest.getRole());

            return accountRepository.save(oldAccount);
        } else {
            throw new AccountNotFoundException("Not found account with username: " + username);
        }
    }

    @Override
    public void deleteByUsername(String username) {
        boolean isAccountExists = accountRepository.existsByUsername(username);

        if (isAccountExists) {
            accountRepository.deleteByUsername(username);
        } else {
            throw new AccountNotFoundException("Not found account with username: " + username);
        }
    }

}
