package br.com.facol.livrariaback.controller;


import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(@RequestBody Client client){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(client.getUsername(), client.getPassword());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Client usuario = (Client) authenticate.getPrincipal();
        return tokenService.gerarToken(usuario);
    }
}
