package br.com.facol.livrariaback.dto;

import br.com.facol.livrariaback.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String code;
    private String name;
    private String description;
    private Double value;
    private Long stock;

    public BookDTO toBookDto(Book book){
        this.setCode(book.getCode());
        this.setName(book.getName());
        this.setDescription(book.getDescription());
        this.setValue(book.getValue());
        this.setStock(book.getStock());

        return this;

    } public Book toBook(Book book){
        book.setCode(this.getCode());
        book.setName(this.getName());
        book.setDescription(this.getDescription());
        book.setValue(this.getValue());
        book.setStock(this.getStock());

        return book;

    }
}
