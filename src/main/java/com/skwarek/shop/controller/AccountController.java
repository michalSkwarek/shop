package com.skwarek.shop.controller;

import com.skwarek.shop.model.user.Account;
import com.skwarek.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
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

    @GetMapping(value = "/accounts/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Long accountId) {
        Account account = accountService.findById(accountId);

        return ResponseEntity.ok(account);
    }

    @PutMapping(value = "/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable("accountId") Long accountId,
                                                 @RequestBody Account accountRequest) {
        Account updatedAccount = accountService.update(accountId, accountRequest);

        return ResponseEntity.ok(updatedAccount);
    }

    @PutMapping(value = "/accounts/{accountId}/permission")
    public ResponseEntity<Account> changeRole(@PathVariable("accountId") Long accountId,
                                              @RequestBody Account accountRequest) {
        Account updatedAccount = accountService.changeRole(accountId, accountRequest);

        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping(value = "/accounts/{accountId}")
    public ResponseEntity<HttpStatus> deleteAccountById(@PathVariable("accountId") Long accountId) {
        accountService.deleteById(accountId);

        return ResponseEntity.noContent().build();
    }

}
