package com.trustify.darktransfertdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String identify;
    private String fullname;
    private String address;
    private String telephone;

   //@OneToMany(targetEntity = Operation.class, cascade = CascadeType.ALL)
    //@OneToOne(targetEntity = Operation.class, cascade = CascadeType.ALL)
    //@JoinColumn(name = "cp_fk", referencedColumnName = "id")
   @OneToOne(cascade = CascadeType.ALL)
   private Operation operation;
}
