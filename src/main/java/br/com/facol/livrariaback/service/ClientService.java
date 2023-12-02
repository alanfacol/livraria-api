package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.dto.ClientCreateDTO;
import br.com.facol.livrariaback.dto.ClientDTO;
import br.com.facol.livrariaback.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> getAll(){
        List<Client> clients = this.clientRepository.findAll();
        List<ClientDTO> clientsDTOS = new ArrayList<>();

        for (Client client : clients) {
            ClientDTO clientDTO = new ClientDTO();
            clientsDTOS.add(clientDTO.toClientDTO(client));
        }

        return clientsDTOS;
    }

    @Transactional
    public ClientCreateDTO create(ClientCreateDTO clientDTO){
        this.clientRepository.save(clientDTO.toClient());
        return clientDTO;
    }

    @Transactional
    public ClientDTO update(String username, ClientDTO clientDTO){
        Optional<Client> oldClient = this.clientRepository.findByUsername(username);
        if (oldClient.isPresent()){
            Client newClient = oldClient.get();
            this.clientRepository.save(clientDTO.toClient(newClient));
            return clientDTO;
        } else {
            return null;
        }
    }

    @Transactional
    public ClientDTO findByUsername(String username){
        Optional<Client> OptClient = this.clientRepository.findByUsername(username);
        if(OptClient.isPresent()){
            Client client = OptClient.get();
            return new ClientDTO().toClientDTO(client);
        } else return null;
    }
}
