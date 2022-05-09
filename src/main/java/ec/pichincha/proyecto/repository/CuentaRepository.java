package ec.pichincha.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.pichincha.proyecto.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	
	Optional<Cuenta> findByCuentaId(Long cuentaId);
	
	Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

}
