package br.com.facol.livrariaback.controller;


import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.service.AddressService;
import br.com.facol.livrariaback.service.ClientService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
@NoArgsConstructor
@AllArgsConstructor
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Address> getAll(@RequestParam("client") Long id, @RequestParam(name = "addr", required = false) Long addr){
        if (addr == null){
            return this.addressService.getAddressesByClient(id);
        } else return this.addressService.getAddressByClient(id, addr);
    }

    @PostMapping
    public Address create(@RequestParam("client") Long id, @RequestBody Address addr){
        Optional<Client> client = this.clientService.findById(id);
        client.ifPresent(addr::setClient);
        return this.addressService.create(addr);
    }

    @PutMapping
    public Address update(@RequestParam("adrr") Long addr, @RequestBody Address address){
        return null;
    }
}
