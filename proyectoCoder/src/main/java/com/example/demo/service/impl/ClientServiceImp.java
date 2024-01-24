package com.example.demo.service.impl;

import com.example.demo.modal.ClientEntity;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    @Transactional
    public ClientEntity crear(ClientEntity client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientEntity> buscarPorId(String email) {
           return clientRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientEntity> clientes() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        clientRepository.deleteById(id);
    }
}
