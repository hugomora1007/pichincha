package ec.pichincha.proyecto.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.pichincha.proyecto.builder.MovimientoBuilder;
import ec.pichincha.proyecto.dto.EstadoCuentaDto;
import ec.pichincha.proyecto.entity.Cuenta;
import ec.pichincha.proyecto.entity.Movimiento;
import ec.pichincha.proyecto.exception.MovimientoException;
import ec.pichincha.proyecto.service.CuentaService;
import ec.pichincha.proyecto.service.MovimientoService;
import ec.pichincha.proyecto.to.MovimientoTo;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	private MovimientoService movimientoService;
	@Autowired
	private CuentaService cuentaService;

	@PostMapping("/save")
	public ResponseEntity<String> guardarMovimiento(@RequestBody MovimientoTo movimientoTo) {
		Cuenta cuenta = this.cuentaService.getCuentaByNumeroCuenta(movimientoTo.getNumeroCuenta());
		if (cuenta == null) {
			return new ResponseEntity<String>("No existe la cuenta numero: " + movimientoTo.getNumeroCuenta(),
					HttpStatus.OK);
		} else {
			Movimiento movimiento;
			try {
				movimiento = new MovimientoBuilder().asignarCuenta(cuenta).asignarFechaMovimiento(LocalDate.now())
						.asignarValor(movimientoTo.getValor())
						.asignarSaldo(cuenta.getSaldo().add(movimientoTo.getValor())).asignarTipoMovimiento().build();
			} catch (MovimientoException e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
			}

			try {
				Movimiento movimientoGuardado = this.movimientoService.guardarMovimiento(movimiento);
				cuenta.setSaldo(movimientoGuardado.getSaldo());
				this.cuentaService.guardarCuenta(cuenta);
				if (movimientoGuardado.getMovimientoId() == null) {
					return new ResponseEntity<String>("Registro no guardado", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Movimiento guardado correctamente. Codigo de movimiento: "
							+ movimientoGuardado.getMovimientoId(), HttpStatus.OK);
				}
			} catch (MovimientoException e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
			}

		}
	}

	@GetMapping("/reportes/{identificacion}/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Object>> findClienteById(@PathVariable String identificacion,
			@PathVariable String fechaDesde, @PathVariable String fechaHasta) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		List<EstadoCuentaDto> reporte = this.movimientoService.getEstadoCuentaByFechasAndCliente(
				LocalDate.parse(fechaDesde, formatter), LocalDate.parse(fechaHasta, formatter), identificacion);
		if (reporte != null) {
			return new ResponseEntity(reporte, HttpStatus.OK);
		}

		List<EstadoCuentaDto> resp = new ArrayList<>();
		return new ResponseEntity(resp, HttpStatus.OK);
	}

}
