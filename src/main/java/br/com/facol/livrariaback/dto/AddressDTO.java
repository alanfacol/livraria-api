package br.com.facol.livrariaback.dto;


import br.com.facol.livrariaback.domain.Address;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String publicPlace;
    private String number;
    private String complement;
    private String district;
    private String zipCode;
    private String city;
    private String state;

    public AddressDTO toAddressDto(Address address){
        this.setPublicPlace(address.getPublicPlace());
        this.setNumber(address.getNumber());
        this.setComplement(address.getComplement());
        this.setDistrict(address.getDistrict());
        this.setZipCode(address.getZipCode());
        this.setCity(address.getCity());
        this.setState(address.getState());

        return this;
    }

    public Address toAddress(Address address){
        address.setPublicPlace(this.getPublicPlace());
        address.setNumber(this.getNumber());
        address.setComplement(this.getComplement());
        address.setDistrict(this.getDistrict());
        address.setZipCode(this.getZipCode());
        address.setCity(this.getCity());
        address.setState(this.getState());

        return address;
    }
}
