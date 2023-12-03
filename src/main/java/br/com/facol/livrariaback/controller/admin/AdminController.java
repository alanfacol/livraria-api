package br.com.facol.livrariaback.controller.admin;


import br.com.facol.livrariaback.domain.Login;
import br.com.facol.livrariaback.dto.AddressDTO;
import br.com.facol.livrariaback.dto.BookDTO;
import br.com.facol.livrariaback.dto.ClientDTO;
import br.com.facol.livrariaback.dto.LoginDTO;
import br.com.facol.livrariaback.service.AddressService;
import br.com.facol.livrariaback.service.BookService;
import br.com.facol.livrariaback.service.ClientService;
import br.com.facol.livrariaback.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@NoArgsConstructor
public class AdminController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private BookService bookService;

    @GetMapping("/addresses")
    @Secured("ROLE_ADMIN")
    public List<AddressDTO> getAllAddresses(@RequestParam("username") String username, @RequestParam(name = "addr", required = false) Long addr) {
        if (addr == null){
            return this.addressService.getAddressesByClient(username);
        } else return this.addressService.getAddressByClient(username, addr);
    }

    @GetMapping("/clients")
    @Secured("ROLE_ADMIN")
    public List<ClientDTO> getAllClients(){
        return this.clientService.getAll();
    }

    @GetMapping("/users")
    @Secured("ROLE_ADMIN")
    public List<LoginDTO> getAllUsers(){
        return this.loginService.getAll();
    }
    @PostMapping("/users")
    @Secured("ROLE_ADMIN")
    public LoginDTO createUser(@RequestParam(name = "username") String username, @RequestBody LoginDTO user){
        return this.loginService.create(username, user);
    }

    @PutMapping("/users")
    @Secured("ROLE_ADMIN")
    public LoginDTO updateUser(@RequestParam(name = "username") String username, @RequestBody LoginDTO user){
        return this.loginService.update(username, user);
    }

    @DeleteMapping("/users")
    @Secured("ROLE_ADMIN")
    public void deleteUser(@RequestParam(name = "username") String username){
        this.loginService.delete(username);
    }

    @PostMapping("/books")
    @Secured("ROLE_ADMIN")
    public BookDTO create(@RequestBody BookDTO book){
        return this.bookService.create(book);
    }

    @PutMapping("/books")
    @Secured("ROLE_ADMIN")
    public BookDTO update(@RequestBody BookDTO book){
        return this.bookService.update(book);
    }

    @DeleteMapping("/books")
    @Secured("ROLE_ADMIN")
    public void delete(@RequestParam(name = "code") String code){
        this.bookService.delete(code);
    }

    @GetMapping("/auth/test")
    @Secured("ROLE_ADMIN")
    public String testAdmin(){
        return "Usuário admin autenticado!";
    }
}
