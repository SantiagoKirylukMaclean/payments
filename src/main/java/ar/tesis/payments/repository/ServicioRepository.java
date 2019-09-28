package ar.tesis.payments.repository;

import ar.tesis.payments.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio,Long> {

    Servicio findById(long servicioId);
}
