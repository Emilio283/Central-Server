package main.java.logica.clasesbasicas;

import java.util.HashSet;
import java.util.Set;

public class Keyword {
	private String nombre;
	private Set<OfertaLaboral> ofertasConKeyword;
	
	public Keyword(String nombre) {
		setNombre(nombre);
		ofertasConKeyword = new HashSet<OfertaLaboral>();
	}
	
	public String getNombreKeyword() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<OfertaLaboral> ofertasConKeyword() {
	  return ofertasConKeyword;
	}
}
