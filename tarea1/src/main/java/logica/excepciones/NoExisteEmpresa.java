package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class NoExisteEmpresa extends Exception {
	public NoExisteEmpresa(String msg) {
		super(msg);
	}
}
