package com.trustify.darktransfertdata.model;

import com.trustify.darktransfertdata.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String fullname;
    private String address;
    private String telephone;
    private Instant dateRegister;
    private String role;
    private String identifyAgency;
    private String password;
    //@OneToOne(cascade = CascadeType.ALL)
    //private Agency agency;
}
