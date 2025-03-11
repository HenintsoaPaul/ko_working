package itu.ko_working_api.repository;

import itu.ko_working_api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    @Query("select generate_client_seq()")
    String generateId();

    Optional<Client> findByNumero(String numero);
}
