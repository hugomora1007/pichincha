package ec.pichincha.proyecto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.pichincha.proyecto.entity.Cuenta;
import ec.pichincha.proyecto.repository.CuentaRepository;
import ec.pichincha.proyecto.service.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public Cuenta getCuentaById(Long idCuenta) {
		return this.cuentaRepository.findByCuentaId(idCuenta).orElse(null);
	}

	@Override
	public Cuenta guardarCuenta(Cuenta cuenta) {
		return this.cuentaRepository.save(cuenta);
	}

	@Override
	public void eliminarCuenta(Cuenta cuenta) {
		this.cuentaRepository.delete(cuenta);
	}

	@Override
	public void eliminarCuenta(Long idCuenta) {
		Cuenta cuenta = this.getCuentaById(idCuenta);
		if (cuenta != null) {
			this.eliminarCuenta(cuenta);
		}
	}

	@Override
	public Cuenta getCuentaByNumeroCuenta(String numeroCuenta) {
		return this.cuentaRepository.findByNumeroCuenta(numeroCuenta).orElse(null);
	}

}
