package main.java.logica.interfaces;

import main.java.logica.controladores.ControladorUsuario;
import main.java.logica.controladores.ControladorOferta;


public class Fabrica {
	private static Fabrica instancia;

    private Fabrica() {
    };

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }
    
    public IUsuario getIusuario() {
    	return new ControladorUsuario();
    }
    public IOferta getIOferta() {
    	return new ControladorOferta();
    }
}
