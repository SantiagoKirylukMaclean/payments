package ar.tesis.payments.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "servicio_id")
    private int id;

    @Column(name = "descripcion")
    @NotEmpty(message = "*Please provide your descripcion")
    private String descripcion;

    @Column(name = "costo")
    @NotEmpty(message = "*Please provide your costo")
    private Double costo;

}
