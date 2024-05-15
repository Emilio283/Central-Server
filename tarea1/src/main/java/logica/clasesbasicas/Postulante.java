package main.java.logica.clasesbasicas;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import main.java.logica.datatypes.DataPostulante;

public class Postulante extends Usuario {

  private LocalDate nacimiento;
  private String nacionalidad;
  private Set<Postulacion> postulaciones = new HashSet<Postulacion>();

  public Postulante(String nick, String nombre, String apellido, String email, LocalDate nacimiento,
      String nacionalidad, String password, String foto) {
    super(nick, nombre, apellido, email, password, foto);
    this.nacimiento = nacimiento;
    this.nacionalidad = nacionalidad;
  }

  public Postulante(DataPostulante data, String password) {
    super(data, password);
    this.nacimiento = data.getNacimiento();
    this.nacionalidad = data.getNacionalidad();
    postulaciones = new HashSet<Postulacion>();
  }

  public String getNickname() {
    return getNick();
  }

  public LocalDate getNacimiento() {
    return nacimiento;
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  public Set<Postulacion> getPostulaciones() {
    return postulaciones;
  }

  public Set<String> getPostulacionesN() {
    Set<String> resultado = new HashSet<String>();
    Iterator<Postulacion> iter = postulaciones.iterator();

    while (iter.hasNext()) {
      resultado.add((iter.next()).getNickUsuario());
    }
    return resultado;
  }

  @Override
  public DataPostulante data() {
    return new DataPostulante(getNickname(), getNombre(), getApellido(), getEmail(), nacimiento,
        nacionalidad, getFoto());
  }

  public void actualizar(DataPostulante dtp) {
    setNombre(dtp.getNombre());
    setApellido(dtp.getApellido());
    setNacimiento(dtp.getNacimiento());
    setNacionalidad(dtp.getNacionalidad());
  }

  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }

  public void setNacimiento(LocalDate nacimiento) {
    this.nacimiento = nacimiento;
  }

  public void agregarPostulacionP(Postulacion postulacion) {
    postulaciones.add(postulacion);
  }
}
