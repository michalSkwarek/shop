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

    @GetMapping(value = "/accounts/{email}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable("email") String email) {
        Account account = accountService.findByEmail(email);

        return ResponseEntity.ok(account);
    }

    @PostMapping(value = "/accounts/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account accountRequest) {
        Account createdAccount = accountService.create(accountRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{username}").buildAndExpand(createdAccount.getEmail()).toUri();

        return ResponseEntity.created(location).body(createdAccount);
    }

    @PutMapping(value = "/accounts/{email}")
    public ResponseEntity<Account> updateAccount(@PathVariable("email") String email,
                                                 @RequestBody Account accountRequest) {
        Account updatedAccount = accountService.update(email, accountRequest);

        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping(value = "/accounts/{email}")
    public ResponseEntity<HttpStatus> deleteAccountByEmail(@PathVariable("email") String email) {
        accountService.deleteByEmail(email);

        return ResponseEntity.noContent().build();
    }

}
