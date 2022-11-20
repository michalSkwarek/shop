package com.skwarek.shop.controller;

import com.skwarek.shop.model.user.Account;
import com.skwarek.shop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/auth/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account accountRequest) {
        Account createdAccount = accountService.create(accountRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/accounts")
                .path("/{accountId}").buildAndExpand(createdAccount.getId()).toUri();

        return ResponseEntity.created(location).body(createdAccount);
    }

    @PostMapping(value = "/auth/login")
    public ResponseEntity<HttpStatus> loginAccount(@RequestBody Account accountRequest) {
        accountService.login(accountRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/auth/logout")
    public ResponseEntity<HttpStatus> logoutAccount() {
        accountService.logout();

        return ResponseEntity.ok().build();
    }

}
