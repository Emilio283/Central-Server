package main.java.logica.clasesbasicas;

import java.time.LocalDate;

import main.java.logica.datatypes.DataTipoPublicacion;

public class TipoPublicacion {
	
	private String nombre;
	private String descripcion;
	private int exposicion;
	private int duracion;
	private float costo;
	private LocalDate fecAlta;
	
	public TipoPublicacion(String nombre, String descripcion, int exposicion, int duracion, float costo, LocalDate fecAlta) {
		setNombre(nombre);
		setDescripcion(descripcion);
		setExposicion(exposicion);
		setDuracion(duracion);
		setCosto(costo);
		setFecAlta(fecAlta);
	}
	
	public TipoPublicacion(DataTipoPublicacion data) {
		setNombre(data.getNombre());
		setDescripcion(data.getDescripcion());
		setExposicion(data.getExposicion());
		setDuracion(data.getDuracion());
		setCosto(data.getCosto());
		setFecAlta(data.getFecAlta());
	}

	public String getNombreTP() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setExposicion(int exposicion) {
		this.exposicion = exposicion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public void setFecAlta(LocalDate fecAlta) {
		this.fecAlta = fecAlta;
	}
	
	public DataTipoPublicacion data() {
		return new DataTipoPublicacion(nombre, descripcion, exposicion, duracion, costo, fecAlta);
	}

	public int getDuracion() {
		return duracion;
	}
	
}
