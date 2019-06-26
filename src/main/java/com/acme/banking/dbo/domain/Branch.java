package com.acme.banking.dbo.domain;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private Collection<Account> accounts; //TODO impl
    private Collection<Employee> employees = new HashSet<>();
    private Collection<Branch> childrens = new HashSet<>();

    public Branch(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public Collection<Branch> getChildren() {
        return Collections.unmodifiableCollection(childrens);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Collection<Object> getEmployees() {
        return Collections.unmodifiableCollection(employees);
    }

    public void addChildren(Branch childrenBranch) {
        childrens.add(childrenBranch);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
