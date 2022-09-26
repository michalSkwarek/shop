package com.skwarek.shop.service;

import com.skwarek.shop.model.user.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findByUsername(String username);

    Account create(Account accountRequest);

    Account update(String username, Account accountRequest);

    void deleteByUsername(String username);

}
