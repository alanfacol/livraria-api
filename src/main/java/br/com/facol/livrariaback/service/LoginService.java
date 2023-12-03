package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.dto.LoginDTO;
import br.com.facol.livrariaback.repository.LoginRepository;
import br.com.facol.livrariaback.utils.PassCrypt;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public List<LoginDTO> getAll(){
        List<Login> logins = this.loginRepository.findAll();
        List<LoginDTO> loginDTOS = new ArrayList<>();

        for (Login login: logins) {
            loginDTOS.add(new LoginDTO().toLoginDTO(login));
        }

        return loginDTOS;
    }

    public LoginDTO create(String username, LoginDTO user){
        Optional<Login> optLogin = this.loginRepository.findUserByUsername(username);

        if(optLogin.isEmpty()) {
            Login newUser = new Login();
            newUser.setAdmin(true);
            this.loginRepository.save(user.toLogin(newUser));
        }

        return user;
    }

    public LoginDTO update(String username, LoginDTO user){
        Optional<Login> oldLogin = this.loginRepository.findUserByUsername(username);

        if (oldLogin.isPresent()){
            Login newUser = oldLogin.get();
            this.loginRepository.save(user.toLogin(newUser));
            return user;
        } else return null;
    }

    public void delete(String username){
        Optional<Login> user = this.loginRepository.findUserByUsername(username);
        user.ifPresent(login -> this.loginRepository.deleteById(login.getId()));
    }
}
