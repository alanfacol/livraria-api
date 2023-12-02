package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Sale;
import br.com.facol.livrariaback.repository.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> getAll(){
        return this.saleRepository.findAll();
    }
}
