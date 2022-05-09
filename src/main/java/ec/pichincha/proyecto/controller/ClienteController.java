package ec.pichincha.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.pichincha.proyecto.entity.Cliente;
import ec.pichincha.proyecto.enumeracion.EstadoConsultaEnum;
import ec.pichincha.proyecto.service.ClienteService;
import ec.pichincha.proyecto.to.ClienteTo;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping("/save")
	public ResponseEntity<String> guardarCliente(@RequestBody Cliente cliente) {
		Cliente clienteGuardado = this.clienteService.guardarCliente(cliente);
		if (clienteGuardado.getClienteId() == null) {
			return new ResponseEntity<String>("Registro no guardado", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(
					"Cliente guardado correctamente. Codigo de cliente: " + clienteGuardado.getClienteId(),
					HttpStatus.OK);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<String> actualizarCliente(@RequestBody Cliente cliente) {
		Cliente clienteDato = this.clienteService.getClienteById(cliente.getClienteId());
		if (clienteDato == null) {
			return new ResponseEntity<String>("Cliente no encontrado con id: " + cliente.getClienteId(), HttpStatus.OK);
		} else {
			clienteDato.setContrasenia(
					cliente.getContrasenia() == null ? clienteDato.getContrasenia() : cliente.getContrasenia());
			clienteDato
					.setDireccion(cliente.getDireccion() == null ? clienteDato.getDireccion() : cliente.getDireccion());
			clienteDato.setEdad(cliente.getEdad() == null ? clienteDato.getEdad() : cliente.getEdad());
			clienteDato.setEstado(cliente.getEstado() == null ? clienteDato.getEstado() : cliente.getEstado());
			clienteDato.setGenero(cliente.getGenero() == null ? clienteDato.getGenero() : cliente.getGenero());
			clienteDato.setIdentificacion(cliente.getIdentificacion() == null ? clienteDato.getIdentificacion()
					: cliente.getIdentificacion());
			clienteDato.setNombre(cliente.getNombre() == null ? clienteDato.getNombre() : cliente.getNombre());
			clienteDato.setTelefono(cliente.getTelefono() == null ? clienteDato.getTelefono() : cliente.getTelefono());
			this.clienteService.guardarCliente(clienteDato);
			return new ResponseEntity<String>(
					"Cliente actualizado correctamente. Codigo de cliente: " + clienteDato.getClienteId(),
					HttpStatus.OK);
		}

	}

	@GetMapping("/find/{idCliente}")
	public ResponseEntity<Object> findClienteById(@PathVariable Long idCliente) {
		ClienteTo clienteTo = null;
		Cliente cliente = this.clienteService.getClienteById(idCliente);
		if (cliente == null) {
			clienteTo = new ClienteTo(cliente, EstadoConsultaEnum.FALLIDO,
					"No se pudo obtener el cliente con id: " + idCliente);
		} else {
			clienteTo = new ClienteTo(cliente, EstadoConsultaEnum.EXITOSO, null);
		}

		return new ResponseEntity<Object>(clienteTo, HttpStatus.OK);
	}

	@GetMapping("/find/identificacion/{identificacion}")
	public ResponseEntity<Object> findClienteByIdentificacion(@PathVariable String identificacion) {
		ClienteTo clienteTo = null;
		Cliente cliente = this.clienteService.getClienteByIdentificacion(identificacion);
		if (cliente == null) {
			clienteTo = new ClienteTo(cliente, EstadoConsultaEnum.FALLIDO,
					"No se pudo obtener el cliente con la identificacion: " + identificacion);
		} else {
			clienteTo = new ClienteTo(cliente, EstadoConsultaEnum.EXITOSO, null);
		}

		return new ResponseEntity<Object>(clienteTo, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{idCliente}")
	public ResponseEntity<String> deleteClienteById(@PathVariable Long idCliente) {
		this.clienteService.eliminarCliente(idCliente);
		return new ResponseEntity<String>("Registro eliminado correctamente", HttpStatus.OK);
	}

}
