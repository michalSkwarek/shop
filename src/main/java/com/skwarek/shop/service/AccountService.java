package com.skwarek.shop.service;

import com.skwarek.shop.model.user.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findByEmail(String email);

    Account create(Account accountRequest);

    void login(Account accountRequest);

    void logout();

    Account update(String email, Account accountRequest);

    Account changeRole(String email, Account accountRequest);

    void deleteByEmail(String email);

}
