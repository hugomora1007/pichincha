package ec.pichincha.proyecto.service;

import ec.pichincha.proyecto.entity.Cliente;

public interface ClienteService {
	
	Cliente getClienteById(Long idCliente);
	
	Cliente guardarCliente(Cliente cliente);
	
	void eliminarCliente(Cliente cliente);
	
	void eliminarCliente(Long idCliente);
	
	Cliente getClienteByIdentificacion(String identificacion);

}
