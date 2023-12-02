package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Address update(Long clientId, Long addrId , Address addr){

        List<Address> clientAddresses = this.addressRepository.getAddressesByClient(clientId);

        for (Address add : clientAddresses) {
            if(Objects.equals(addrId, add.getId())){
                add.setId(addrId);
                add.setPublicPlace(addr.getPublicPlace());
                add.setNumber(addr.getNumber());
                add.setComplement(addr.getComplement());
                add.setDistrict(addr.getDistrict());
                add.setCity(addr.getCity());
                add.setZipCode(addr.getZipCode());
                add.setState(addr.getState());

                return this.addressRepository.save(add);
            }
        }
        return null;
    }

    public void delete(Long clientId, Long addrId){
        List<Address> clientAddresses = this.addressRepository.getAddressesByClient(clientId);
        for (Address add : clientAddresses) {
            if (Objects.equals(addrId, add.getId())) {
                this.addressRepository.deleteById(addrId);
                break;
            }
        }
    }
}
