package com.example.demo.repository;

import com.example.demo.modal.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
