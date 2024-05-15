package main.java.logica.datatypes;

import java.util.Objects;

//import logica.DataTypes.DataUsuario;
/**
 * 
 */

public class DataEmpresa extends DataUsuario {
  private String descripcion;
  private String sitioWeb;

  public DataEmpresa(String nick, String nombre, String apellido, String email, String descripcion,
      String sitioWeb, String foto) {
    super(nick, nombre, apellido, email, foto);
    this.descripcion = descripcion;
    this.sitioWeb = sitioWeb;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getSitioWeb() {
    return sitioWeb;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNickname(), getNombre(), getApellido(), getEmail(), descripcion,
        sitioWeb);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DataEmpresa other = (DataEmpresa) obj;
    return Objects.equals(descripcion, other.descripcion)
        && Objects.equals(sitioWeb, other.sitioWeb)
        && Objects.equals(getNickname(), other.getNickname())
        && Objects.equals(getNombre(), other.getNombre())
        && Objects.equals(getApellido(), other.getApellido())
        && Objects.equals(getEmail(), other.getEmail());
  }
}
