package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.Address;
import br.com.facol.livrariaback.domain.Book;
import br.com.facol.livrariaback.domain.Client;
import br.com.facol.livrariaback.domain.Sale;
import br.com.facol.livrariaback.dto.ClientDTO;
import br.com.facol.livrariaback.dto.SaleCreateDTO;
import br.com.facol.livrariaback.dto.SaleDTO;
import br.com.facol.livrariaback.repository.AddressRepository;
import br.com.facol.livrariaback.repository.BookRepository;
import br.com.facol.livrariaback.repository.ClientRepository;
import br.com.facol.livrariaback.repository.SaleRepository;
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
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<SaleDTO> getAll(String username) {
        List<Sale> sales = this.saleRepository.findAllByUsername(username);
        List<SaleDTO> saleDTOS = new ArrayList<>();

        for (Sale sale : sales) {
            SaleDTO saleDTO = new SaleDTO();
            saleDTOS.add(saleDTO.toSaleDTO(sale));
        }

        return saleDTOS;

    }

    public SaleCreateDTO create(String username, SaleCreateDTO saleCreateDTO) {
        Optional<Client> client = this.clientRepository.findByUsername(username);
        Address address = this.addressRepository.getAddressByClient(username, saleCreateDTO.getAddressId());
        List<Book> books = new ArrayList<>();

        if(client.isPresent()){
            for ( String code: saleCreateDTO.getBooksCode()) {
                Optional<Book> book = this.bookRepository.findByCode(code);
                if (book.isPresent()){
                    books.add(book.get());
                } else return null;
            }
            this.saleRepository.save(saleCreateDTO.toSale(client.get(), address, books));
        } else return null;

        return saleCreateDTO;

    }
}
