package com.trustify.darktransfertdata.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String identify;
    private String fullname;
    private String telephone;
    private String address;
    private String numberIdentify;
    private String mail;
    private String fullnameRecever;
    private String phoneRecever;
    private String addressRecever;
    private String mailRecever;

   @OneToOne(cascade = CascadeType.ALL)
   private Operation operation;
}
