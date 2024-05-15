package main.java.logica.datatypes;

public class DataUsuario {

	@Override
	public String toString() {
		return nick;
	}
	
	private String nick;
	private String nombre;
	private String apellido;
	private String email;
	private String foto;
	
	DataUsuario(String nick, String nombre, String apellido, String email, String foto) {
		this.nick = nick;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.foto = foto;
	}

	public String getNickname() {
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
}
