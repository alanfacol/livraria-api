package br.com.facol.livrariaback.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "document", length = 16, nullable = false)
    private String document;

    @Column(name = "is_pj", nullable = false)
    private boolean isPj;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Login login;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private List<Address> addressList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private List<Sale> saleList;
}