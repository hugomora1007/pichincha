package ec.pichincha.proyecto.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ec.pichincha.proyecto.builder.MovimientoBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "HM_MOVIMIENTO")
@NoArgsConstructor
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "MOVIMIENTO_ID", nullable = false, unique = true)
	private Long movimientoId;
	
	@Column(name = "FECHA_MOVIMIENTO")
	private LocalDate fechaMovimiento;
	
	@Column(name = "TIPO_MOVIMIENTO")
	private String tipoMovimiento;
	
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@Column(name = "SALDO")
	private BigDecimal saldo;
	
	@JoinColumn(name = "cuenta_id", nullable = false)
	@ManyToOne
	private Cuenta cuenta;
	
	public Movimiento(MovimientoBuilder builder) {
		this.movimientoId = builder.getMovimientoId();
		this.fechaMovimiento = builder.getFechaMovimiento();
		this.tipoMovimiento = builder.getTipoMovimiento();
		this.valor = builder.getValor();
		this.saldo = builder.getSaldo();
		this.cuenta = builder.getCuenta();
	}

	public Movimiento(BigDecimal valor) {
		super();
		this.valor = valor;
	}

}
