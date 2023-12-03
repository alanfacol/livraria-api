package br.com.facol.livrariaback.repository;

import br.com.facol.livrariaback.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT s.* FROM sale s " +
            "JOIN client c on s.client_id = c.id " +
            "JOIN login l on c.login_id = l.id " +
            "WHERE l.username = ?1", nativeQuery = true)
    List<Sale> findAllByUsername(String username);

}
