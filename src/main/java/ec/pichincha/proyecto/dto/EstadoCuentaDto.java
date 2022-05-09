package ec.pichincha.proyecto.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstadoCuentaDto {

	private LocalDate fechaMovimiento;

	private String nombreCliente;

	private String numeroCuenta;

	private String tipo;

	private BigDecimal saldoInicial;

	private String estado;

	private BigDecimal movimiento;

	private BigDecimal saldo;

	public EstadoCuentaDto(LocalDate fechaMovimiento, String nombreCliente, String numeroCuenta, String tipo,
			BigDecimal saldoInicial, String estado, BigDecimal movimiento, BigDecimal saldo) {
		super();
		this.fechaMovimiento = fechaMovimiento;
		this.nombreCliente = nombreCliente;
		this.numeroCuenta = numeroCuenta;
		this.tipo = tipo;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.movimiento = movimiento;
		this.saldo = saldo;
	}

}
