package com.acme.banking.dbo.repo;

import java.util.UUID;

public interface ClientRepository {

//    private List<Client> clients = new ArrayList<>();

    UUID createClient(String name);
}
