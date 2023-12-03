package br.com.facol.livrariaback.controller;

import br.com.facol.livrariaback.domain.Book;
import br.com.facol.livrariaback.dto.BookDTO;
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
    @Secured("ROLE_USER")
    public List<BookDTO> getAll(){
       return this.bookService.getAll();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public BookDTO create(@RequestBody BookDTO book){
        return this.bookService.create(book);
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    public BookDTO update(@RequestBody BookDTO book){
        return this.bookService.update(book);
    }

    @DeleteMapping
    @Secured("ROLE_ADMIN")
    public void delete(@RequestParam(name = "code") String code){
        this.bookService.delete(code);
    }
}
