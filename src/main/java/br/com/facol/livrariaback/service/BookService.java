package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Book;
import br.com.facol.livrariaback.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public List<Book> getAll(){
        return this.bookRepository.findAll();
    }

    @Transactional
    public Optional<Book> getById(Long id){
        return this.bookRepository.findById(id);
    }

    @Transactional
    public Book create(Book book){
        return this.bookRepository.save(book);
    }

    public Book update(Long id, Book book){
        Optional<Book> oldBook = this.bookRepository.findById(id);
        if (oldBook.isPresent()){
            Book newBook = oldBook.get();

            newBook.setId(id);
            newBook.setCode(book.getCode());
            newBook.setName(book.getName());
            newBook.setDescription(book.getDescription());
            newBook.setValue(book.getValue());
            newBook.setStock(book.getStock());


            return this.bookRepository.save(newBook);
        } else {
            return null;
        }
    }

    public void delete(Long id){
        Optional<Book> book = this.bookRepository.findById(id);

        if(book.isPresent()){
            this.bookRepository.deleteById(id);
        }
    }
}
