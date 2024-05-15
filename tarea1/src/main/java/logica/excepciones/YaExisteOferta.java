package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class YaExisteOferta extends Exception{
	
	public YaExisteOferta(String msg) {
		super(msg);
	}
}
