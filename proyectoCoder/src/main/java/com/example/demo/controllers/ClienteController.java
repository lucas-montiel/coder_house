package com.example.demo.controllers;

import com.example.demo.modal.ClientEntity;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody ClientEntity client){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clientService.crear(client));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/findClients")
    public ResponseEntity<?> findAllClients (){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.clientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> client(@PathVariable long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clientService.buscarPorId(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<?> client(@RequestBody ClientEntity  client, @PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clientService.modifyClient(client, id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/edad/{id}")
    public ResponseEntity<?> getYears(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clientService.getYears(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
