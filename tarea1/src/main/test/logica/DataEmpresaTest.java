package main.test.logica;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import main.java.logica.datatypes.DataEmpresa;

class DataEmpresaTest {

	static final String nick = "mundo-mangueras";
	static final String nombre = "Carlos";
	static final String apellido = "Chinea";
	static final String email = "mundomangeras@fing.edu,uy";
	static final String descripcion = "Queres comprar mangueras? Es con nosotros";
  static final String sitioWeb = "https://www.mundomangueras.com";
  static final String foto = "foteto";

	@Test
	void instanciar() {
		DataEmpresa usuario = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);
		assertEquals(nick, usuario.getNickname());
		assertEquals(nombre, usuario.getNombre());
		assertEquals(apellido, usuario.getApellido());
		assertEquals(email, usuario.getEmail());
		assertEquals(descripcion, usuario.getDescripcion());
		assertEquals(sitioWeb, usuario.getSitioWeb());
	}
	
	@Test
	void reflexiva() {
		DataEmpresa de1 = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);

		assertEquals(de1, de1);
	}
	
	@Test
	void simetrica() {
		DataEmpresa de1 = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);
		DataEmpresa de2 = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);

		assertEquals(de1, de2);
		assertEquals(de2, de1);
	}
	
	@Test
	void desigualdadDeDistintos() {
		DataEmpresa de1 = new DataEmpresa("otroNick", nombre, apellido, email, descripcion, sitioWeb, foto);
		DataEmpresa de2 = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);

		assertNotEquals(de1, de2);
		assertNotEquals(de2, de1);
	}
	
	@Test
	void desigualdadConNull() {
		DataEmpresa de1 = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);

		assertNotEquals(de1, null);
		assertNotEquals(null, de1);
	}
	
	@Test
	void desigualdadConString() {
		DataEmpresa de1 = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);

		assertNotEquals(de1, "Holis");
		assertNotEquals("De nuevo c:", de1);
	}
	
	@Test
	void toStringEsNick() {
		DataEmpresa de1 = new DataEmpresa(nick, nombre, apellido, email, descripcion, sitioWeb, foto);
		
		assertEquals(de1.toString(), nick);
	}
}
