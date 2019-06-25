package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.repo.AccountRepository;
import com.acme.banking.dbo.repo.ClientRepository;

import java.util.Collection;
import java.util.UUID;

//TODO impl
public class Processing {

    private Cash cash;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    public Processing() {
    }

    public Processing(Cash cash, ClientRepository clientRepository, AccountRepository accountRepository) {
        this.cash = cash;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }


    public UUID createClient(String name) {
        return clientRepository.createClient(name);
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return accountRepository.getAccountsByClientId(clientId);
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        Account fromAccount = accountRepository.findAccountById(fromAccountId);
        Account toAccount = accountRepository.findAccountById(toAccountId);
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    public void cash(double amount, UUID fromAccountId) {
        cash.log(amount, fromAccountId);
    }

    public static ProcessingBuilder builder() {
        return new ProcessingBuilder();
    }

    public static class ProcessingBuilder {
        Processing processing;

        ProcessingBuilder() {
            processing = new Processing();
        }

        public ProcessingBuilder cash(Cash cash) {
            processing.cash = cash;
            return this;
        }

        public ProcessingBuilder clientRepository(ClientRepository clientRepository) {
            processing.clientRepository = clientRepository;
            return this;
        }

        public ProcessingBuilder accountRepository(AccountRepository accountRepository) {
            processing.accountRepository = accountRepository;
            return this;
        }

        public Processing build() {
            return processing;
        }

    }
}
