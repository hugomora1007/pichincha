package ec.pichincha.proyecto.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.pichincha.proyecto.builder.CuentaBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "HM_CUENTA")
@NoArgsConstructor
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "cuenta_id", nullable = false, unique = true)
	private Long cuentaId;
	
	@Column(name = "numero_cuenta")
	private String numeroCuenta;
	
	@Column(name = "tipo_cuenta")
	private String tipoCuenta;
	
	@Column(name = "saldo_inicial")
	private BigDecimal saldoInicial;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "saldo")
	private BigDecimal saldo;
	
	@JoinColumn(name = "id_cliente", nullable = false)
	@ManyToOne
	private Cliente cliente;

	public Cuenta(CuentaBuilder builder) {
		this.cuentaId = builder.getCuentaId();
		this.numeroCuenta = builder.getNumeroCuenta();
		this.tipoCuenta = builder.getTipoCuenta();
		this.saldoInicial = builder.getSaldoInicial();
		this.saldo = builder.getSaldo();
		this.estado = builder.getEstado();
		this.cliente = builder.getCliente();
	}
}
