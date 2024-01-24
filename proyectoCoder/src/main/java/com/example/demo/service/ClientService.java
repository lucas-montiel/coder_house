package com.example.demo.service;

import com.example.demo.modal.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientEntity crear(ClientEntity client);

    Optional<ClientEntity> buscarPorId(String email);

    List<ClientEntity> clientes();

    void eliminarCliente(Long id);


}
