package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class OfertaLaboralNoVigente extends Exception {
	public OfertaLaboralNoVigente(String msg) {
		super(msg);
	}

}
