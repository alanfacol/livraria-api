package br.com.facol.livrariaback.repository;

import br.com.facol.livrariaback.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT a.* FROM address a WHERE a.client_id = ?1", nativeQuery = true)
    List<Address> getAddressesByClient(Long id);

    @Query(value = "SELECT a.* FROM address a WHERE a.id = ?1", nativeQuery = true)
    List<Address> getAddressByClient(Long client, Long addr);
}
