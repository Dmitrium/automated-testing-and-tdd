package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BranchTest {
    private static final Client TEST_CLIENT = new Client(UUID.randomUUID(), "John");
    private static final double POSITIVE_AMOUNT = 1.0;
    private static final UUID accountId = UUID.randomUUID();

    @Test
    public void shouldSaveEmployeeWhenAddedToBranch() {
        List dummyAccounts = Collections.EMPTY_LIST;
        Branch sut = new Branch(dummyAccounts);
        Employee employee = new Employee("name", 30);
        sut.addEmployee(employee);
        assertThat(sut.getEmployees().contains(employee));
    }

    @Test
    public void shouldSaveAccountsWhenBranchCreated() {
        List dummyAccounts = Collections.EMPTY_LIST;
        Branch sut = new Branch(dummyAccounts);
        assertThat(sut.getAccounts().isEmpty());
    }

    @Test
    public void shouldSaveAndRetrieveChildrenWhenAddChildren() {
        List dummyAccounts = Collections.EMPTY_LIST;
        Branch sut = new Branch(dummyAccounts);
        Branch childrenBranch = new Branch(dummyAccounts);
        sut.addChildren(childrenBranch);
        assertThat(sut.getChildren().contains(childrenBranch));
    }

    @Test
    public void shouldSaveAndRetrieveAccountsWhenAddAccounts() {
        Account account = new SavingAccount(accountId, TEST_CLIENT, POSITIVE_AMOUNT);
        List dummyAccounts = new ArrayList<>();
        Branch sut = new Branch(dummyAccounts);
        sut.addAccount(account);
        assertTrue(sut.getAccounts().contains(account));
    }

    @Test
    public void shouldChildrenBranchContainChildrenBranches(){
        List dummyAccounts = new ArrayList<>();
        Branch sut = new Branch(dummyAccounts);
        Branch childrenBranch = new Branch(dummyAccounts);
        Branch childrenChildrenBranch = new Branch(dummyAccounts);

        childrenBranch.addChildren(childrenChildrenBranch);
        sut.addChildren(childrenBranch);

        assertEquals(childrenChildrenBranch, sut.getChildren().iterator().next().getChildren().iterator().next());
    }

    @Test
    public void shouldChildrenBranchContainAccountAfterSave() {
        List dummyAccounts = new ArrayList<>();
        Branch sut = new Branch(dummyAccounts);
        Branch childrenBranch = new Branch(dummyAccounts);
        Account account = new SavingAccount(accountId, TEST_CLIENT, POSITIVE_AMOUNT);

        childrenBranch.addAccount(account);
        sut.addChildren(childrenBranch);

        assertEquals(account, sut.getChildren().iterator().next().getAccounts().iterator().next());
    }

}
