package main.test.logica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import main.java.logica.datatypes.DataPostulante;


class DataPostulanteTest {
		static final String nick = "aarge";
		static final String nombre = "Argenlia";
		static final String apellido = "Antolep";
		static final String email = "aantolep@fing.edu,uy";
		static final LocalDate nacimiento = LocalDate.of(1989, 1, 24);
    static final String nacionalidad = "Oriental";
    static final String foto = "fototeta";

		@Test
		void instanciar() {
			DataPostulante usuario = new DataPostulante(nick, nombre, apellido, email, nacimiento, nacionalidad, foto);
			assertEquals(nick, usuario.getNickname());
			assertEquals(nombre, usuario.getNombre());
			assertEquals(apellido, usuario.getApellido());
			assertEquals(email, usuario.getEmail());
			assertEquals(nacimiento, usuario.getNacimiento());
			assertEquals(nacionalidad, usuario.getNacionalidad());
		}
		
		@Test
		void igualdadDeIguales() {
			DataPostulante dp1 = new DataPostulante(nick, nombre, apellido, email, nacimiento, nacionalidad, foto);
			DataPostulante dp2 = new DataPostulante(nick, nombre, apellido, email, nacimiento, nacionalidad, foto);

			assertEquals(dp1, dp2);
			assertEquals(dp2, dp1);
		}
		
		@Test
		void dpsigualdadDeDistintos() {
			DataPostulante dp1 = new DataPostulante("otroNick", nombre, apellido, email, nacimiento, nacionalidad, foto);
			DataPostulante dp2 = new DataPostulante(nick, nombre, apellido, email, nacimiento, nacionalidad, foto);

			assertNotEquals(dp1, dp2);
			assertNotEquals(dp2, dp1);
		}
		
		@Test
		void dpsigualdadConNull() {
			DataPostulante dp1 = new DataPostulante(nick, nombre, apellido, email, nacimiento, nacionalidad, foto);

			assertNotEquals(dp1, null);
			assertNotEquals(null, dp1);
		}
		
		@Test
		void toStringEsNick() {
			DataPostulante dp1 = new DataPostulante(nick, nombre, apellido, email, nacimiento, nacionalidad, foto);
			
			assertEquals(dp1.toString(), nick);
		}
	}
