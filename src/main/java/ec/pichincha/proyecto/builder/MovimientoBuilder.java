package ec.pichincha.proyecto.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import ec.pichincha.proyecto.entity.Cuenta;
import ec.pichincha.proyecto.entity.Movimiento;
import ec.pichincha.proyecto.exception.MovimientoException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoBuilder {

	private Long movimientoId;

	private LocalDate fechaMovimiento;

	private String tipoMovimiento;

	private BigDecimal valor;

	private BigDecimal saldo;

	private Cuenta cuenta;
	
	public MovimientoBuilder asignarMovimientoId(Long movimientoId) {
		this.movimientoId = movimientoId;
		return this;
	}
	
	public MovimientoBuilder asignarFechaMovimiento(LocalDate fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
		return this;
	}
	
	public MovimientoBuilder asignarTipoMovimiento() {
		this.tipoMovimiento = this.valor.compareTo(BigDecimal.ZERO) < 0 ? "Retiro" : "Deposito";
		return this;
	}
	
	public MovimientoBuilder asignarValor(BigDecimal valor) {
		this.valor = valor;
		return this;
	}
	
	public MovimientoBuilder asignarSaldo(BigDecimal saldo) throws MovimientoException {
		if (saldo.compareTo(BigDecimal.ZERO) < 0) {
			throw new MovimientoException("El valor a debitar es mayor que el saldo disponible");
		}
		this.saldo = saldo;
		return this;
	}
	
	public MovimientoBuilder asignarCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
		return this;
	}
	
	public Movimiento build() {
		return new Movimiento(this);
	}

}
