package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.domain.Book;
import br.com.facol.livrariaback.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
@NoArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<Book> getAll(@RequestParam(name = "id", required = false) Long id ){
       return this.bookService.getAll();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public Book create(@RequestBody Book book){
        return this.bookService.create(book);
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    public Book create(@RequestParam(name = "id") Long id, @RequestBody Book book){
        return this.bookService.update(id, book);
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public void delete(@RequestParam(name = "id") Long id){
        this.bookService.delete(id);
    }
}
