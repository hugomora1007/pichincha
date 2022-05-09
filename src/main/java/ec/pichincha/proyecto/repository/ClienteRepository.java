package ec.pichincha.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.pichincha.proyecto.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Optional<Cliente> findByClienteId(Long clienteId);
	
	Optional<Cliente> findByIdentificacion(String identificacion);

}
