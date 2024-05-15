package main.java.logica.datatypes;

import java.time.LocalDate;
import java.util.Objects;

public class DataTipoPublicacion {

	@Override
	public String toString() {
		return getNombre();
	}

	private String nombre;
	private String descripcion;
	private int exposicion;
	private int duracion;
	private float costo;
	private LocalDate fecAlta;
	
	public DataTipoPublicacion(
			String nombre,
			String descripcion,
			int exposicion,
			int duracion,
			float costo,
			LocalDate fecAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.exposicion = exposicion;
		this.duracion = duracion;
		this.costo = costo;
		this.fecAlta = fecAlta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		// TODO Auto-generated method stub
		return descripcion;
	}

	public int getExposicion() {
		// TODO Auto-generated method stub
		return exposicion;
	}

	public int getDuracion() {
		// TODO Auto-generated method stub
		return duracion;
	}

	public float getCosto() {
		// TODO Auto-generated method stub
		return costo;
	}

	public LocalDate getFecAlta() {
		// TODO Auto-generated method stub
		return fecAlta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, descripcion, duracion, exposicion, fecAlta, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataTipoPublicacion other = (DataTipoPublicacion) obj;
		return Float.floatToIntBits(costo) == Float.floatToIntBits(other.costo)
				&& Objects.equals(descripcion, other.descripcion) && duracion == other.duracion
				&& exposicion == other.exposicion && Objects.equals(fecAlta, other.fecAlta)
				&& Objects.equals(nombre, other.nombre);
	}

}
