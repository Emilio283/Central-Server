package main.java.logica.clasesbasicas;

import java.time.LocalDate;

import main.java.logica.datatypes.DataPostulacion;

public class Postulacion {
  private LocalDate fecPost;
  private String curriculum;
  private String motivacion;
  private Postulante postulante;
  private OfertaLaboral oferta;

  public Postulacion(LocalDate fecPost, String curriculum, String motivacion, Postulante postulante,
      OfertaLaboral oferta) {
    setFecPost(fecPost);
    setcV(curriculum);
    setMotivacion(motivacion);
    setP(postulante);
    setO(oferta);
  }

  public String getNickUsuario() {
    return postulante.getNickname();
  }

  public void setFecPost(LocalDate fecPost) {
    this.fecPost = fecPost;
  }

  public void setcV(String curriculum) {
    this.curriculum = curriculum;
  }

  public void setMotivacion(String motivacion) {
    this.motivacion = motivacion;
  }

  public void setP(Postulante postulante) {
    this.postulante = postulante;
  }

  public void setO(OfertaLaboral oferta) {
    this.oferta = oferta;
  }

  public DataPostulacion getDataPostulacion() {
    DataPostulacion datap = new DataPostulacion(fecPost, curriculum, motivacion, oferta.data(), postulante.data());
    return datap;
  }

  public OfertaLaboral getO() {
    return oferta;
  }
}
