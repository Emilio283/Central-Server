package main.test.logica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import main.java.logica.clasesbasicas.Postulante;
import main.java.logica.datatypes.DataPostulante;

class PostulanteTest {

  static final String nick = "jorgepegnarol2";
  static final String nombre = "Jorge";
  static final String apellido = "Jimenez";
  static final String email = "jjimenez@fing.edu,uy";
  static final LocalDate nacimiento = LocalDate.of(2002, 2, 28);
  static final String nacionalidad = "Oriental";
  static final String password = "MeGustanLasPapas%100";
  static final String foto = "foto%100";

  @Test
  void instanciar() {
    Postulante usuario = new Postulante(nick, nombre, apellido, email, nacimiento, nacionalidad, password, foto);
    assertEquals(nick, usuario.getNick());
    assertEquals(nombre, usuario.getNombre());
    assertEquals(apellido, usuario.getApellido());
    assertEquals(email, usuario.getEmail());
    assertEquals(nacimiento, usuario.getNacimiento());
    assertEquals(nacionalidad, usuario.getNacionalidad());
    assertTrue(usuario.isPassword(password));
    assertFalse(usuario.isPassword(nick));
    assertTrue(usuario.getPostulacionesN().isEmpty());
  }

  @Test
  void instanciarConDatatype() {
    DataPostulante dataPostulante =
        new DataPostulante(nick, nombre, apellido, email, nacimiento, nacionalidad, foto);

    Postulante postulante = new Postulante(dataPostulante, password);

    assertEquals(dataPostulante.getNickname(), postulante.getNick());
    assertEquals(dataPostulante.getNombre(), postulante.getNombre());
    assertEquals(dataPostulante.getApellido(), postulante.getApellido());
    assertEquals(dataPostulante.getEmail(), postulante.getEmail());
    assertEquals(dataPostulante.getNacimiento(), postulante.getNacimiento());
    assertEquals(dataPostulante.getNacionalidad(), postulante.getNacionalidad());
    assertTrue(postulante.isPassword(password));
    assertFalse(postulante.isPassword(nick));
    assertTrue(postulante.getPostulacionesN().isEmpty());
  }

  @Test
  void datatype() {
    Postulante postulante = new Postulante(nick, nombre, apellido, email, nacimiento, nacionalidad, password, foto);

    DataPostulante dataPostulante = postulante.data();

    assertEquals(nick, dataPostulante.getNickname());
    assertEquals(nombre, dataPostulante.getNombre());
    assertEquals(apellido, dataPostulante.getApellido());
    assertEquals(email, dataPostulante.getEmail());
    assertEquals(nacimiento, dataPostulante.getNacimiento());
    assertEquals(nacionalidad, dataPostulante.getNacionalidad());
  }

  @Test
  void udpateConIgualesMantiene() {
    Postulante postulante = new Postulante(nick, nombre, apellido, email, nacimiento, nacionalidad, password, foto);

    DataPostulante cambios =
        new DataPostulante(nick, nombre, apellido, email, nacimiento, nacionalidad, foto);

    postulante.actualizar(cambios);

    assertEquals(cambios.getNombre(), postulante.getNombre());
    assertEquals(cambios.getApellido(), postulante.getApellido());
    assertEquals(cambios.getNacimiento(), postulante.getNacimiento());
    assertEquals(cambios.getNacionalidad(), postulante.getNacionalidad());
  }
}
