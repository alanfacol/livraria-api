package br.com.facol.livrariaback.controller.admin;

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
@RequestMapping("/books")
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

}
