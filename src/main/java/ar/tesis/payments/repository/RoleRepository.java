package ar.tesis.payments.repository;

import java.util.Optional;

import ar.tesis.payments.model.Role;
import ar.tesis.payments.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}