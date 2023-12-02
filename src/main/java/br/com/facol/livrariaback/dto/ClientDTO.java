package br.com.facol.livrariaback.dto;

import br.com.facol.livrariaback.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private String name;
    private String document;
    private Date birthdate;
    private boolean isPj;

    public ClientDTO toClientDTO(Client client){
        this.setName(client.getName());
        this.setDocument(client.getDocument());
        this.setBirthdate(client.getBirthdate());
        this.setPj(client.isPj());

        return this;
    }

    public Client toClient(Client client){
        client.setName(this.getName());
        client.setDocument(this.getDocument());
        client.setBirthdate(this.getBirthdate());
        client.setPj(this.isPj());

        return client;
    }
}
