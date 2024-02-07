package com.example.demo.service.impl;

import com.example.demo.modal.ClientEntity;
import com.example.demo.modal.dto.ClientDto;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public Optional<ClientEntity> buscarPorId(long id) {
           return clientRepository.findById(id);
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

    @Override
    public ClientEntity modifyClient(ClientEntity client, Long id) {
        Optional<ClientEntity> clientRepo = clientRepository.findById(id);
        if(clientRepo.isPresent()){
            ClientEntity clientDB = clientRepo.get();
            clientDB.setEmail(client.getEmail());
            clientDB.setNombre(client.getNombre());
            clientDB.setApellido(client.getApellido());
            clientDB.setFechaNacimiento(client.getFechaNacimiento());
            return clientRepository.save(clientDB);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto getYears(Long id) {
        Date fechaActual = new Date();
        // se trabaja con milisegundos para obtener los anios
        long milisegundosPorAnio = 1000L * 60 * 60 * 24 * 365;
        Optional<ClientEntity> clientOpt = clientRepository.findById(id);
        ClientDto clientResponse = new ClientDto();
        if(clientOpt.isPresent()){
            ClientEntity client = clientOpt.get();
            long anios = (fechaActual.getTime()- client.getFechaNacimiento().getTime())/ milisegundosPorAnio;
            clientResponse.setNombre(client.getNombre());
            clientResponse.setApellido(client.getApellido());
            clientResponse.setEdad(anios);
        }
        return clientResponse;
    }
}
