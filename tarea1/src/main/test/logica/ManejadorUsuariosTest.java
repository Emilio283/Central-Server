package main.test.logica;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import main.java.logica.clasesbasicas.Empresa;
import main.java.logica.clasesbasicas.Postulante;
import main.java.logica.clasesbasicas.Usuario;
import main.java.logica.excepciones.NoExistePostulante;
import main.java.logica.excepciones.YaExisteUsuario;
import main.java.logica.manejadores.ManejadorUsuarios;

class ManejadorUsuariosTest {

  final private Postulante postulante = new Postulante("u1", "Alicia", "Artez", "aartez@email.net",
      LocalDate.of(1999, 4, 22), "Oriental", "ukayaka", "Fotito");

  final private Empresa empresa = new Empresa("u2", "Braulio", "Baloong", "bbaloong@email.net",
      "Soy un fontanero", "https://www.fontaneria.com.uy", "LaContraseaniasdf", "fotota");

  @AfterEach
  void tearDownAfterEach() {
    ManejadorUsuarios.getInstancia().clear();
  }

  @Test
  void esSingleton() {
    ManejadorUsuarios manejador1 = ManejadorUsuarios.getInstancia();
    ManejadorUsuarios manejador2 = ManejadorUsuarios.getInstancia();

    assertEquals(manejador1, manejador2);
  }

  @Test
  void agregaUsuario() {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    assertDoesNotThrow(() -> manejador.add(postulante));
  }
//	
//	@Test
//	void rechazaUsuarioPorNick() {
//		ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();
//		
//		assertTrue(manejador.add(postulante));
//		assertFalse(manejador.add(postulanteRepiteNick));
//	}

//	@Test
//	void rechazaUsuarioPorEmail() {
//		ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();
//		
//		assertTrue(manejador.add(postulante));
//		assertFalse(manejador.add(postulanteRepiteEmail));
//	}
//	
//	@Test
//	void rechazaUsuarioPorNickYEmail() {
//		ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();
//		
//		assertTrue(manejador.add(postulante));
//		assertFalse(manejador.add(postulanteRepiteNickYEmail));
//	}

//	
//	@Test
//	void encuentraPorEmail() {		
//		ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();
//		
//		m.add(postulante);
//		
//		assertEquals(postulante, manejador.encontrarPorEmail(new String(postulante.email)));
//	}
//	
//	@Test
//	void noEncuentraPorEmail() {		
//		ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();
//		
//		m.add(postulante);
//		assertThrows(IllegalArgumentException.class,() -> manejador.encontrarPorEmail(empresa.getEmail()));
//	}

  @Test
  void encuentraPorNick() {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    try {
      manejador.add(postulante);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    try {
      assertEquals(postulante, manejador.encontrarPostulante(new String(postulante.getNick())));
    } catch (NoExistePostulante e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }
  }

  @Test
  void noEncuentraPorNick() {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    try {
      manejador.add(postulante);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertThrows(NoExistePostulante.class, () -> manejador.encontrarPostulante(empresa.getNick()));
  }

  @Test
  void existePorNick() {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    try {
      manejador.add(postulante);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertTrue(manejador.existePorNick(new String(new String(postulante.getNick()))));
  }

  @Test
  void noExistePorNick() {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    try {
      manejador.add(postulante);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertFalse(manejador.existePorNick(new String(empresa.getNick())));
  }

  @Test
  void existePorEmail() {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    try {
      manejador.add(postulante);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertTrue(manejador.existePorEmail(new String(postulante.getEmail())));
  }

  @Test
  void noExistePorEmail() {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    try {
      manejador.add(postulante);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertFalse(manejador.existePorEmail(new String(empresa.getEmail())));
  }

  @Test
  void listarUsuarios() {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    try {
      manejador.add(postulante);
      manejador.add(empresa);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    Set<Usuario> usuarios = manejador.listarUsuarios();

    assertTrue(usuarios.contains(postulante));
    assertTrue(usuarios.contains(empresa));

    assertEquals(2, usuarios.size());
  }
}
