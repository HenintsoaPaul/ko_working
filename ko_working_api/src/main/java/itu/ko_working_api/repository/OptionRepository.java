package itu.ko_working_api.repository;

import itu.ko_working_api.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, String> {
    @Query("select generate_option_seq()")
    String generateId();
}
