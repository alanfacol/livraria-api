package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.repository.LoginRepository;
import br.com.facol.livrariaback.utils.PassCrypt;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public List<Login> getAll(){
        return this.loginRepository.findAll();
    }

    @Transactional
    public Optional<Login> findById(Long id){
        return this.loginRepository.findById(id);
    }

    public Login create(Login user){
        return this.loginRepository.save(user);
    }

    public Login update(Long id, Login newLogin){
        Optional<Login> oldLogin = this.findById(id);

        if (oldLogin.isPresent()){
            Login login = oldLogin.get();

            login.setId(id);
            login.setUsername(newLogin.getUsername());
            login.setPassword(PassCrypt.encrypt(newLogin.getPassword()));
            login.setAdmin(newLogin.isAdmin());

            return this.loginRepository.save(login);
        } else return null;
    }

    public void delete(Long id){
        try{
            this.loginRepository.deleteById(id);
        } catch (Exception ignored){
        }
    }
}
