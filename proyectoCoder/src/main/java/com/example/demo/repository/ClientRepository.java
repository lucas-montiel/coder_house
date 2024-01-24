package com.example.demo.repository;

import com.example.demo.modal.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("Select * FROM client WHERE email = {email}")
    Optional<ClientEntity> findByEmail(String email);
}
