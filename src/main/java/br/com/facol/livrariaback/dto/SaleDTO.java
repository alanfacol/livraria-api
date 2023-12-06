package br.com.facol.livrariaback.dto;


import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.domain.Pack;
import br.com.facol.livrariaback.domain.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SaleDTO {
    private Date date;
    private Address address;
    private List<PackDTO> packs;

    public SaleDTO toSaleDTO(Sale sale, List<PackDTO> packs) {
        this.setDate(sale.getDate());
        this.setAddress(sale.getAddress());
        this.setPacks(packs);

        return this;
    }
}
