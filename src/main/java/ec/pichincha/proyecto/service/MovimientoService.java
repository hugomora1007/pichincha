package ec.pichincha.proyecto.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import ec.pichincha.proyecto.dto.EstadoCuentaDto;
import ec.pichincha.proyecto.entity.Cuenta;
import ec.pichincha.proyecto.entity.Movimiento;
import ec.pichincha.proyecto.exception.MovimientoException;

public interface MovimientoService {

	Movimiento getMovimientoById(Long idMovimiento);

	Movimiento guardarMovimiento(Movimiento movimiento) throws MovimientoException;

	void eliminarMovimiento(Movimiento movimiento);

	void eliminarMovimiento(Long movimientoId);

	BigDecimal getTotalRetiroDiario(String tipoMovimiento, Cuenta cuenta);

	List<EstadoCuentaDto> getEstadoCuentaByFechasAndCliente(LocalDate fechaDesde, LocalDate fechaHasta,
			String identificacion);

}
