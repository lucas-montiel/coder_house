package com.example.demo.controllers;

import com.example.demo.modal.ClientEntity;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    /**
     * Devuelve todos los clientes de la base de datos
     * @return ResponseEntity<?>
     */
    @GetMapping("/findClients")
    public ResponseEntity<?> findAllClients (){
        try{
            List<ClientEntity> clients = clientService.clientes();
            if(!clients.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(clientService.clientes());
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe ningun cliente en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Devuelve un cliente por el id si existe en la base de datos
     * @param id
     * @return ResponseEntity<?>
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> client(@PathVariable long id){
        try{
            Optional<ClientEntity> client = clientService.buscarPorId(id);
            if(client.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(client);
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe el cliente en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Se obtiene una entidad cliente y un id para modificar el cliente si existe en la base de datos
     * @param client
     * @param id
     * @return ResponseEntity<?>
     */
    @PutMapping("/modify/{id}")
    public ResponseEntity<?> client(@RequestBody ClientEntity  client, @PathVariable Long id){
        try{

            Optional<ClientEntity> clientEntity = clientService.buscarPorId(id);
            if(clientEntity.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(clientService.modifyClient(client, id));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe el cliente en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Devuelve la edad del cliente pasando el id
     * @param id
     * @return ResponseEntity<?>
     */
    @GetMapping("/edad/{id}")
    public ResponseEntity<?> getYears(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(clientService.getYears(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
