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
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clientService.crear(client));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/client/findClients")
    public ResponseEntity<?> findAllClients (){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.clientes());
    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> client(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.buscarPorId(id));
    }

    @GetMapping("/client/edad/{id}")
    public ResponseEntity<?> getYears(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getYears(id));
    }
}
