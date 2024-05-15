package main.java.logica.excepciones;

public class NoExisteKeyword extends Exception {
	private static final long serialVersionUID = 1L;

	public NoExisteKeyword(String msg) {
		super(msg);
	}
}
