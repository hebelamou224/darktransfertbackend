package com.trustify.darktransfertdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private LocalDateTime dateTransfert;
    private String devise;
    private boolean status;
    @ManyToOne
    private ModeTransfert mode;

    @Override
    public String toString() {
        return "Transfert{" +
                "id=" + id +
                ", amount=" + amount +
                ", dateTransfert=" + dateTransfert +
                ", devise='" + devise + '\'' +
                ", status=" + status +
                ", mode=" + mode +
                '}';
    }
}
