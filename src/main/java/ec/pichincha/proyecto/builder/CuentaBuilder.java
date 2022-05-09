package ec.pichincha.proyecto.builder;

import java.math.BigDecimal;

import ec.pichincha.proyecto.entity.Cliente;
import ec.pichincha.proyecto.entity.Cuenta;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CuentaBuilder {
	
	private Long cuentaId;

	private String numeroCuenta;

	private String tipoCuenta;

	private BigDecimal saldoInicial;
	
	private BigDecimal saldo;

	private String estado;

	private Cliente cliente;
	
	public CuentaBuilder asignarCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
		return this;
	}
	 
	public CuentaBuilder asignarNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
		return this;
	}
	
	public CuentaBuilder asignarTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
		return this;
	}
	
	public CuentaBuilder asignarSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
		return this;
	}
	
	public CuentaBuilder asignarSaldo(BigDecimal saldo) {
		this.saldo = saldo;
		return this;
	}
	
	public CuentaBuilder asignarEstado(String estado) {
		this.estado = estado;
		return this;
	}
	
	public CuentaBuilder asignarCliente(Cliente cliente) {
		this.cliente = cliente;
		return this;
	}
	
	public Cuenta build() {
		return new Cuenta(this);
	}

}
