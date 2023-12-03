package br.com.facol.livrariaback.dto;


import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.domain.Book;
import br.com.facol.livrariaback.domain.Client;
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
public class SaleCreateDTO {
    private Long addressId;
    private List<String> booksCode;

    public Sale toSale(Client client, Address address, List<Book> books) {
        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setClient(client);
        sale.setAddress(address);
        sale.setBooks(books);

        return sale;
    }
}
