package ec.pichincha.proyecto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HM_CLIENTE")
public class Cliente extends Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id", nullable = false, unique = true)
	private Long clienteId;

	@Column
	private String contrasenia;

	@Column
	private String estado;

}
