package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Login login){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Login usuario = (Login) authenticate.getPrincipal();

        Map<String, String> response = new HashMap<>();
        response.put("token", tokenService.gerarToken(usuario));
        return response;
    }

    @GetMapping("/test")
    public String testLogin(){
        return "Usuário autorizado!";
    }
}
