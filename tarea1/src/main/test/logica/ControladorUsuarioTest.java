package main.test.logica;

import static org.junit.Assert.assertFalse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import main.java.logica.clasesbasicas.Empresa;
import main.java.logica.clasesbasicas.OfertaLaboral;
import main.java.logica.clasesbasicas.Postulante;
import main.java.logica.clasesbasicas.TipoPublicacion;
import main.java.logica.controladores.ControladorOferta;
import main.java.logica.controladores.ControladorUsuario;
import main.java.logica.datatypes.DataEmpresa;
import main.java.logica.datatypes.DataPostulante;
import main.java.logica.datatypes.DataTipoPublicacion;
import main.java.logica.datatypes.DataUsuario;
import main.java.logica.enums.StatusOfertaLaboral;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.excepciones.NoExistePostulante;
import main.java.logica.excepciones.YaExistePaquete;
import main.java.logica.excepciones.YaExisteUsuario;
import main.java.logica.manejadores.ManejadorUsuarios;

class ControladorUsuarioTest {

  private DataPostulante dataPostulante = new DataPostulante("dieguito", "Diego", "Dorien",
      "ddorien@email.co", LocalDate.of(1961, 9, 4), "Portugesa", "fotito");
  private DataEmpresa dataEmpresa = new DataEmpresa("Jueguetrias Plastiquito", "Domina", "Damacua",
      "ddamacua@email.co", "Vendemos juguetes baratos", "https://juguetesbaratos.com", "fotito");

  private DataPostulante dataPostulanteConNick = new DataPostulante("dieguito", "Diego", "Dorien",
      "otroemail@email.co", LocalDate.of(1961, 9, 7), "Portugesa", "fotito");
  private DataEmpresa dataEmpresaConNick = new DataEmpresa("Jueguetrias Plastiquito", "Domina",
      "Damacua", "jueguetria@plastiquito.com.uy", "Vendemos juguetes baratos",
      "https://juguetesbaratos.com", "fotito");

  private DataPostulante dataPostulanteConEmail = new DataPostulante("remplon", "Diego", "Dorien",
      "ddorien@email.co", LocalDate.of(1961, 9, 10), "Portugesa", "fotito");
  private DataEmpresa dataEmpresaConEmail = new DataEmpresa("Jugetes baratos", "Domina", "Damacua",
      "ddamacua@email.co", "Vendemos juguetes baratos", "https://juguetesbaratos.com", "fotito");

  private String password1 = "Yokojfaoejfe";

  @AfterEach
  void tearDownAfterEach() {
    ManejadorUsuarios.getInstancia().clear();
  }

  @Test
  void altaPostulante() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();
    assertDoesNotThrow(() -> controladorUsuario.alta(dataPostulante, password1));
  }

  @Test
  void altaPostulanteFallaPorNick() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataPostulante, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertThrows(YaExisteUsuario.class,
        () -> controladorUsuario.alta(dataPostulanteConNick, password1));
  }

  @Test
  void altaPostulanteFallaPorEmail() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataPostulante, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertThrows(YaExisteUsuario.class,
        () -> controladorUsuario.alta(dataPostulanteConEmail, password1));
  }

  @Test
  void altaEmpresa() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    assertDoesNotThrow(() -> controladorUsuario.alta(dataEmpresa, password1));
  }

  @Test
  void altaEmpresaFallaPorNick() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataEmpresa, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertThrows(YaExisteUsuario.class,
        () -> controladorUsuario.alta(dataEmpresaConNick, password1));
  }

  @Test
  void altaEmpresaFallaPorEmail() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataEmpresa, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertThrows(YaExisteUsuario.class,
        () -> controladorUsuario.alta(dataEmpresaConEmail, password1));
  }

  @Test
  void encontrarUsuarioEncuentraUsuario() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();
    assertDoesNotThrow(() -> controladorUsuario.alta(dataPostulante, password1));
    assertDoesNotThrow(() -> assertEquals(dataPostulante,
        controladorUsuario.encontrarUser(dataPostulante.getNickname())));
  }

  @Test
  void listarDataUsuarios() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataEmpresa, password1);
      controladorUsuario.alta(dataPostulante, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    Set<DataUsuario> usuarios = controladorUsuario.listarDataUsuarios();

    assertTrue(usuarios.contains(dataPostulante));
    assertTrue(usuarios.contains(dataEmpresa));

    assertEquals(2, usuarios.size());
  }

  @Test
  void listarDataEmpresas() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataEmpresa, password1);
      controladorUsuario.alta(dataPostulante, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    Set<String> empresas = controladorUsuario.listarEmpresas();

    assertTrue(empresas.contains(new String(dataEmpresa.getNickname())));

    assertEquals(1, empresas.size());
  }

  @Test
  void modificaPostulante() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataEmpresa, password1);
      controladorUsuario.alta(dataPostulante, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    DataPostulante dataModificada = new DataPostulante(dataPostulante.getNickname(),
        dataPostulante.getNombre() + "McHuntingong", dataPostulante.getApellido(),
        dataPostulante.getEmail(), dataPostulante.getNacimiento(),
        dataPostulante.getNacionalidad(), dataPostulante.getFoto());

    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    Postulante usu;
    try {
      usu = manejador.encontrarPostulante(dataPostulante.getNickname());
    } catch (NoExistePostulante e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    Postulante guardado = usu;

    assertEquals(dataPostulante, guardado.data());
    assertNotEquals(dataModificada, guardado.data());

    try {
      controladorUsuario.actualizarPostulante(dataModificada.getNacimiento(),
          dataModificada.getNacionalidad(), dataModificada.getNickname(),
          dataModificada.getNombre(), dataModificada.getApellido(), dataModificada.getEmail(), dataModificada.getFoto());
    } catch (NoExistePostulante e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertNotEquals(dataPostulante, guardado.data());
    assertEquals(dataModificada, guardado.data());
  }

  @Test
  void modificaEmpresa() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataEmpresa, password1);
      controladorUsuario.alta(dataPostulante, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    DataEmpresa dataModificada = new DataEmpresa(dataEmpresa.getNickname(),
        dataEmpresa.getNombre() + "McHuntingong", dataEmpresa.getApellido(), dataEmpresa.getEmail(),
        dataEmpresa.getDescripcion(), dataEmpresa.getSitioWeb(), dataEmpresa.getFoto());

    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    Empresa usu;
    try {
      usu = manejador.encontrarEmpresa(dataEmpresa.getNickname());
    } catch (NoExisteEmpresa e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    Empresa guardado = usu;

    assertEquals(dataEmpresa, guardado.data());
    assertNotEquals(dataModificada, guardado.data());

    try {
      controladorUsuario.actualizarEmpresa(dataModificada.getDescripcion(),
          dataModificada.getSitioWeb(), dataModificada.getNickname(), dataModificada.getNombre(),
          dataModificada.getApellido(), dataModificada.getEmail(), dataModificada.getFoto());
    } catch (NoExisteEmpresa e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertNotEquals(dataEmpresa, guardado.data());
    assertEquals(dataModificada, guardado.data());
  }

  @Test
  void modificarPostulanteThrowPorNickDistinto() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataPostulante, password1);
      controladorUsuario.alta(dataEmpresa, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    DataPostulante dataModificada = new DataPostulante(dataPostulante.getNickname() + "cambia nick",
        dataPostulante.getNombre(), dataPostulante.getApellido(), dataPostulante.getEmail(), // Pero
                                                                                             // no
                                                                                             // email
        dataPostulante.getNacimiento(), dataPostulante.getNacionalidad(), dataPostulante.getFoto());

    assertThrows(NoExistePostulante.class,
        () -> controladorUsuario.actualizarPostulante(dataModificada.getNacimiento(),
            dataModificada.getNacionalidad(), dataModificada.getNickname(),
            dataModificada.getNombre(), dataModificada.getApellido(),
            dataModificada.getApellido(), dataModificada.getFoto()));
  }

  @Test
  void modificarEmpresaThrowPorNickDistinto() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataPostulante, password1);
      controladorUsuario.alta(dataEmpresa, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    DataEmpresa dataModificada = new DataEmpresa(dataEmpresa.getNickname() + "cambia nick",
        dataEmpresa.getNombre(), dataEmpresa.getApellido(), dataEmpresa.getEmail(), // Pero no email
        dataEmpresa.getDescripcion(), dataEmpresa.getSitioWeb(), dataEmpresa.getFoto());

    assertThrows(NoExisteEmpresa.class,
        () -> controladorUsuario.actualizarEmpresa(dataModificada.getDescripcion(),
            dataModificada.getSitioWeb(), dataModificada.getNickname(), dataModificada.getNombre(),
            dataModificada.getApellido(), dataModificada.getApellido(), dataModificada.getFoto()));
  }

  @Test
  void modificarPostulanteThrowPorEmailDistinto() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataPostulante, password1);
      controladorUsuario.alta(dataEmpresa, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    DataPostulante dataModificada = new DataPostulante(dataPostulante.getNickname(), // no cambia
                                                                                     // nick
        dataPostulante.getNombre(), dataPostulante.getApellido(),
        dataPostulante.getEmail() + "pero si email", dataPostulante.getNacimiento(),
        dataPostulante.getNacionalidad(), dataPostulante.getFoto());

    assertThrows(NoExistePostulante.class,
        () -> controladorUsuario.actualizarPostulante(dataModificada.getNacimiento(),
            dataModificada.getNacionalidad(), dataModificada.getNickname(),
            dataModificada.getNombre(), dataModificada.getApellido(),
            dataModificada.getApellido(), dataModificada.getFoto()));

  }

  @Test
  void modificarEmpresaThrowPorEmailDistinto() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataPostulante, password1);
      controladorUsuario.alta(dataEmpresa, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    DataEmpresa dataModificada = new DataEmpresa(dataEmpresa.getNickname(), // no cambia nick
        dataEmpresa.getNombre(), dataEmpresa.getApellido(),
        dataEmpresa.getEmail() + "pero si email", dataEmpresa.getDescripcion(),
        dataEmpresa.getSitioWeb(), dataEmpresa.getFoto());

    assertThrows(NoExisteEmpresa.class,
        () -> controladorUsuario.actualizarEmpresa(dataModificada.getDescripcion(),
            dataModificada.getSitioWeb(), dataModificada.getNickname(), dataModificada.getNombre(),
            dataModificada.getApellido(), dataModificada.getApellido(), dataEmpresa.getFoto()));
  }

  // public void actualizarPostulante(LocalDate fecNac, String nacionalidad,
  // String nick, String nombre, String apellido, String email) throws
  // IllegalArgumentException
  @Test
  void actualizarPostulanteInexistenteThrows() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    assertThrows(NoExistePostulante.class,
        () -> controladorUsuario.actualizarPostulante(dataPostulante.getNacimiento(),
            dataPostulante.getNacionalidad(), dataPostulante.getNickname(),
            dataPostulante.getNombre(), dataPostulante.getApellido(), dataPostulante.getEmail(), dataPostulante.getFoto()));
  }

  @Test
  void actualizarPostulanteConEmpresaThrows() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(dataEmpresa, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertThrows(NoExistePostulante.class,
        () -> controladorUsuario.actualizarPostulante(dataPostulante.getNacimiento(),
            dataPostulante.getNacionalidad(), dataEmpresa.getNickname(), dataPostulante.getNombre(),
            dataPostulante.getApellido(), dataPostulante.getEmail(), dataPostulante.getFoto()));
  }

  @Test
  void actualizarPostulanteActualizaPostulante() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    Postulante postulante = new Postulante(dataPostulante, password1);

    try {
      ManejadorUsuarios.getInstancia().add(postulante);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    String nuevaNacionalidad = dataPostulante.getNacionalidad() + "Pepepe";

    assertEquals(dataPostulante, postulante.data());

    try {
      controladorUsuario.actualizarPostulante(dataPostulante.getNacimiento(), nuevaNacionalidad,
          dataPostulante.getNickname(), dataPostulante.getNombre(), dataPostulante.getApellido(),
          dataPostulante.getEmail(), dataPostulante.getFoto());
    } catch (NoExistePostulante e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertNotEquals(dataPostulante, postulante.data());
  }

  @Test
  void actualizarPostulanteConEmailDistintoThrows() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    Postulante postulante = new Postulante(dataPostulante, password1);

    try {
      ManejadorUsuarios.getInstancia().add(postulante);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    String nuevoEmail = dataPostulante.getEmail() + "Pepepe";

    assertThrows(NoExistePostulante.class,
        () -> controladorUsuario.actualizarPostulante(dataPostulante.getNacimiento(),
            dataPostulante.getNacionalidad(), dataPostulante.getNickname(),
            dataPostulante.getNombre(), dataPostulante.getApellido(), nuevoEmail, dataPostulante.getFoto()));
  }

  // public Set<DataOfertaLaboral> listarOfertasLaborales(String nick) throws
  // IllegalArgumentException;
  @Test
  void listarOfertasLaboralesSinEmpresaThrows() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    assertThrows(NoExisteEmpresa.class,
        () -> controladorUsuario.listarOfertasLaborales(dataEmpresa.getNickname(), false));
  }

  @Test
  void listarOfertasLaboralesConEmpresaEsVacia() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    Empresa empresa = new Empresa(dataEmpresa, password1);

    try {
      ManejadorUsuarios.getInstancia().add(empresa);
    } catch (YaExisteUsuario e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }

    try {
      assertTrue(
          controladorUsuario.listarOfertasLaborales(dataEmpresa.getNickname(), false).isEmpty());
    } catch (NoExisteEmpresa e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }
  }

  @Test
  void listarOfertasLaboralesConEmpresaConOfertaNoEsVacia() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    Empresa empresa = new Empresa(dataEmpresa, password1);

    try {
      ManejadorUsuarios.getInstancia().add(empresa);
    } catch (YaExisteUsuario e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }

    OfertaLaboral oferta = new OfertaLaboral("Nombre", "Desc", 0, "Horario", "Ciudad",
        "Departamento", LocalDate.of(1961, 9, 4), new HashSet<String>(),
        new TipoPublicacion(
            new DataTipoPublicacion("nombre", "desc", 0, 0, 0, LocalDate.of(1961, 9, 4))),
        StatusOfertaLaboral.Ingresada, "", empresa, null);

    empresa.agregarOferta(oferta);

    try {
      assertFalse(
          controladorUsuario.listarOfertasLaborales(dataEmpresa.getNickname(), false).isEmpty());
    } catch (NoExisteEmpresa e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }
  }

  @Test
  void verificarCredencialesExito() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    Empresa empresa = new Empresa(dataEmpresa, password1);

    try {
      ManejadorUsuarios.getInstancia().add(empresa);
    } catch (YaExisteUsuario e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }

    assertEquals(empresa.data(),
        controladorUsuario.verificarCredenciales(empresa.getNick(), password1));
  }

  @Test
  void verificarCredencialesFallo() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    Empresa empresa = new Empresa(dataEmpresa, password1);

    try {
      ManejadorUsuarios.getInstancia().add(empresa);
    } catch (YaExisteUsuario e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }

    assertEquals(null,
        controladorUsuario.verificarCredenciales(empresa.getNick(), empresa.getApellido()));
  }

  @Test
  void verificarCredencialesNoHayUsuario() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    assertEquals(null,
        controladorUsuario.verificarCredenciales(dataEmpresa.getNickname(), password1));
  }

  @Test
  void listarOfertasLaboralesDeEmpresa() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    Empresa empresa = new Empresa(dataEmpresa, password1);

    try {
      ManejadorUsuarios.getInstancia().add(empresa);
    } catch (YaExisteUsuario e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }

    OfertaLaboral oferta = new OfertaLaboral("Nombre", "Desc", 0, "Horario", "Ciudad",
        "Departamento", LocalDate.of(1961, 9, 4), new HashSet<String>(),
        new TipoPublicacion(
            new DataTipoPublicacion("nombre", "desc", 0, 0, 0, LocalDate.of(1961, 9, 4))),
        StatusOfertaLaboral.Ingresada, "", empresa, null);

    empresa.agregarOferta(oferta);

    try {
      assertFalse(
          controladorUsuario.listarOfertasLaborales(dataEmpresa.getNickname(), false).isEmpty());
    } catch (NoExisteEmpresa e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }
  }

  @Test
  void listarOfertasLaboralesIngresadas() {
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    Empresa empresa = new Empresa(dataEmpresa, password1);

    try {
      ManejadorUsuarios.getInstancia().add(empresa);
    } catch (YaExisteUsuario e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }

    OfertaLaboral oferta = new OfertaLaboral("Nombre", "Desc", 0, "Horario", "Ciudad",
        "Departamento", LocalDate.of(1961, 9, 4), new HashSet<String>(),
        new TipoPublicacion(
            new DataTipoPublicacion("nombre", "desc", 0, 0, 0, LocalDate.of(1961, 9, 4))),
        StatusOfertaLaboral.Ingresada, "", empresa, null);

    empresa.agregarOferta(oferta);

    try {
      assertFalse(
          controladorUsuario.listarOfertasLaboralesIngresadas(dataEmpresa.getNickname()).isEmpty());
    } catch (NoExisteEmpresa e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }
  }

  // public void agregarPaquete(String nomP, String nomE) throws NoExisteEmpresa,
  // NoExistePaquete {
  @Test
  void agregarPaqueteAgregaPaqueteNotThrows() {
    Empresa empresa = new Empresa(dataEmpresa, password1);
    
    ControladorUsuario controladorUsuario = new ControladorUsuario();


    try {
      controladorUsuario.alta(empresa.data(), "contrasenia");
    } catch (YaExisteUsuario e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }

    try {
      ControladorOferta controladorOferta = new ControladorOferta();

      controladorOferta.insertarDatosPaquete("paquete", "paquete de prueba", 5, 20, LocalDate.now(), "");

    } catch (YaExistePaquete e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }
    
        
    assertDoesNotThrow(() -> controladorUsuario.agregarPaquete("paquete", dataEmpresa.getNickname()));
  }
  
  @Test
  void listarPaquetesListaPaquetes() {
    Empresa empresa = new Empresa(dataEmpresa, password1);
    
    ControladorUsuario controladorUsuario = new ControladorUsuario();


    try {
      controladorUsuario.alta(empresa.data(), "contrasenia");
    } catch (YaExisteUsuario e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }

    try {
      ControladorOferta controladorOferta = new ControladorOferta();

      controladorOferta.insertarDatosPaquete("paquete2", "paquete de prueba", 5, 20, LocalDate.now(), "");

      controladorUsuario.agregarPaquete("paquete2", dataEmpresa.getNickname());
      assertFalse(controladorUsuario.listarPaquetesDeEmpresa(dataEmpresa.getNickname()).isEmpty());

    } catch (YaExistePaquete | NoExisteEmpresa | NoExistePaquete e1) {
      e1.printStackTrace();
      fail(e1.getMessage());
      return;
    }
  }

}
