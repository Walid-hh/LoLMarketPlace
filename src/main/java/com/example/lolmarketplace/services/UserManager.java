package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.Client;

import java.util.List;

public interface ClientManager {
    public Client getClient(String id);
    public List<Client> getClients();
    public Client addClient(Client client);

}
