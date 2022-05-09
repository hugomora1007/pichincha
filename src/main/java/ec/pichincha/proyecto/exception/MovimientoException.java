package ec.pichincha.proyecto.exception;

public class MovimientoException extends Exception {

	private static final long serialVersionUID = -5126242632622248719L;
	
	public MovimientoException() {
		
	}
	
	public MovimientoException(String mensaje) {
		super(mensaje);
	}
	
	public MovimientoException(Throwable throwable) {
		super(throwable);
	}
	
	public MovimientoException(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}

}
