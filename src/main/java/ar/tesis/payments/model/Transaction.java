package ar.tesis.payments.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(name = "mailComprador")
    @NotEmpty(message = "*Please provide your mail")
    private String mailComprador;

    @Column(name = "monto")
    @NotEmpty(message = "*Please provide your monto")
    private Double monto;

    @Column(name = "tarjeta_credito")
    private String tarjetaCredito;

    @Column(name = "cuotas")
    private String cuotas;

    @Column(name = "estado")
    private int estado;
}
