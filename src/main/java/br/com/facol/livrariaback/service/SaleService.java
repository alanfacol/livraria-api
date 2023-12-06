package br.com.facol.livrariaback.service;

import br.com.facol.livrariaback.domain.*;
import br.com.facol.livrariaback.dto.*;
import br.com.facol.livrariaback.repository.AddressRepository;
import br.com.facol.livrariaback.repository.BookRepository;
import br.com.facol.livrariaback.repository.ClientRepository;
import br.com.facol.livrariaback.repository.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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
            List<Pack> packs = sale.getPacks();
            List<PackDTO> packDTOS = new ArrayList<>();

            saleDTO.setAddress(sale.getAddress());

            for (Pack pack : packs) {
                PackDTO packDTO = new PackDTO();
                packDTO.setBook(pack.getBook());
                packDTO.setAmount(pack.getAmount());
                packDTOS.add(packDTO);
            }

            saleDTOS.add(saleDTO.toSaleDTO(sale, packDTOS));
        }

        return saleDTOS;

    }

    public SaleCreateDTO create(String username, SaleCreateDTO saleCreateDTO) {
        Optional<Client> client = this.clientRepository.findByUsername(username);
        Address address = this.addressRepository.getAddressByClient(username, saleCreateDTO.getAddressId());

        if(client.isPresent()){
            List<Pack> packs = new ArrayList<>();
            for ( PackCreateDTO code: saleCreateDTO.getPacks()) {
                Optional<Book> book = this.bookRepository.findByCode(code.getBook());

                if(book.isPresent()){
                    Pack pack = new Pack();
                    pack.setBook(book.get());
                    pack.setAmount(code.getAmount());
                    packs.add(pack);
                }
            }

            this.saleRepository.save(saleCreateDTO.toSale(client.get(), address, packs));

        } else return null;

        return saleCreateDTO;

    }
}
