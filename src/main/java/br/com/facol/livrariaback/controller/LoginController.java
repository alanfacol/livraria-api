package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.dto.LoginDTO;
import br.com.facol.livrariaback.service.AuthenticationService;
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
    @Autowired
    private AuthenticationService authenticationService;

    @PutMapping
    @Secured("ROLE_USER")
    public LoginDTO update(@RequestBody LoginDTO user){
        String username = this.authenticationService.getMyUsername();
        return this.loginService.update(username, user);
    }
}
