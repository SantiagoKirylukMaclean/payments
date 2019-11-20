package ar.tesis.payments.service;

import ar.tesis.payments.model.Descuento;
import ar.tesis.payments.model.Market;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DescuentoServiceInterface {

    public List<Descuento> findDescuentoById(long descuentoId);

    public void saveDescuento(Descuento descuento);

}
