package com.trustify.darktransfertdata.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private String code;
    private String codeWithdrawal;
    private Instant dateDeposit;
    private Instant dateWithdrawal;
    private Instant dateModify;
    private boolean status=false;
    @Enumerated(EnumType.STRING)
    private com.trustify.darktransfertdata.Operation type;
    @ManyToOne
    private Agency agency;

}
