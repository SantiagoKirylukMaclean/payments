package ar.tesis.payments.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "configuracion")

public class Configuracion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "configuracion_id")
    private int id;

    @Column(name = "cantidadRecordarCompraAbandonada")
    @NotEmpty(message = "*Please provide your cantidadRecordarCompraAbandonada")
    private byte cantidadRecordarCompraAbandonada;

    @Column(name = "codigoTelefonoNoOk")
    @NotEmpty(message = "*Please provide your codigoTelefonoNoOk")
    private String codigoTelefonoNoOk;

    @Column(name = "codigoTelefonoOk")
    @NotEmpty(message = "*Please provide your codigoTelefonoOk")
    private String codigoTelefonoOk;

    @Column(name = "mensajeBienvenida")
    @NotEmpty(message = "*Please provide your mensajeBienvenida")
    private String mensajeBienvenida;

    @Column(name = "postConfirmacion")
    @NotEmpty(message = "*Please provide your postConfirmacion")
    private byte postConfirmacion;

    @Column(name = "preConfirmacion")
    @NotEmpty(message = "*Please provide your preConfirmacion")
    private byte preConfirmacion;

    @Column(name = "tipoProcesamiento")
    @NotEmpty(message = "*Please provide your tipoProcesamiento")
    private String tipoProcesamiento;

    @Column(name = "URLNoOk")
    @NotEmpty(message = "*Please provide your URLNoOk")
    private String URLNoOk;

    @Column(name = "URLOk")
    @NotEmpty(message = "*Please provide your URLOk")
    private String URLOk;
}
