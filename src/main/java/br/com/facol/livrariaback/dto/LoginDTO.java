package br.com.facol.livrariaback.dto;

import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.utils.PassCrypt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String username;
    private String password;

    public LoginDTO toLoginDTO(Login login){
        this.setUsername(login.getUsername());
        this.setPassword(PassCrypt.encrypt(login.getPassword()));

        return this;
    }

    public Login toLogin(Login login){
        login.setUsername(login.getUsername());
        login.setPassword(PassCrypt.encrypt(login.getPassword()));
        login.setAdmin(login.isAdmin());

        return login;
    }
}
