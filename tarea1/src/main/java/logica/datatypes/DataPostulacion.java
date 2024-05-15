package main.java.logica.datatypes;

import java.time.LocalDate;

public class DataPostulacion {

  public DataPostulacion(LocalDate fecPost, String cV2, String motivacion,
      DataOfertaLaboral dataof, DataPostulante dataPostulante) {
    this.fecPost = fecPost;
    this.curriculum = cV2;
    this.motivacion = motivacion;
    this.dof = dataof;
    this.dataPostulante = dataPostulante;
  }

  private final LocalDate fecPost;
  private final String curriculum;
  private final String motivacion;
  private final DataOfertaLaboral dof;
  private final DataPostulante dataPostulante;

  public LocalDate getFecPost() {
    return fecPost;
  }

  public String getcV() {
    return curriculum;
  }

  public String getMotivacion() {
    return motivacion;
  }

  public DataOfertaLaboral getDataOfertaLaboral() {
    return dof;
  }
  
  public DataPostulante getDataPostulante() {
    return dataPostulante;
  }
}
