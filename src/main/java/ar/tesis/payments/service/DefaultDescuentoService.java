package ar.tesis.payments.service;


import ar.tesis.payments.model.Descuento;
import ar.tesis.payments.model.Market;
import ar.tesis.payments.repository.DescuentoRepository;
import ar.tesis.payments.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DefaultDescuentoService")
public class DefaultDescuentoService implements DescuentoServiceInterface{

    @Autowired
    private DescuentoRepository descuentoRepository;

    public List<Descuento> findDescuentoById(long descuentoId) {
        return descuentoRepository.findById(descuentoId);
    }

    public void saveDescuento(Descuento descuento){
        descuentoRepository.save(descuento);
    }

}
