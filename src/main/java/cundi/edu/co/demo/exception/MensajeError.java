package cundi.edu.co.demo.exception;

public class MensajeError {
	private String mensaje;
	private String tipo;
	
	public MensajeError(String mensaje, String tipo) {
		super();
		this.mensaje = mensaje;
		this.tipo = tipo;
	}

	public MensajeError() {
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
