package main.java.logica.datatypes;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


public class DataPaquete {
	private final String nombre;
	private final String descripcion;
	private final int periodo;
	private final float descuento;
	private final LocalDate fechaAlta;
	private final Set<DataTipoPublicacionPaquete> tipoPublicaciones;
	private String imagen;
	private float costo;
	
	public DataPaquete(String nombre,
			 String descripcion,
			 int periodo,
			 float descuento,
			 LocalDate fechaAlta,
			 Set<DataTipoPublicacionPaquete> tPublicaciones, String imagen, float cost) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodo = periodo;
		this.descuento = descuento;
		this.fechaAlta = fechaAlta;
		this.tipoPublicaciones = tPublicaciones;
		this.imagen = imagen;
		this.costo = cost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, descuento, fechaAlta, nombre, periodo, tipoPublicaciones);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataPaquete other = (DataPaquete) obj;
		return Objects.equals(descripcion, other.descripcion)
				&& Float.floatToIntBits(descuento) == Float.floatToIntBits(other.descuento)
				&& Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(nombre, other.nombre)
				&& periodo == other.periodo && Objects.equals(tipoPublicaciones, other.tipoPublicaciones);
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return getNombre();
	}

	public int getPeriodo() {
		return periodo;
	}

	public float getDescuento() {
		return descuento;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public String getImagen() {
    return imagen;
  }
	
	public Set<DataTipoPublicacionPaquete> getTipoPublicaciones(){
		return tipoPublicaciones;
	}
	
	public float getCosto() {
		return this.costo;
	}
}
