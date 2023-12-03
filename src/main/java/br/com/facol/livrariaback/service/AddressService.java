package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.dto.AddressDTO;
import br.com.facol.livrariaback.repository.AddressRepository;
import br.com.facol.livrariaback.repository.ClientRepository;
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

    @Autowired
    private ClientRepository clientRepository;

    public List<Address> getAddressesByClient(String username){
        return this.addressRepository.getAddressesByClient(username);
    }

    public List<Address> getAddressByClient(String username, Long addr){
        return this.addressRepository.getAddressByClient(username, addr);
    }

    public AddressDTO create(String username, AddressDTO addr){
        Optional<Client> OptClient = this.clientRepository.findByUsername(username);

        if (OptClient.isPresent()){
            Address newAddress = new Address();
            Client client = OptClient.get();
            newAddress.setClient(client);
            this.addressRepository.save(addr.toAddress(newAddress));
            return addr;
        } else return null;
    }

    public AddressDTO update(String username, Long addrId , AddressDTO addr){

        List<Address> clientAddresses = this.addressRepository.getAddressesByClient(username);

        for (Address add : clientAddresses) {
            if(Objects.equals(addrId, add.getId())){;
                this.addressRepository.save(addr.toAddress(add));
                return addr;
            }
        }
        return null;
    }

    public void delete(String clientId, Long addrId){
        List<Address> clientAddresses = this.addressRepository.getAddressesByClient(clientId);
        for (Address add : clientAddresses) {
            if (Objects.equals(addrId, add.getId())) {
                this.addressRepository.deleteById(addrId);
                break;
            }
        }
    }
}
