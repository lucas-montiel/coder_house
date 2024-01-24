package com.example.demo.controllers;

import com.example.demo.modal.ClientEntity;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/client/create")
    public ResponseEntity<?> createClient(@RequestBody ClientEntity client){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.crear(client));
    }

    @GetMapping("/client/findClients")
    public ResponseEntity<?> findAllClients (){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.clientes());
    }

}
