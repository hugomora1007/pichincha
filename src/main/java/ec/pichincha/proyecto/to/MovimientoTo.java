package ec.pichincha.proyecto.to;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoTo {

	private Long movimientoId;

	private BigDecimal valor;

	private String numeroCuenta;

}
