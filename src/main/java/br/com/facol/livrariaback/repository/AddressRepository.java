package br.com.facol.livrariaback.repository;

import br.com.facol.livrariaback.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT a.* FROM address a " +
            "JOIN client c ON a.client_id = c.id " +
            "JOIN login l on c.login_id = l.id " +
            " WHERE l.username = ?1", nativeQuery = true)
    List<Address> getAddressesByClient(String username);

    @Query(value = "SELECT a.* FROM address a " +
            "JOIN client c ON a.client_id = c.id " +
            "JOIN login l on c.login_id = l.id " +
            "WHERE l.username = ?1 AND a.id = ?2", nativeQuery = true)
    List<Address> getAddressByClient(String username, Long addr);
}
