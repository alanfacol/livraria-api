package br.com.facol.livrariaback.controller;


import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.dto.AddressDTO;
import br.com.facol.livrariaback.dto.ClientDTO;
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
@RequestMapping("me/address")
@NoArgsConstructor
@AllArgsConstructor
public class AddressController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping()
    public List<AddressDTO> getAll(@RequestParam(name = "addr", required = false) Long addr){
        String username = this.authenticationService.getMyUsername();
        if (addr == null){
            return this.addressService.getAddressesByClient(username);
        } else return this.addressService.getAddressByClient(username, addr);
    }

    @PostMapping()
    @Secured("ROLE_USER")
    public AddressDTO create(@RequestBody AddressDTO addr){
        String username = this.authenticationService.getMyUsername();
        return this.addressService.create(username, addr);
    }

    @PutMapping()
    @Secured("ROLE_USER")
    public AddressDTO update(@RequestParam(name = "id") Long addrId, @RequestBody AddressDTO address){
        String username = this.authenticationService.getMyUsername();
        return this.addressService.update(username, addrId, address);
    }

    @DeleteMapping()
    @Secured("ROLE_USER")
    public void delete(@RequestParam(name = "id") Long addrId){
        String username = this.authenticationService.getMyUsername();
        this.addressService.delete(username, addrId);
    }
}
