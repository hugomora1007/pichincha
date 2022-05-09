package ec.pichincha.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.pichincha.proyecto.builder.CuentaBuilder;
import ec.pichincha.proyecto.entity.Cliente;
import ec.pichincha.proyecto.entity.Cuenta;
import ec.pichincha.proyecto.service.ClienteService;
import ec.pichincha.proyecto.service.CuentaService;
import ec.pichincha.proyecto.to.CuentaTo;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;
	@Autowired
	private ClienteService clienteService;

	@PostMapping("/save")
	public ResponseEntity<String> guardarCuenta(@RequestBody CuentaTo cuentaTo) {
		Cliente cliente = this.clienteService.getClienteByIdentificacion(cuentaTo.getIdentificacionCliente());
		if (cliente == null) {
			return new ResponseEntity<String>(
					"No existe el cliente con identificacion: " + cuentaTo.getIdentificacionCliente(), HttpStatus.OK);
		} else {
			Cuenta cuenta = new CuentaBuilder().asignarCliente(cliente).asignarEstado(cuentaTo.getEstado())
					.asignarNumeroCuenta(cuentaTo.getNumeroCuenta()).asignarSaldoInicial(cuentaTo.getSaldoInicial())
					.asignarTipoCuenta(cuentaTo.getTipoCuenta()).asignarSaldo(cuentaTo.getSaldoInicial()).build();

			Cuenta cuentaGuardada = this.cuentaService.guardarCuenta(cuenta);
			if (cuentaGuardada.getCuentaId() == null) {
				return new ResponseEntity<String>("Registro no guardado", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(
						"Cuenta guardada correctamente. Codigo de cuenta: " + cuentaGuardada.getCuentaId(),
						HttpStatus.OK);
			}
		}
	}

	@PostMapping("/update")
	public ResponseEntity<String> actualizarCuenta(@RequestBody CuentaTo cuentaTo) {
		Cuenta cuenta = this.cuentaService.getCuentaById(cuentaTo.getCuentaId());
		if (cuenta == null) {
			return new ResponseEntity<String>("Cuenta no encontrada con id: " + cuentaTo.getCuentaId(), HttpStatus.OK);
		} else {
			Cliente cliente = this.clienteService.getClienteByIdentificacion(cuentaTo.getIdentificacionCliente());
			Cuenta cuentaActualzar = new CuentaBuilder().asignarCliente(cliente)
					.asignarEstado(cuentaTo.getEstado() == null ? cuenta.getEstado() : cuentaTo.getEstado())
					.asignarNumeroCuenta(
							cuentaTo.getNumeroCuenta() == null ? cuenta.getNumeroCuenta() : cuentaTo.getNumeroCuenta())
					.asignarSaldoInicial(
							cuentaTo.getSaldoInicial() == null ? cuenta.getSaldoInicial() : cuentaTo.getSaldoInicial())
					.asignarTipoCuenta(
							cuentaTo.getTipoCuenta() == null ? cuenta.getTipoCuenta() : cuentaTo.getTipoCuenta())
					.asignarCuentaId(cuentaTo.getCuentaId()).build();
			this.cuentaService.guardarCuenta(cuentaActualzar);
			return new ResponseEntity<String>(
					"Cuenta actualizada correctamente. Codigo de cuenta: " + cuentaActualzar.getCuentaId(),
					HttpStatus.OK);
		}

	}

	@DeleteMapping("/delete/{idCuenta}")
	public ResponseEntity<String> deleteClienteById(@PathVariable Long idCuenta) {
		this.cuentaService.eliminarCuenta(idCuenta);
		return new ResponseEntity<String>("Registro eliminado correctamente", HttpStatus.OK);
	}

}
