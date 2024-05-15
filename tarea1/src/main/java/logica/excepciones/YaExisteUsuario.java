package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class YaExisteUsuario extends Exception {
	public YaExisteUsuario(String msg) {
		super(msg);
	}
}
