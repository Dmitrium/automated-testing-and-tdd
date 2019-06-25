package com.acme.banking.dbo.repo;

import com.acme.banking.dbo.domain.Account;

import java.util.Collection;
import java.util.UUID;

public interface AccountRepository {

//    private List<Client> clients = new ArrayList<>();
    Collection<Account> getAccountsByClientId(UUID clientId);
    Account findAccountById(UUID uuid);
    void save(Account account);

}
