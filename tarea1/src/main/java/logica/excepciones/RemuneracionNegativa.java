package main.java.logica.excepciones;

public class RemuneracionNegativa extends Exception {
	private static final long serialVersionUID = 1L;

	public RemuneracionNegativa(String msg) {
		super(msg);
	}
}
