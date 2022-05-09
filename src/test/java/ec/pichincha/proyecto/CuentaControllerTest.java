package ec.pichincha.proyecto;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;

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
import ec.pichincha.proyecto.entity.Cuenta;
import ec.pichincha.proyecto.service.ClienteService;
import ec.pichincha.proyecto.service.CuentaService;
import ec.pichincha.proyecto.to.CuentaTo;
import ec.pichincha.proyecto.util.JsonUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class CuentaControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CuentaService cuentaService;

	@MockBean
	private ClienteService clienteService;

	@Test
	public void guardarCuentaTest() throws IOException, Exception {
		Cliente cliente = new Cliente();
		cliente.setClienteId(1L);
		cliente.setContrasenia("123");
		cliente.setDireccion("Direccion");
		cliente.setEdad(25);
		cliente.setEstado("True");
		cliente.setGenero("Masculino");
		cliente.setIdentificacion("1717558904");
		cliente.setNombre("Hugo Mora");
		cliente.setTelefono("022587045");

		given(this.clienteService.getClienteByIdentificacion(Mockito.anyString())).willReturn(cliente);

		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(cliente);
		cuenta.setEstado("True");
		cuenta.setNumeroCuenta("12345");
		cuenta.setSaldo(new BigDecimal(1000));
		cuenta.setSaldoInicial(new BigDecimal(1000));
		cuenta.setTipoCuenta("Corriente");

		given(this.cuentaService.guardarCuenta(Mockito.any())).willReturn(cuenta);

		CuentaTo cuentaTo = new CuentaTo();
		cuentaTo.setEstado("True");
		cuentaTo.setIdentificacionCliente("1717558904");
		cuentaTo.setNumeroCuenta("12345");
		cuentaTo.setSaldoInicial(new BigDecimal(1000));
		cuentaTo.setTipoCuenta("Ahorro");

		this.mvc.perform(
				post("/cuentas/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(cuentaTo)))
				.andExpect(status().isOk());

		verify(this.cuentaService, VerificationModeFactory.times(1)).guardarCuenta(Mockito.any());
		reset(this.cuentaService);
	}

	@Test
	public void eliminarCuenta() throws Exception {
		Cuenta cuenta = new Cuenta();
		cuenta.setNumeroCuenta("12345");
		cuenta.setTipoCuenta("Ahorro");

		mvc.perform(
				delete("/cuentas/delete/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(cuenta)))
				.andExpect(status().isOk());

		verify(this.cuentaService, VerificationModeFactory.times(1)).eliminarCuenta(Mockito.anyLong());
		reset(this.cuentaService);
	}

	@Test
	public void actualizarCuenta() throws Exception {
		Cuenta cuenta = new Cuenta();

		Cliente cliente = new Cliente();
		cliente.setClienteId(1L);
		cliente.setContrasenia("123");
		cliente.setDireccion("Direccion");
		cliente.setEdad(25);
		cliente.setEstado("True");
		cliente.setGenero("Masculino");
		cliente.setIdentificacion("1717558904");
		cliente.setNombre("Hugo Mora");
		cliente.setTelefono("022587045");

		cuenta.setTipoCuenta("Corriente");
		cuenta.setCliente(cliente);
		cuenta.setCuentaId(1L);
		cuenta.setEstado("True");
		cuenta.setNumeroCuenta("12345");
		cuenta.setSaldo(new BigDecimal(100));
		cuenta.setSaldoInicial(new BigDecimal(100));

		given(this.cuentaService.getCuentaById(Mockito.anyLong())).willReturn(cuenta);

		given(this.clienteService.getClienteByIdentificacion(Mockito.anyString())).willReturn(cliente);

		CuentaTo cuentaTo = new CuentaTo();
		cuentaTo.setTipoCuenta("Ahorro");

		this.mvc.perform(
				delete("/cuentas/delete/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(cuentaTo)))
				.andExpect(status().isOk());

		verify(this.cuentaService, VerificationModeFactory.times(1)).eliminarCuenta(Mockito.anyLong());
		reset(this.cuentaService);
	}

}
