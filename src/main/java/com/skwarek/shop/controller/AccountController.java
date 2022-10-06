package com.skwarek.shop.controller;

import com.skwarek.shop.model.user.Account;
import com.skwarek.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/accounts/{email}")
    public ResponseEntity<Account> updateAccount(@PathVariable("email") String email,
                                                 @RequestBody Account accountRequest) {
        Account updatedAccount = accountService.update(email, accountRequest);

        return ResponseEntity.ok(updatedAccount);
    }

    @PutMapping(value = "/accounts/{email}/permission")
    public ResponseEntity<Account> changeRole(@PathVariable("email") String email,
                                              @RequestBody Account accountRequest) {
        Account updatedAccount = accountService.changeRole(email, accountRequest);

        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping(value = "/accounts/{email}")
    public ResponseEntity<HttpStatus> deleteAccountByEmail(@PathVariable("email") String email) {
        accountService.deleteByEmail(email);

        return ResponseEntity.noContent().build();
    }

}
