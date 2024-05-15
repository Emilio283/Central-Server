package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class NoExisteTipoPublicacion extends Exception {
	public NoExisteTipoPublicacion(String msg) {
		super(msg);
	}
}
