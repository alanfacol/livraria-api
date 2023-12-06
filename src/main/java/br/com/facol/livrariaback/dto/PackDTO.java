package br.com.facol.livrariaback.dto;


import br.com.facol.livrariaback.domain.Book;
import br.com.facol.livrariaback.domain.Pack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackDTO {
    private Book book;
    private int amount;

    private PackDTO toPack(Pack pack) {
        this.setBook(pack.getBook());
        this.setAmount(pack.getAmount());

        return this;
    }

}

