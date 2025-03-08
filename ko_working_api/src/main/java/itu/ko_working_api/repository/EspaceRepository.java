package itu.ko_working_api.repository;

import itu.ko_working_api.entity.Espace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EspaceRepository extends JpaRepository<Espace, String> {
    @Query("select generate_espace_seq()")
    String generateId();
}
