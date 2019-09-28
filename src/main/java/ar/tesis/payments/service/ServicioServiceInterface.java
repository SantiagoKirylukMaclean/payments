package ar.tesis.payments.service;

import ar.tesis.payments.model.Servicio;

import java.util.List;

public interface ServicioServiceInterface {


    List<Servicio> getServices();

    Servicio getServicioById(long servicioId);
}
