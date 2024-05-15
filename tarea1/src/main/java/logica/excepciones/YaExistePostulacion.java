package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class YaExistePostulacion extends Exception {
	public YaExistePostulacion(String msg) {
		super(msg);
	}
}
