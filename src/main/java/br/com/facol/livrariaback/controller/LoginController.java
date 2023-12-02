package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@NoArgsConstructor
@AllArgsConstructor
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<Login> getAll(){
        return this.loginService.getAll();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public Login create(@RequestBody Login user){
        return this.loginService.create(user);
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    public Login update(@RequestParam(name = "id") Long id, @RequestBody Login user){
        return this.loginService.update(id, user);
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public void delete(@RequestParam(name = "id") Long id){
        this.loginService.delete(id);
    }


}
