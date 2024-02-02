package com.example.demo.service;

import com.example.demo.modal.ClientEntity;
import com.example.demo.modal.dto.ClientDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientEntity crear(ClientEntity client);

    Optional<ClientEntity> buscarPorId(long id);

    List<ClientEntity> clientes();

    void eliminarCliente(Long id);


    ClientDto getYears(Long id);
}
