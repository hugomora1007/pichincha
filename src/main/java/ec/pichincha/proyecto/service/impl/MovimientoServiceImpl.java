package ec.pichincha.proyecto.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.pichincha.proyecto.dto.EstadoCuentaDto;
import ec.pichincha.proyecto.entity.Cliente;
import ec.pichincha.proyecto.entity.Cuenta;
import ec.pichincha.proyecto.entity.Movimiento;
import ec.pichincha.proyecto.exception.MovimientoException;
import ec.pichincha.proyecto.repository.MovimientoRepository;
import ec.pichincha.proyecto.service.ClienteService;
import ec.pichincha.proyecto.service.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	private static final BigDecimal RETIRO_MAXIMO_DIARIO = new BigDecimal(1000);

	@Autowired
	private MovimientoRepository movimientoRepository;
	@Autowired
	private ClienteService clienteService;

	@Override
	public Movimiento getMovimientoById(Long idMovimiento) {
		return this.movimientoRepository.findByMovimientoId(idMovimiento).orElse(null);
	}

	@Override
	public Movimiento guardarMovimiento(Movimiento movimiento) throws MovimientoException {
		if ("Retiro".equals(movimiento.getTipoMovimiento())) {
			BigDecimal retirosRealizados = this.getTotalRetiroDiario("Retiro", movimiento.getCuenta());
			BigDecimal retiroTotal = retirosRealizados.abs().add(movimiento.getValor().abs());
			if (retiroTotal.compareTo(RETIRO_MAXIMO_DIARIO) > 0) {
				throw new MovimientoException(
						"El valor de retiro diario maximo superó el permitido que es: " + RETIRO_MAXIMO_DIARIO);
			}
		}

		return this.movimientoRepository.save(movimiento);
	}

	@Override
	public void eliminarMovimiento(Movimiento movimiento) {
		this.movimientoRepository.delete(movimiento);
	}

	@Override
	public void eliminarMovimiento(Long movimientoId) {
		Movimiento movimiento = this.getMovimientoById(movimientoId);
		if (movimiento != null) {
			this.eliminarMovimiento(movimiento);
		}
	}

	@Override
	public BigDecimal getTotalRetiroDiario(String tipoMovimiento, Cuenta cuenta) {
		Movimiento movimiento = new Movimiento();
		movimiento.setValor(BigDecimal.ZERO);
		List<Movimiento> lista = new ArrayList<>();
		lista.add(movimiento);

		List<Movimiento> resultadoLista = this.movimientoRepository
				.getAllByFechaMovimientoAndTipoMovimientoAndCuenta(tipoMovimiento, cuenta).orElse(lista);

		return new BigDecimal(resultadoLista.stream().mapToInt(i -> i.getValor().intValue()).sum());
	}

	@Override
	public List<EstadoCuentaDto> getEstadoCuentaByFechasAndCliente(LocalDate fechaDesde, LocalDate fechaHasta,
			String identificacion) {
		Cliente cliente = this.clienteService.getClienteByIdentificacion(identificacion);
		return this.movimientoRepository
				.getEstadoCuentaByFechaAndCliente(fechaDesde, fechaHasta, cliente.getClienteId()).orElse(null);
	}

}
