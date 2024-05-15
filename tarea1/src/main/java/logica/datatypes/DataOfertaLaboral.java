package main.java.logica.datatypes;

import java.time.LocalDate;
import java.util.Set;

import main.java.logica.enums.StatusOfertaLaboral;

public class DataOfertaLaboral {
  @Override
  public String toString() {
    return nombre;
  }

  private String nombre;
  private String descOL;
  private float remu;
  private String horario;
  private String ciudad;
  private String departamento;
  private LocalDate fecAlta;
  private Set<String> keyw;
  private DataTipoPublicacion tipo;
  private StatusOfertaLaboral status;
  private String img;
  private DataEmpresa publicador;
  private String paquetePago;

  public DataOfertaLaboral(String nombre, String descOL, float remu, String horario, String ciudad,
      String departamento, LocalDate fecAlta, DataTipoPublicacion tipo, Set<String> keywords,
      StatusOfertaLaboral stat, String imagen, DataEmpresa publicador, String nomPaq) {
    this.nombre = nombre;
    this.descOL = descOL;
    this.remu = remu;
    this.horario = horario;
    this.ciudad = ciudad;
    this.departamento = departamento;
    this.fecAlta = fecAlta;
    this.tipo = tipo;
    this.keyw = keywords;
    this.status = stat;
    this.img = imagen;
    this.publicador = publicador;
    this.paquetePago = nomPaq;
  }

  public String getNombre() {
    return nombre;
  }
  
  public String getPaquetePago() {
	  return paquetePago;
  }

  public String getDescOL() {
    return descOL;
  }

  public float getRemu() {
    return remu;
  }

  public String getHorario() {
    return horario;
  }

  public String getCiudad() {
    return ciudad;
  }

  public String getDepartamento() {
    return departamento;
  }

  public LocalDate getFecAlta() {
    return fecAlta;
  }

  public Set<String> getKeyw() {
    return keyw;
  }

  public DataTipoPublicacion getTipo() {
    return tipo;
  }
  
  public DataEmpresa getPublicador() {
    return publicador;
  }

  public Set<String> getKeywods() {
    return keyw;
  }

  public StatusOfertaLaboral getStatus() {
    return status;
  }

  public String getImg() {
    return img;
  }

}
