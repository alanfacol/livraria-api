package br.com.facol.livrariaback.dto;

import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.utils.PassCrypt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreateDTO {
    private String name;
    private String document;
    private Date birthdate;
    private boolean isPj;
    private String username;
    private String password;

    public Client toClient(){
        Client client = new Client();
        client.setName(this.getName());
        client.setDocument(this.getDocument());
        client.setBirthdate(this.getBirthdate());
        Login user = new Login();
        user.setUsername(this.getUsername());
        user.setPassword(PassCrypt.encrypt(this.getPassword()));
        client.setLogin(user);

        return client;
    }


}
