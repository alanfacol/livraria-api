package br.com.facol.livrariaback.dto;


import br.com.facol.livrariaback.domain.*;
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
public class SaleCreateDTO {
    private Long addressId;
    private List<PackCreateDTO> packs;

    public Sale toSale(Client client, Address address, List<Pack> packs) {
        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setClient(client);
        sale.setAddress(address);
        sale.setPacks(packs);

        return sale;
    }
}

