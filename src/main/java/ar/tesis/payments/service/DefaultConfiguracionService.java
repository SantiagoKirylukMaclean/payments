package ar.tesis.payments.service;

import ar.tesis.payments.model.Configuracion;
import ar.tesis.payments.repository.ConfiguracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DefaultConfiguracionService")
public class DefaultConfiguracionService implements ConfiguracionServiceInterface {

    @Autowired
    ConfiguracionRepository configuracionRepository;

    public Configuracion findConfiguracionById(int configuracionId){
        return configuracionRepository.findById(configuracionId);
    }
}
