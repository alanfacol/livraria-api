package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.domain.Sale;
import br.com.facol.livrariaback.service.SaleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
@NoArgsConstructor
@AllArgsConstructor
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<Sale> getAll(){
        return this.saleService.getAll();
    }
}
