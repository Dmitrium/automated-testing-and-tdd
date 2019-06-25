package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.repo.AccountRepository;
import com.acme.banking.dbo.repo.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private Cash cash;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private UUID clientID;
    private Processing sut;

    @Before
    public void setup() {
        cash = mock(Cash.class);
        clientRepository = mock(ClientRepository.class);
        accountRepository = mock(AccountRepository.class);
        clientID = UUID.randomUUID();
        sut = Processing.builder()
                .cash(cash)
                .accountRepository(accountRepository)
                .clientRepository(clientRepository)
                .build();
    }

    @Test
    public void createClientTest() {
        when(clientRepository.createClient(anyString())).thenReturn(clientID);
        assertEquals(clientID, sut.createClient("ClientName"));
    }

    @Test
    public void shouldGetAccountsOfClientByClientId() {
        when(accountRepository.getAccountsByClientId(any(UUID.class))).thenReturn(Collections.singletonList(any(Account.class)));
        assertEquals(1, sut.getAccountsByClientId(UUID.randomUUID()).size());
    }

    @Test
    public void shouldWithdrawFirstAccountAndDepositSecondAccount() {
        UUID fromAccountId = UUID.randomUUID();
        UUID toAccountId = UUID.randomUUID();
        Account fromAccount = mock(Account.class);
        Account toAccount = mock(Account.class);
        when(accountRepository.findAccountById(fromAccountId)).thenReturn(fromAccount);
        when(accountRepository.findAccountById(toAccountId)).thenReturn(toAccount);
        sut.transfer(1.0, fromAccountId, toAccountId);
        verify(accountRepository, times(2)).findAccountById(any(UUID.class));
        verify(fromAccount, times(1)).withdraw(anyDouble());
        verify(toAccount, times(1)).deposit(anyDouble());

    }
}
