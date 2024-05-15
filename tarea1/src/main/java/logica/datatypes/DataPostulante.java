package main.java.logica.datatypes;

import java.time.LocalDate;
import java.util.Objects;

public class DataPostulante extends DataUsuario {
  private LocalDate nacimiento;
  private String nacionalidad;

  public DataPostulante(String nick, String nombre, String apellido, String email,
      LocalDate nacimiento, String nacionalidad, String foto) {
    super(nick, nombre, apellido, email, foto);
    this.nacimiento = nacimiento;
    this.nacionalidad = nacionalidad;
  }

  public LocalDate getNacimiento() {
    return nacimiento;
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  @Override
  public int hashCode() {
    return Objects.hash(nacimiento, nacionalidad);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DataPostulante other = (DataPostulante) obj;
    return Objects.equals(nacimiento, other.nacimiento)
        && Objects.equals(nacionalidad, other.nacionalidad)
        && Objects.equals(getNickname(), other.getNickname())
        && Objects.equals(getNombre(), other.getNombre())
        && Objects.equals(getApellido(), other.getApellido());
  }
}
