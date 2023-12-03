package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.domain.Sale;
import br.com.facol.livrariaback.dto.SaleCreateDTO;
import br.com.facol.livrariaback.dto.SaleDTO;
import br.com.facol.livrariaback.service.AuthenticationService;
import br.com.facol.livrariaback.service.SaleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/me/sales")
@NoArgsConstructor
@AllArgsConstructor
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    @Secured("ROLE_USER")
    public List<SaleDTO> getAll() {
        String username = this.authenticationService.getMyUsername();
        return this.saleService.getAll(username);
    }

    @PostMapping
    @Secured("ROLE_USER")
    public SaleCreateDTO create(@RequestBody SaleCreateDTO saleCreateDTO){
        String username = this.authenticationService.getMyUsername();
        this.saleService.create(username, saleCreateDTO);
        return saleCreateDTO;
    }
}
