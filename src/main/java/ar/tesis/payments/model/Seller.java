package ar.tesis.payments.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seller_id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String nombre;

    @Column(name = "apellido")
    @NotEmpty(message = "*Please provide your surname")
    private String apellido;

    @Column(name = "razonSocial")
    @NotEmpty(message = "*Please provide your razonSocial")
    private String razonSocial;

    @Column(name = "cuit")
    @NotEmpty(message = "*Please provide your cuit")
    private String cuit;

    @Column(name = "direccion")
    @NotEmpty(message = "*Please provide your direccion")
    private String direccion;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "Contrasena")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your Contrasena")
    private String Contrasena;

    @Column(name = "active")
    private int active;

    private Market market;

    private Servicio servicio;

    private Configuracion configuracion;


    

}
