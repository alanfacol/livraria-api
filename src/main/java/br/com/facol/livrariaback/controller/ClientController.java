package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.service.AddressService;
import br.com.facol.livrariaback.service.ClientService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@NoArgsConstructor
@AllArgsConstructor
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> getAll(){
        return this.clientService.getAll();
    }

    @PostMapping("/client")
    public Client create(@RequestBody Client client){
        return this.clientService.create(client);
    }

    @PutMapping("/client")
    public Client update(@RequestParam("id") Long id, @RequestBody Client client) {
        return this.clientService.update(id, client);
    }
}
