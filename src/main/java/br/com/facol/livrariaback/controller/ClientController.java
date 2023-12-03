package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.dto.ClientCreateDTO;
import br.com.facol.livrariaback.dto.ClientDTO;
import br.com.facol.livrariaback.service.AuthenticationService;
import br.com.facol.livrariaback.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/me")
@NoArgsConstructor
@AllArgsConstructor
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping()
    @Secured("ROLE_USER")
    public ClientDTO getMe(){
        String username = this.authenticationService.getMyUsername();
        return this.clientService.findByUsername(username);
    }

    @PostMapping
    public ClientCreateDTO create(@RequestBody ClientCreateDTO client){
        return this.clientService.create(client);
    }

    @PutMapping()
    @Secured("ROLE_USER")
    public ClientDTO update(@RequestBody ClientDTO client) {
        String username = this.authenticationService.getMyUsername();
        return this.clientService.update(username, client);
    }
}
