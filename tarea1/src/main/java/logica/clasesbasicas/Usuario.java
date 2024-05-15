package main.java.logica.clasesbasicas;

import main.java.logica.datatypes.DataUsuario;

abstract public class Usuario {
  private String nick;
  private String nombre;
  private String apellido;
  private String email;
  private String password;
  private String foto;

  Usuario(String nick, String nombre, String apellido, String email, String password, String foto) {
    this.nick = nick;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.password = password;
    this.foto = foto;
  }

  public Usuario(DataUsuario data, String password) {
    this.nick = data.getNickname();
    this.nombre = data.getNombre();
    this.apellido = data.getApellido();
    this.email = data.getEmail();
    this.foto = data.getFoto();
    this.password = password;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getNick() {
    return nick;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getEmail() {
    return email;
  }
  
  public String getFoto() {
    return foto;
  }
  
  public boolean isPassword(String password) {
    return password.equals(this.password);
  }

  public abstract DataUsuario data();
}
