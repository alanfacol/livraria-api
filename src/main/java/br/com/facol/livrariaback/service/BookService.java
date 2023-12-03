package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Book;
import br.com.facol.livrariaback.dto.BookDTO;
import br.com.facol.livrariaback.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public List<BookDTO> getAll(){
        List<Book> books = this.bookRepository.findAll();
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book : books) {
            bookDTOS.add(new BookDTO().toBookDto(book));
        }

        return bookDTOS;
    }

    @Transactional
    public BookDTO getByCode(String code){
        Optional<Book> optBook = this.bookRepository.findByCode(code);
        return optBook.map(book -> new BookDTO().toBookDto(book)).orElse(null);
    }

    @Transactional
    public BookDTO create(BookDTO bookDto){
        Optional<Book> optBook = this.bookRepository.findByCode(bookDto.getCode());

        if(optBook.isEmpty()) {
            this.bookRepository.save(bookDto.toBook(new Book()));
            return bookDto;

        } else return null;
    }

    public BookDTO update(BookDTO book){
        Optional<Book> oldBook = this.bookRepository.findByCode(book.getCode());
        if (oldBook.isPresent()){
            Book newBook = oldBook.get();
            this.bookRepository.save(book.toBook(newBook));
            return book;
        } else return null;
    }

    public void delete(String code){
        Optional<Book> book = this.bookRepository.findByCode(code);
        book.ifPresent(value -> this.bookRepository.deleteById(value.getId()));
    }
}
