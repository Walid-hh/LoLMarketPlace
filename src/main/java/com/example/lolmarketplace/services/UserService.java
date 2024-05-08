package com.example.lolmarketplace.services;

import com.example.lolmarketplace.dao.entities.Client;
import com.example.lolmarketplace.dao.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService implements ClientManager{

    private ClientRepository clientRepository;
    @Override
    public Client getClient(String id) {
        Client client = clientRepository.findById(id).get();
        return client;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    @Override
    public Client addClient(Client client) {
        return null;
    }
}
