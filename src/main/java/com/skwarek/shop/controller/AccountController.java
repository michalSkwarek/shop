package com.skwarek.shop.controller;

import com.skwarek.shop.model.user.Account;
import com.skwarek.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

//    @GetMapping(value = "/{accountId}")
//    public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Long accountId) {
//        Account account = accountService.findById(accountId);
//
//        return ResponseEntity.ok(account);
//    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Account> getAccountById(@PathVariable("username") String username) {
        Account account = accountService.findByUsername(username);

        return ResponseEntity.ok(account);
    }

}
