package ec.pichincha.proyecto.service;

import ec.pichincha.proyecto.entity.Cuenta;

public interface CuentaService {
	
	Cuenta getCuentaById(Long idCuenta);

	Cuenta guardarCuenta(Cuenta cuenta);

	void eliminarCuenta(Cuenta cuenta);
	
	void eliminarCuenta(Long idCuenta);
	
	Cuenta getCuentaByNumeroCuenta(String numeroCuenta);

}
