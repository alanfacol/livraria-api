package br.com.facol.livrariaback.repository;

import br.com.facol.livrariaback.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findByUsername(String username);

    @Query(value = "SELECT c.id from client c JOIN login l on c.login_id = l.id  where l.username = ?1",
            nativeQuery = true)
    Long findClientIdByUsername(String username);
}
