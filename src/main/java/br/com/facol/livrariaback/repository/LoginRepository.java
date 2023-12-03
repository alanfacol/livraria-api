package br.com.facol.livrariaback.repository;

import br.com.facol.livrariaback.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findByUsername(String username);

    Optional<Login> findUserByUsername(String username);

}
