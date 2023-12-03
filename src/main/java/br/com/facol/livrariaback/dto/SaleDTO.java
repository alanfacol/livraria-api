package br.com.facol.livrariaback.dto;


import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.domain.Book;
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
    private List<Book> books;

    public SaleDTO toSaleDTO(Sale sale){
        this.setDate(sale.getDate());
        this.setAddress(sale.getAddress());
        this.setBooks(sale.getBooks());

        return this;
    }
    public Sale toSale(Sale sale){
        sale.setDate(this.getDate());
        sale.setAddress(this.getAddress());
        sale.setBooks(this.getBooks());
        return sale;
    }
}
