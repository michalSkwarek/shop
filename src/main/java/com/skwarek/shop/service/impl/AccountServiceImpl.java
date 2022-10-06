package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.AccountExistingException;
import com.skwarek.shop.exception.AccountNotFoundException;
import com.skwarek.shop.exception.AccountWrongPasswordException;
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
    public Account findByEmail(String email) {
        return accountRepository.findOptionalByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException("Not found account with email: " + email));
    }

    @Override
    public Account create(Account accountRequest) {
        boolean isAccountExists = accountRepository.existsByEmail(accountRequest.getEmail());

        if (!isAccountExists) {
            Account newAccount = new Account();
            newAccount.setEmail(accountRequest.getEmail());
            newAccount.setPassword(accountRequest.getPassword());
            newAccount.setNewsletter(accountRequest.getNewsletter());
            newAccount.setCreatedAt(LocalDateTime.now());
            newAccount.setUpdatedAt(null);
            newAccount.setRole(Role.USER);

            return accountRepository.save(newAccount);
        } else {
            throw new AccountExistingException("Existing account with email: " + accountRequest.getEmail());
        }
    }

    @Override
    public void login(Account accountRequest) {
        String email = accountRequest.getEmail();
        Optional<Account> accountDb = accountRepository.findOptionalByEmail(email);

        if (accountDb.isPresent()) {
            Account oldAccount = accountDb.get();

            if (oldAccount.getPassword().equals(accountRequest.getPassword())) {
                System.out.println("!!! Login success !!!");
            } else {
                throw new AccountWrongPasswordException("Wrong password for account with email: " + email);
            }
        } else {
            throw new AccountNotFoundException("Not found account with email: " + email);
        }
    }

    @Override
    public void logout() {
        System.out.println("!!! Logout success !!!");
    }

    @Override
    public Account update(String email, Account accountRequest) {
        Optional<Account> accountDb = accountRepository.findOptionalByEmail(email);

        if (accountDb.isPresent()) {
            Account oldAccount = accountDb.get();
            oldAccount.setEmail(accountRequest.getEmail());
            oldAccount.setPassword(accountRequest.getPassword());
            oldAccount.setNewsletter(accountRequest.getNewsletter());
            oldAccount.setUpdatedAt(LocalDateTime.now());

            return accountRepository.save(oldAccount);
        } else {
            throw new AccountNotFoundException("Not found account with email: " + email);
        }
    }

    @Override
    public Account changeRole(String email, Account accountRequest) {
        Optional<Account> accountDb = accountRepository.findOptionalByEmail(email);

        if (accountDb.isPresent()) {
            Account oldAccount = accountDb.get();
            oldAccount.setUpdatedAt(LocalDateTime.now());
            oldAccount.setRole(accountRequest.getRole());

            return accountRepository.save(oldAccount);
        } else {
            throw new AccountNotFoundException("Not found account with email: " + email);
        }
    }

    @Override
    public void deleteByEmail(String email) {
        boolean isAccountExists = accountRepository.existsByEmail(email);

        if (isAccountExists) {
            accountRepository.deleteByEmail(email);
        } else {
            throw new AccountNotFoundException("Not found account with email: " + email);
        }
    }

}
