package com.skwarek.shop.service;

import com.skwarek.shop.model.user.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Long accountId);

    Account create(Account accountRequest);

    void login(Account accountRequest);

    void logout();

    Account update(Long accountId, Account accountRequest);

    Account changeRole(Long accountId, Account accountRequest);

    void deleteById(Long accountId);

}
