package ar.tesis.payments.repository;

import ar.tesis.payments.model.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracionRepository extends JpaRepository<Configuracion, Long> {
    Configuracion findById(int configuracionId);
}
