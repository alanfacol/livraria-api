package br.com.facol.livrariaback.repository;

import br.com.facol.livrariaback.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select c.* from client c join login l on c.login_id = l.id where l.username = ?1", nativeQuery = true)
    Optional<Client> findByUsername(String username);
}
