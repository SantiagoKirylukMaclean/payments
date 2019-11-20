package ar.tesis.payments.repository;

import ar.tesis.payments.model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {

    List<Descuento> findById(long marketId);
}
