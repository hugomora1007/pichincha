package ec.pichincha.proyecto.to;

import ec.pichincha.proyecto.entity.Cuenta;
import ec.pichincha.proyecto.enumeracion.EstadoConsultaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaRespuestaTo {
	
	private Cuenta cuenta;

	private EstadoConsultaEnum estadoConsultaEnum;

	private String mensaje;

}
