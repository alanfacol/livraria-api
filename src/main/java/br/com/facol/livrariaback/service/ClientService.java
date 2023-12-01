package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return this.clientRepository.findAll();
    }

    @Transactional
    public Client create(Client client){
        return this.clientRepository.save(client);
    }

    @Transactional
    public Client update(Long id, Client client){
        Optional<Client> oldClient = this.clientRepository.findById(id);
        if (oldClient.isPresent()){
            Client newClient = oldClient.get();

            newClient.setId(id);
            newClient.setName(client.getName());
            newClient.setDocument(client.getDocument());
            newClient.setPj(client.isPj());
            newClient.setBirthdate(client.getBirthdate());

            Login login = newClient.getLogin();
            login.setUsername(client.getLogin().getUsername());
            login.setPassword(client.getLogin().getPassword());
            login.setAdmin(client.getLogin().isAdmin());

            newClient.setLogin(login);

            return this.clientRepository.save(newClient);
        } else {
            return null;
        }
    }

    @Transactional
    public Optional<Client> findById(Long id){
        return this.clientRepository.findById(id);
    }
}
