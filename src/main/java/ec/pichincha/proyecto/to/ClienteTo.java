package ec.pichincha.proyecto.to;

import ec.pichincha.proyecto.entity.Cliente;
import ec.pichincha.proyecto.enumeracion.EstadoConsultaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteTo {
	
	private Cliente cliente;
	
	private EstadoConsultaEnum estadoConsultaEnum;
	
	private String mensaje;

	public ClienteTo(EstadoConsultaEnum estadoConsultaEnum, String mensaje) {
		super();
		this.estadoConsultaEnum = estadoConsultaEnum;
		this.mensaje = mensaje;
	}

}
