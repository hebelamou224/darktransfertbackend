package com.trustify.darktransfertdata.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String fullname;
    private String address;
    private String telephone;
    private Instant dateRegister;

    @OneToMany(targetEntity = Agency.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_agency_fk", referencedColumnName = "username")
    private List<Agency> agencies;

}
