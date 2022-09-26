package com.skwarek.shop.service;

import com.skwarek.shop.model.user.Account;
import org.springframework.stereotype.Service;

public interface AccountService {

    Account findById(Long accountId);

    Account findByUsername(String username);

}
