package ec.pichincha.proyecto.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.pichincha.proyecto.dto.EstadoCuentaDto;
import ec.pichincha.proyecto.entity.Cuenta;
import ec.pichincha.proyecto.entity.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	Optional<Movimiento> findByMovimientoId(Long movimientoId);

	@Query("SELECT new ec.pichincha.proyecto.entity.Movimiento(m.valor) FROM Movimiento m "
			+ " WHERE m.fechaMovimiento = current_date AND m.tipoMovimiento = :tipoMovimiento"
			+ " AND m.cuenta = :cuenta ")
	Optional<List<Movimiento>> getAllByFechaMovimientoAndTipoMovimientoAndCuenta(
			@Param("tipoMovimiento") String tipoMovimiento, @Param("cuenta") Cuenta cuenta);

	@Query("SELECT new ec.pichincha.proyecto.dto.EstadoCuentaDto(movi.fechaMovimiento, movi.cuenta.cliente.nombre,"
			+ " movi.cuenta.numeroCuenta, movi.cuenta.tipoCuenta, movi.cuenta.saldoInicial,"
			+ " movi.cuenta.estado, movi.valor, movi.saldo) FROM Movimiento movi " 
			+ " WHERE movi.fechaMovimiento BETWEEN :fechaDesde AND :fechaHasta "
			+ " AND movi.cuenta.cliente.clienteId = :clienteId ")
	Optional<List<EstadoCuentaDto>> getEstadoCuentaByFechaAndCliente(@Param("fechaDesde") LocalDate fechaDesde,
			@Param("fechaHasta") LocalDate fechaHasta, @Param("clienteId") Long clienteId);

}
