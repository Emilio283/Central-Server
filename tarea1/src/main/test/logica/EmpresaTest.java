package main.test.logica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.java.logica.clasesbasicas.Empresa;
import main.java.logica.datatypes.DataEmpresa;

class EmpresaTest {

  static final String nick = "mundo-mangueras";
  static final String nombre = "Carlos";
  static final String apellido = "Chinea";
  static final String email = "mundomangeras@fing.edu,uy";
  static final String descripcion = "Queres comprar mangueras? Es con nosotros";
  static final String sitioWeb = "https://www.mundomangueras.com";
  static final String password = "Me gustan las papas";
  static final String foto = "Fotilla";

  @Test
  void instanciar() {
    Empresa usuario = new Empresa(nick, nombre, apellido, email, descripcion, sitioWeb, password, foto);
    assertEquals(nick, usuario.getNick());
    assertEquals(nombre, usuario.getNombre());
    assertEquals(apellido, usuario.getApellido());
    assertEquals(email, usuario.getEmail());
    assertEquals(descripcion, usuario.getDescripcion());
    assertTrue(usuario.isPassword(password));
    assertFalse(usuario.isPassword(nick));
    assertEquals(sitioWeb, usuario.getSitioWeb());
  }

  @Test
  void instanciarConDatatype() {
    DataEmpresa de1 = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);

    Empresa empresa = new Empresa(de1, password);

    assertEquals(de1.getNickname(), empresa.getNick());
    assertEquals(de1.getNombre(), empresa.getNombre());
    assertEquals(de1.getApellido(), empresa.getApellido());
    assertEquals(de1.getEmail(), empresa.getEmail());
    assertEquals(de1.getDescripcion(), empresa.getDescripcion());
    assertEquals(de1.getSitioWeb(), empresa.getSitioWeb());
    assertTrue(empresa.isPassword(password));
    assertFalse(empresa.isPassword(nick));
    assertTrue(empresa.listarOfertasLaborales().isEmpty());
  }

  @Test
  void datatype() {
    Empresa empresa = new Empresa(nick, nombre, apellido, email, descripcion, sitioWeb, password, foto);

    DataEmpresa de1 = empresa.data();

    assertEquals(nick, de1.getNickname());
    assertEquals(nombre, de1.getNombre());
    assertEquals(apellido, de1.getApellido());
    assertEquals(email, de1.getEmail());
    assertEquals(descripcion, de1.getDescripcion());
    assertEquals(sitioWeb, de1.getSitioWeb());
  }

  @Test
  void udpateConIgualesMantiene() {
    Empresa empresa = new Empresa(nick, nombre, apellido, email, descripcion, sitioWeb, password, foto);

    DataEmpresa cambios = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);

    empresa.actualizar(cambios);

    assertEquals(cambios.getNickname(), empresa.getNick());
    assertEquals(cambios.getNombre(), empresa.getNombre());
    assertEquals(cambios.getApellido(), empresa.getApellido());
    assertEquals(cambios.getEmail(), empresa.getEmail());
    assertEquals(cambios.getDescripcion(), empresa.getDescripcion());
    assertEquals(cambios.getSitioWeb(), empresa.getSitioWeb());
  }

}
