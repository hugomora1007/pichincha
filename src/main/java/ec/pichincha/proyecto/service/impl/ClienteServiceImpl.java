package ec.pichincha.proyecto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.pichincha.proyecto.entity.Cliente;
import ec.pichincha.proyecto.repository.ClienteRepository;
import ec.pichincha.proyecto.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente getClienteById(Long idCliente) {
		return this.clienteRepository.findByClienteId(idCliente).orElse(null);
	}

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	@Override
	public void eliminarCliente(Cliente cliente) {
		this.clienteRepository.delete(cliente);
	}

	@Override
	public void eliminarCliente(Long idCliente) {
		Cliente cliente = this.getClienteById(idCliente);
		if (cliente != null) {
			this.eliminarCliente(cliente);
		}
	}

	@Override
	public Cliente getClienteByIdentificacion(String identificacion) {
		return this.clienteRepository.findByIdentificacion(identificacion).orElse(null);
	}

}
