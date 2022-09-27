package com.skwarek.shop.controller;

import com.skwarek.shop.model.user.Account;
import com.skwarek.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.findAll();

        if (!accounts.isEmpty()) {
            return ResponseEntity.ok(accounts);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/accounts/{username}")
    public ResponseEntity<Account> getAccountByUsername(@PathVariable("username") String username) {
        Account account = accountService.findByUsername(username);

        return ResponseEntity.ok(account);
    }

    @PostMapping(value = "/accounts/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account accountRequest) {
        Account createdAccount = accountService.create(accountRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{username}").buildAndExpand(createdAccount.getUsername()).toUri();

        return ResponseEntity.created(location).body(createdAccount);
    }

    @PutMapping(value = "/accounts/{username}")
    public ResponseEntity<Account> updateAccount(@PathVariable("username") String username,
                                                   @RequestBody Account accountRequest) {
        Account updatedAccount = accountService.update(username, accountRequest);

        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping(value = "/accounts/{username}")
    public ResponseEntity<HttpStatus> deleteAccountByUsername(@PathVariable("username") String username) {
        accountService.deleteByUsername(username);

        return ResponseEntity.noContent().build();
    }

}
