package ec.pichincha.proyecto;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ec.pichincha.proyecto.entity.Cliente;
import ec.pichincha.proyecto.service.ClienteService;
import ec.pichincha.proyecto.util.JsonUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ClienteService clienteService;

	@Test
	public void crearCliente() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setContrasenia("123");
		cliente.setDireccion("Direccion");
		cliente.setEdad(25);
		cliente.setEstado("True");
		cliente.setGenero("Masculino");
		cliente.setIdentificacion("1717558904");
		cliente.setNombre("Hugo Mora");
		cliente.setTelefono("022587045");

		given(this.clienteService.guardarCliente(Mockito.any())).willReturn(cliente);

		this.mvc.perform(post("/clientes/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(cliente)))
				.andExpect(status().isOk());

		verify(this.clienteService, VerificationModeFactory.times(1)).guardarCliente(Mockito.any());
		reset(this.clienteService);
	}

	@Test
	public void buscarClientePorIdentificacion() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setContrasenia("567");
		cliente.setDireccion("Quito");
		cliente.setEdad(45);
		cliente.setEstado("True");
		cliente.setGenero("Femenino");
		cliente.setIdentificacion("1719584804");
		cliente.setNombre("Anita Aguirre");
		cliente.setTelefono("022585588");

		given(this.clienteService.getClienteByIdentificacion(Mockito.anyString())).willReturn(cliente);

		mvc.perform(get("/clientes/find/identificacion/1719584804").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		verify(this.clienteService, VerificationModeFactory.times(1)).getClienteByIdentificacion(Mockito.anyString());
		reset(this.clienteService);
	}

	@Test
	public void eliminarCliente() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setClienteId(1L);
		cliente.setNombre("Hugo Mora");

		mvc.perform(
				delete("/clientes/delete/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(cliente)))
				.andExpect(status().isOk());
		
		verify(this.clienteService, VerificationModeFactory.times(1)).eliminarCliente(Mockito.anyLong());
		reset(this.clienteService);
	}

}
