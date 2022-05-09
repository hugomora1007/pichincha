package ec.pichincha.proyecto.to;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaTo {
	
	private Long cuentaId;
	
	private String numeroCuenta;
	
	private String tipoCuenta;
	
	private BigDecimal saldoInicial;
	
	private String estado;
	
	private String identificacionCliente;

}
