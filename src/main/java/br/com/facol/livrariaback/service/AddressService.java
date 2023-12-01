package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAll(){
        return this.addressRepository.findAll();
    }

    public List<Address> getAddressesByClient(Long id){
        return this.addressRepository.getAddressesByClient(id);
    }

    public List<Address> getAddressByClient(Long client, Long addr){
        return this.addressRepository.getAddressByClient(client, addr);
    }

    public Address create(Address addr){
        return this.addressRepository.save(addr);
    }

    public Address update(Address addr){
        return this.addressRepository.save(addr);
    }
}
