package ar.tesis.payments.service;

import ar.tesis.payments.model.Servicio;
import ar.tesis.payments.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DefaultServicioService")
public class DefaultServicioService implements ServicioServiceInterface{

    @Autowired
    ServicioRepository servicioRepository;

    public List<Servicio> getServices(){
        return servicioRepository.findAll();
    }

    public Servicio getServicioById(long servicioId){
        return servicioRepository.findById(servicioId);
    }


}
