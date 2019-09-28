package ar.tesis.payments.service;

import ar.tesis.payments.model.Configuracion;
import org.springframework.stereotype.Service;

@Service
public interface ConfiguracionServiceInterface {

    Configuracion findConfiguracionById(int configuracionId);
}
