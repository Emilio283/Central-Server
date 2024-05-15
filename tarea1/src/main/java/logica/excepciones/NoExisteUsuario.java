package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class NoExisteUsuario extends Exception {
	public NoExisteUsuario(String msg) {
		super(msg);
	}
}
