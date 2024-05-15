package main.java.logica.clasesbasicas;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import main.java.logica.datatypes.DataPaquete;
import main.java.logica.datatypes.DataTipoPublicacionPaquete;

public class Paquete {

  private String nombre;
  private String descripcion;
  private int duracion;
  private float descuento;
  private LocalDate fecAlta;
  private Map<String, TipoPublicacionPaquete> tipoPaqueteYCant;
  private String imagen;
  private boolean comprado;
  private float costo;


  public Paquete(String nombre, String descripcion, int duracion, float descuento,
      LocalDate fecAlta, String imagen) {
    setNombre(nombre);
    setDescripcion(descripcion);
    setDuracion(duracion);
    setDescuento(descuento);
    tipoPaqueteYCant = new HashMap<String, TipoPublicacionPaquete>();
    setFecAlta(fecAlta);
    this.imagen = imagen;
  }
  
  public void actualizarCosto() {
	  float total = 0;
	  Iterator<Map.Entry<String, TipoPublicacionPaquete>> iter = tipoPaqueteYCant.entrySet().iterator();
      while (iter.hasNext()) {
    	  Map.Entry<String, TipoPublicacionPaquete> entry = iter.next();
    	  total = total + (((entry.getValue()).getDataTipoPublicacionPaquete()).getDtp().getCosto() * (entry.getValue()).getDataTipoPublicacionPaquete().getCant());
      }
      total=total*((100- this.descuento)/100);
      
      this.costo=total;
  }
  
  public float getCosto() {
	  return this.costo;
  }

  public void setFecAlta(LocalDate fecAlta) {
    this.fecAlta = fecAlta;
  }

  public int getDuracion() {
    return duracion;
  }
  
  public boolean getComprado() {
	    return comprado;
  }
  
  public void setComprado(boolean estado) {
	    comprado = estado;
  }

  public void modificarDatos(String nombre, String desc, int duracion, float descuento,
      LocalDate fecAlta) {
    setNombre(nombre);
    setDescripcion(desc);
    setDuracion(duracion);
    setDescuento(descuento);
    setFecAlta(fecAlta);
  }

  public String getNombre() {
    return nombre;
  }

  public void agregarTipo(String nombre, TipoPublicacionPaquete tpp) {
    tipoPaqueteYCant.put(nombre, tpp);
    actualizarCosto();
  }

  public boolean existeTP(String nombre) {
    return tipoPaqueteYCant.containsKey(nombre);
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDescripcion(String desc) {
    descripcion = desc;
  }

  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }

  public void setDescuento(float descuento) {
    this.descuento = descuento;
  }

  public Set<DataTipoPublicacionPaquete> getTiposPublicacionPaquete() {
    Set<DataTipoPublicacionPaquete> resu = new HashSet<>();
    if (!tipoPaqueteYCant.isEmpty()) {
      Iterator<Map.Entry<String, TipoPublicacionPaquete>> iter =
          tipoPaqueteYCant.entrySet().iterator();
      while (iter.hasNext()) {
        Map.Entry<String, TipoPublicacionPaquete> entry = iter.next();
        resu.add((entry.getValue()).getDataTipoPublicacionPaquete());

      }
    }
    return resu;
  }
  
  
  public DataTipoPublicacionPaquete getTipoPubliPaquete(String nomTipoPubli) {
	  DataTipoPublicacionPaquete dtpp = null;
	  if (!tipoPaqueteYCant.isEmpty()) {
	      Iterator<Map.Entry<String, TipoPublicacionPaquete>> iter = tipoPaqueteYCant.entrySet().iterator();
	      while (iter.hasNext()) {
	    	  Map.Entry<String, TipoPublicacionPaquete> entry = iter.next();
	    	  if (entry.getKey().equals(nomTipoPubli)) {
	    		  dtpp = (entry.getValue()).getDataTipoPublicacionPaquete();
	    	  }
	      }
	  }
	  return dtpp;
  }

  public DataPaquete getDataPaquete() {
    return new DataPaquete(nombre, descripcion, duracion, descuento, fecAlta,
        getTiposPublicacionPaquete(), imagen, costo);
  }
}
