package br.com.facol.livrariaback.controller;


import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.service.AddressService;
import br.com.facol.livrariaback.service.AuthenticationService;
import br.com.facol.livrariaback.service.ClientService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
@NoArgsConstructor
@AllArgsConstructor
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<Address> getAll(@RequestParam("client") Long id, @RequestParam(name = "addr", required = false) Long addr){
        if (addr == null){
            return this.addressService.getAddressesByClient(id);
        } else return this.addressService.getAddressByClient(id, addr);
    }

    @GetMapping("/me")
    public List<Address> getAll(@RequestParam(name = "id", required = false) Long addr){
        Long id = this.authenticationService.getMyId();
        if (addr == null){
            return this.addressService.getAddressesByClient(id);
        } else return this.addressService.getAddressByClient(id, addr);
    }

    @PostMapping
    @Secured("ROLE_USER")
    public Address create(@RequestBody Address addr){
        Long id = this.authenticationService.getMyId();
        Optional<Client> client = this.clientService.findById(id);
        client.ifPresent(addr::setClient);
        return this.addressService.create(addr);
    }

    @PutMapping
    @Secured("ROLE_USER")
    public Address update(@RequestParam(name = "id") Long addrId, @RequestBody Address address){
        Long id = this.authenticationService.getMyId();
        return this.addressService.update(id, addrId, address);
    }

    @DeleteMapping
    @Secured("ROLE_USER")
    public void delete(@RequestParam(name = "id") Long addrId){
        Long id = this.authenticationService.getMyId();
        this.addressService.delete(id, addrId);
    }
}
