package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.service.AuthenticationService;
import br.com.facol.livrariaback.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@NoArgsConstructor
@AllArgsConstructor
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping()
    @Secured("ROLE_ADMIN")
    public List<Client> getAll(){
        return this.clientService.getAll();
    }

    @GetMapping("/me")
    public Client getMe(){
        Long id = this.authenticationService.getMyId();
        Optional<Client> client = this.clientService.findById(id);
        return client.orElse(null);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public Client create(@RequestBody Client client){
        return this.clientService.create(client);
    }

    @PutMapping
    @Secured("ROLE_USER")
    public Client update(@RequestBody Client client) {
        Long id = this.authenticationService.getMyId();
        return this.clientService.update(id, client);
    }
}
