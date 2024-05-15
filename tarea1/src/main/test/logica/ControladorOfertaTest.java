package main.test.logica;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.logica.clasesbasicas.Empresa;
import main.java.logica.controladores.ControladorOferta;
import main.java.logica.controladores.ControladorUsuario;
import main.java.logica.datatypes.DataEmpresa;
import main.java.logica.datatypes.DataOfertaLaboral;
import main.java.logica.datatypes.DataPaquete;
import main.java.logica.datatypes.DataPostulante;
import main.java.logica.datatypes.DataTipoPublicacion;
import main.java.logica.datatypes.DataTipoPublicacionPaquete;
import main.java.logica.enums.StatusOfertaLaboral;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExisteKeyword;
import main.java.logica.excepciones.NoExisteOferta;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.excepciones.NoExistePostulante;
import main.java.logica.excepciones.NoExisteTipoPaquete;
import main.java.logica.excepciones.NoExisteTipoPublicacion;
import main.java.logica.excepciones.NoSuficienteTipoPubliEnPaquete;
import main.java.logica.excepciones.OfertaLaboralNoVigente;
import main.java.logica.excepciones.RemuneracionNegativa;
import main.java.logica.excepciones.YaExisteKeyword;
import main.java.logica.excepciones.YaExisteOferta;
import main.java.logica.excepciones.YaExistePaquete;
import main.java.logica.excepciones.YaExistePostulacion;
import main.java.logica.excepciones.YaExisteTipoEnPaquete;
import main.java.logica.excepciones.YaExisteTipoPublicacion;
import main.java.logica.excepciones.YaExisteUsuario;
import main.java.logica.manejadores.ManejadorO;
import main.java.logica.manejadores.ManejadorP;
import main.java.logica.manejadores.ManejadorUsuarios;

class ControladorOfertaTest {

  private DataTipoPublicacion tipoPublicacion1 =
      new DataTipoPublicacion("Nombre", "Descripcion", 3, 10, 350, LocalDate.of(1343, 5, 8));
  private DataTipoPublicacion tipoPublicacion2 = new DataTipoPublicacion("Otro nombre",
      "Otra descripcion", 3, 10, 350, LocalDate.of(1343, 5, 8));

  private DataEmpresa dataEmpresa =
      new DataEmpresa("Jueguetrias Plastiquito", "Domina", "Damacua", "ddamacua@email.co",
          "Vendemos juguetes baratos", "https://juguetesbaratos.com", "String fotinha");

  private String nombrePaquete = "Nombre";
  private String descripcionPaquete = "Descripcion";
  private int duracionPaquete = 5;
  private float descuentoPaquete = 20;
  private LocalDate fechaPaquete = LocalDate.of(2023, 8, 14);
  private float costoPaquete = 20;

  private DataOfertaLaboral oferta1 = new DataOfertaLaboral("Nombre", "Descripcion", 100, "Horario",
      "Ciudad", "Departamento", LocalDate.of(2023, 8, 8), tipoPublicacion1, new HashSet<String>(),
      StatusOfertaLaboral.Ingresada, "", dataEmpresa, nombrePaquete);
  private DataOfertaLaboral oferta1ConMismoNombre =
      new DataOfertaLaboral(new String("Nombre"), "Otra descripcion", 99, "Otro horario",
          "Otra ciudad", "Otro departamento", LocalDate.of(2023, 9, 9), tipoPublicacion1,
          new HashSet<String>(), StatusOfertaLaboral.Ingresada, "", dataEmpresa, nombrePaquete);

  private String password1 = "Yujuuu la contrasena";

  private String nombreOferta = "Nombre";
  private String descripcionOferta = "Descripcion";
  private String horarioOferta = "Horarios";
  private float remuneracionOferta = 22000;
  private String ciudadOferta = "Ciudad";
  private String departamentoOferta = "Departamento";
  private LocalDate fechaAltaOferta = LocalDate.of(2004, 5, 6);
  private String fotoEmpresa = "Fotinha";

  private Set<String> keywords1 = new HashSet<String>(Arrays.asList("k1", "k2"));
  private Set<String> keywords2 = new HashSet<String>(Arrays.asList("k3", "k4", "k5"));
  private Set<String> keywords3 = new HashSet<String>(Arrays.asList("k6", "k7", "k8"));

  private String nombreEmpresa = "Nombre empresa";

  private DataPostulante postulante = new DataPostulante("Nick", "Nombre", "Apellido", "email",
      fechaAltaOferta, "Nacionalidad", "String fotinha");

  @BeforeEach
  void doBeforeEach() {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    try {
      manejador.add(new Empresa(nombreEmpresa, ciudadOferta, ciudadOferta, ciudadOferta,
          ciudadOferta, ciudadOferta, password1, fotoEmpresa));
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    ControladorOferta controlador = new ControladorOferta();

    final Iterator<String> it1 = keywords1.iterator();

    while (it1.hasNext()) {
      assertDoesNotThrow(() -> controlador.nomKeyword(it1.next()));
    }

    final Iterator<String> it2 = keywords2.iterator();

    while (it2.hasNext()) {
      assertDoesNotThrow(() -> controlador.nomKeyword(it2.next()));
    }
  }

  @AfterEach
  void tearDownAfterEach() {
    ManejadorO.getInstancia().clear();
    ManejadorP.getInstancia().clear();
    ManejadorUsuarios.getInstancia().clear();
  }

  // public void insertarTipoPublicacion(DataTipoPublicacion data) throws
  // IllegalArgumentException
  @Test
  void insertarTipoPublicacionListaPublicaciones() {
    ControladorOferta controlador = new ControladorOferta();

    Set<DataTipoPublicacion> listados = controlador.listarTipos();

    assertTrue(listados.isEmpty());

    assertDoesNotThrow(() -> controlador.insertarTipoPublicacion(tipoPublicacion1));

    listados = controlador.listarTipos();

    assertEquals(1, listados.size());

    assertTrue(listados.contains(tipoPublicacion1));
  }

  @Test
  void existeTipoDePublicacion() {
    ControladorOferta controlador = new ControladorOferta();

    // Como no tenemos tests de ManejadorO, tengo que verificarlo por aca
    ManejadorO manejadorOferta = ManejadorO.getInstancia();

    assertDoesNotThrow(() -> controlador.insertarTipoPublicacion(tipoPublicacion1));

    assertTrue(manejadorOferta.existeTP(tipoPublicacion1.getNombre()));
  }

  @Test
  void insertarTipoDePublicacionDosVecesThrows() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarTipoPublicacion(tipoPublicacion1));
    assertThrows(YaExisteTipoPublicacion.class,
        () -> controlador.insertarTipoPublicacion(tipoPublicacion1));
  }

  // public void insertarDatosOferta(String nombre, String desc, String horario,
  // float remu, String ciudad, String departamento, LocalDate fechaAlta, String[]
  // k) throws YaExisteOferta
  @Test
  void insertarOfertaUnicaDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(oferta1.getNombre(),
        oferta1.getDescOL(), oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(),
        oferta1.getDepartamento(), oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(),
        nombreEmpresa, StatusOfertaLaboral.Ingresada, "", nombrePaquete));
  }

  @Test
  void insertarOfertaSinTipoThrows() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertThrows(NoExisteTipoPublicacion.class,
        () -> controlador.insertarDatosOferta(oferta1.getNombre(), oferta1.getDescOL(),
            oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(), oferta1.getDepartamento(),
            oferta1.getFecAlta(), keywords1, tipoPublicacion2.getNombre(), nombreEmpresa,
            StatusOfertaLaboral.Ingresada, "", nombrePaquete));
  }

  @Test
  void insertarDosVecesUnicaThrows() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(oferta1.getNombre(),
        oferta1.getDescOL(), oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(),
        oferta1.getDepartamento(), oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(),
        nombreEmpresa, StatusOfertaLaboral.Ingresada, "", nombrePaquete));

    assertThrows(YaExisteOferta.class,
        () -> controlador.insertarDatosOferta(oferta1.getNombre(), oferta1.getDescOL(),
            oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(), oferta1.getDepartamento(),
            oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(), nombreEmpresa,
            StatusOfertaLaboral.Ingresada, "", nombrePaquete));
  }

  @Test
  void insertarDosConMismoNombreThrows() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteTipoPublicacion e) {
      e.printStackTrace();
      e.getMessage();
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(oferta1.getNombre(),
        oferta1.getDescOL(), oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(),
        oferta1.getDepartamento(), oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(),
        nombreEmpresa, StatusOfertaLaboral.Ingresada, "", nombrePaquete));

    assertThrows(YaExisteOferta.class,
        () -> controlador.insertarDatosOferta(oferta1ConMismoNombre.getNombre(),
            oferta1ConMismoNombre.getDescOL(), oferta1ConMismoNombre.getHorario(),
            oferta1ConMismoNombre.getRemu(), oferta1ConMismoNombre.getCiudad(),
            oferta1ConMismoNombre.getDepartamento(), oferta1ConMismoNombre.getFecAlta(), keywords1,
            tipoPublicacion1.getNombre(), nombreEmpresa, StatusOfertaLaboral.Ingresada, "",
            nombrePaquete));
  }

  @Test
  void insertarConPaqueteQueNoAlcanzaThrows() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
      controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete, duracionPaquete,
          descuentoPaquete, fechaAltaOferta, ciudadOferta);
    } catch (YaExisteTipoPublicacion | YaExistePaquete e) {
      e.printStackTrace();
      e.getMessage();
    }

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.agregarPaquete(nombrePaquete, nombreEmpresa);
    } catch (NoExisteEmpresa | NoExistePaquete exception) {
      fail(exception.getMessage());
      exception.printStackTrace();
    }

    assertThrows(NoSuficienteTipoPubliEnPaquete.class,
        () -> controlador.insertarDatosOferta(oferta1.getNombre(), oferta1.getDescOL(),
            oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(), oferta1.getDepartamento(),
            oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(), nombreEmpresa,
            StatusOfertaLaboral.Ingresada, "", nombrePaquete));
  }

  @Test
  void insertarConPaqueteQueAlcanzaDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
      controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete, duracionPaquete,
          descuentoPaquete, fechaAltaOferta, ciudadOferta);
      controlador.agregarTipo(tipoPublicacion1.getNombre(), nombrePaquete, 5);
    } catch (YaExisteTipoPublicacion | YaExistePaquete | YaExisteTipoEnPaquete | NoExisteTipoPaquete
        | NoExistePaquete | NoExisteTipoPublicacion e) {
      e.printStackTrace();
      e.getMessage();
    }

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.agregarPaquete(nombrePaquete, nombreEmpresa);
    } catch (NoExisteEmpresa | NoExistePaquete exception) {
      fail(exception.getMessage());
      exception.printStackTrace();
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(oferta1.getNombre(),
        oferta1.getDescOL(), oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(),
        oferta1.getDepartamento(), oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(),
        nombreEmpresa, StatusOfertaLaboral.Ingresada, "", nombrePaquete));
  }

  @Test
  void listarTiposSinTiposListaCeroTipos() {
    ControladorOferta controlador = new ControladorOferta();

    assertTrue(controlador.listarTipos().isEmpty());
  }

  /// public void nomKeyword(String nombre) throws YaExisteKeyword
  @Test
  void agregarKeywordDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.nomKeyword("Una keyword uwu"));
  }

  @Test
  void agregarDosKeywordsDistintasDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.nomKeyword("Una keyword uwu"));
    assertDoesNotThrow(() -> controlador.nomKeyword("Otra keyword ovo"));
  }

  @Test
  void agregarDosKeywordsIgualesThrows() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.nomKeyword("Una keyword uwu"));
    assertThrows(YaExisteKeyword.class,
        () -> controlador.nomKeyword(new String("Una keyword uwu")));
  }

  // public void insertarDatosPaquete(String nombre, String desc, int duracion,
  // float descuento, LocalDate fecha) throws YaExistePaquete
  @Test
  void insertarPaqueteDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete,
        duracionPaquete, descuentoPaquete, fechaPaquete, ""));
  }

  @Test
  void insertarDosVecesMismoPaqueteThrows() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete,
        duracionPaquete, descuentoPaquete, fechaPaquete, ""));
    assertThrows(YaExistePaquete.class, () -> controlador.insertarDatosPaquete(nombrePaquete,
        descripcionPaquete, duracionPaquete, descuentoPaquete, fechaPaquete, ""));
  }

  @Test
  void insertarDosPaquetesMismoNombreThrows() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete,
        duracionPaquete, descuentoPaquete, fechaPaquete, ""));
    assertThrows(YaExistePaquete.class,
        () -> controlador.insertarDatosPaquete(new String(nombrePaquete), "Otra desc", 1, 78,
            LocalDate.of(1999, 6, 6), ""));
  }

  @Test
  void insertarDosPaquetesDistintoDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete,
        duracionPaquete, descuentoPaquete, fechaPaquete, ""));
    assertThrows(YaExistePaquete.class, () -> controlador.insertarDatosPaquete(nombrePaquete,
        descripcionPaquete, duracionPaquete, descuentoPaquete, fechaPaquete, ""));
  }

  /*
   * Mismo arugmento que para la otra. Devolver Set vacio en vez de un throw //
   * public Set<String> listarPaquetes() throws NoHayParaListarPaquetes
   */
  @Test
  void listarPaquetesSinPaquetesIsEmpty() {
    ControladorOferta controlador = new ControladorOferta();

    assertTrue(controlador.listarPaquetes().isEmpty());
  }

  @Test
  void listarConUnPaqueteDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete,
        duracionPaquete, descuentoPaquete, fechaPaquete, ""));

    assertDoesNotThrow(() -> controlador.listarPaquetes());
  }

  @Test
  void listarConUnPaqueteDevuelveUnPaquete() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete,
        duracionPaquete, descuentoPaquete, fechaPaquete, ""));

    int cuentaDeListados = controlador.listarPaquetes().size();
    assertEquals(1, cuentaDeListados);
  }

  @Test
  void listarConUnPaqueteDevuelveElPaquete() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete,
        duracionPaquete, descuentoPaquete, fechaPaquete, ""));

    Set<DataPaquete> listados = controlador.listarPaquetes();
    assertEquals(1, listados.size());
    assertTrue(listados.contains(new DataPaquete(nombrePaquete, descripcionPaquete, duracionPaquete,
        descuentoPaquete, fechaPaquete,
        (Set<DataTipoPublicacionPaquete>) new HashSet<DataTipoPublicacionPaquete>(), "", costoPaquete)));
  }

  @Test
  void listarConUnPaqueteConTipoPublicacion() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
      controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete, duracionPaquete,
          descuentoPaquete, fechaPaquete, "");
      controlador.agregarTipo(nombrePaquete, nombreTipoPaquete, cant);
    } catch (YaExisteTipoEnPaquete | NoExisteTipoPaquete | NoExistePaquete | NoExisteTipoPublicacion
        | YaExisteTipoPublicacion | YaExistePaquete e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    Set<DataPaquete> listados = controlador.listarPaquetes();
    assertEquals(1, listados.size());
    assertTrue(listados.contains(new DataPaquete(nombrePaquete, descripcionPaquete, duracionPaquete,
        descuentoPaquete, fechaPaquete,
        (Set<DataTipoPublicacionPaquete>) new HashSet<DataTipoPublicacionPaquete>(), "", costoPaquete)));
  }

  // public void insertarDatosOferta(String nombre, String desc, String horario,
  // float remuneracion, String ciudad, String departamento, LocalDate fechAlta,
  // Set<String> keywords) throws YaExisteOferta
  @Test
  void insertarDatosOfertaDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(nombreOferta, descripcionOferta,
        horarioOferta, remuneracionOferta, ciudadOferta, departamentoOferta, fechaAltaOferta,
        keywords1, tipoPublicacion1.getNombre(), nombreEmpresa, StatusOfertaLaboral.Ingresada, "",
        nombrePaquete));
  }

  @Test
  void insertarDosVecesDatosOfertaThrows() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(nombreOferta, descripcionOferta,
        horarioOferta, remuneracionOferta, ciudadOferta, departamentoOferta, fechaAltaOferta,
        keywords1, tipoPublicacion1.getNombre(), nombreEmpresa, StatusOfertaLaboral.Ingresada, "",
        nombrePaquete));
    assertThrows(YaExisteOferta.class,
        () -> controlador.insertarDatosOferta(nombreOferta, descripcionOferta, horarioOferta,
            remuneracionOferta, ciudadOferta, departamentoOferta, fechaAltaOferta, keywords1,
            tipoPublicacion1.getNombre(), nombreEmpresa, StatusOfertaLaboral.Ingresada, "",
            nombrePaquete));
  }

  @Test
  void insertarOfertaLaboralConKeywordFaltanteThrows() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertThrows(NoExisteKeyword.class,
        () -> controlador.insertarDatosOferta(nombreOferta, descripcionOferta, horarioOferta,
            remuneracionOferta, ciudadOferta, departamentoOferta, fechaAltaOferta, keywords3,
            tipoPublicacion1.getNombre(), nombreEmpresa, StatusOfertaLaboral.Ingresada, "",
            nombrePaquete));
  }

  @Test
  void insertarOfertaLaboralConRemuneracionNegativaThrows() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertThrows(RemuneracionNegativa.class,
        () -> controlador.insertarDatosOferta(nombreOferta, descripcionOferta, horarioOferta, -10,
            ciudadOferta, departamentoOferta, fechaAltaOferta, keywords1,
            tipoPublicacion1.getNombre(), nombreEmpresa, StatusOfertaLaboral.Ingresada, "",
            nombrePaquete));
  }

  // agregarTipo(String nomPaquete, String tp, int cant) throws
  // YaExisteTipoEnPaquete
  private String nombreTipo = "Nombre";
  private String nombreTipoPaquete = "Tipo paquete";
  private int cant = 6;

  @Test
  void agregarTipoPaqueteInexistenteThrows() {
    ControladorOferta controlador = new ControladorOferta();

    assertThrows(NoExistePaquete.class,
        () -> controlador.agregarTipo(nombreTipo, nombreTipoPaquete, cant));
  }

  @Test
  void agregarTipoExistenteDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete,
        duracionPaquete, descuentoPaquete, fechaPaquete, ""));
    assertDoesNotThrow(() -> controlador.insertarTipoPublicacion(tipoPublicacion1));

    assertDoesNotThrow(
        () -> controlador.agregarTipo(nombrePaquete, tipoPublicacion1.getNombre(), cant));
  }

  @Test
  void agregarTipoExistenteDosVecesThrows() {
    ControladorOferta controlador = new ControladorOferta();

    assertDoesNotThrow(() -> controlador.insertarDatosPaquete(nombrePaquete, descripcionPaquete,
        duracionPaquete, descuentoPaquete, fechaPaquete, ""));
    assertDoesNotThrow(() -> controlador.insertarTipoPublicacion(tipoPublicacion1));

    assertDoesNotThrow(
        () -> controlador.agregarTipo(nombrePaquete, tipoPublicacion1.getNombre(), cant));
    assertThrows(YaExisteTipoEnPaquete.class,
        () -> controlador.agregarTipo(nombrePaquete, tipoPublicacion1.getNombre(), cant));
  }

  private String curriculum = "curriculum";
  private String motivacion = "motivacion";

  // public void postulacionOfertaLaboral(String nomOf, String nick, String
  // curriculum,
  // String motivacion, LocalDate fecha)throws NoExisteOferta, NoExistePostulante,
  // YaExistePostulacion
  @Test
  void postularOfertaLaboralSinPostulanteThrows() {
    ControladorOferta controlador = new ControladorOferta();
    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
      controladorUsuario.alta(dataEmpresa, password1);
      controlador.insertarOfertaLaboral(oferta1, dataEmpresa);
    } catch (YaExisteOferta | NoExisteTipoPublicacion | NoExisteKeyword | NoExisteEmpresa
        | YaExisteTipoPublicacion | YaExisteUsuario | NoExistePaquete | RemuneracionNegativa
        | NoSuficienteTipoPubliEnPaquete e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertThrows(NoExistePostulante.class, () -> controlador.postulacionOfertaLaboral(nombreOferta,
        nombreEmpresa, curriculum, motivacion, fechaPaquete));
  }

  @Test
  void postularOfertaLaboralConNickEmpresaThrows() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
      controlador.insertarDatosOferta(oferta1.getNombre(), oferta1.getDescOL(),
          oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(), oferta1.getDepartamento(),
          oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(), nombreEmpresa,
          StatusOfertaLaboral.Ingresada, "", nombrePaquete);
    } catch (YaExisteTipoPublicacion | YaExisteOferta | NoExisteTipoPublicacion | NoExisteKeyword
        | NoExisteEmpresa | RemuneracionNegativa | NoExistePaquete
        | NoSuficienteTipoPubliEnPaquete e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertThrows(NoExistePostulante.class, () -> controlador.postulacionOfertaLaboral(nombreOferta,
        nombreEmpresa, curriculum, motivacion, fechaPaquete));
  }

  @Test
  void postularOfertaLaboralSinEmpresaThrows() {
    ControladorOferta controlador = new ControladorOferta();

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(postulante, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertThrows(NoExisteOferta.class, () -> controlador.postulacionOfertaLaboral(nombreOferta,
        postulante.getNickname(), curriculum, motivacion, fechaPaquete));
  }

  @Test
  void postularOfertaLaboralSinOfertaThrows() {
    ControladorOferta controlador = new ControladorOferta();

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(postulante, password1);
    } catch (YaExisteUsuario e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertThrows(NoExisteOferta.class, () -> controlador.postulacionOfertaLaboral(nombreOferta,
        postulante.getNickname(), curriculum, motivacion, fechaPaquete));
  }

  @Test
  void postularOfertaLaboralConOfertaDoesNotThrow() {
    ControladorOferta controlador = new ControladorOferta();

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(postulante, password1);
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteUsuario | YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());

    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(oferta1.getNombre(),
        oferta1.getDescOL(), oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(),
        oferta1.getDepartamento(), oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(),
        nombreEmpresa, StatusOfertaLaboral.Confirmada, "", nombrePaquete));

    assertDoesNotThrow(() -> controlador.postulacionOfertaLaboral(nombreOferta,
        postulante.getNickname(), curriculum, motivacion, fechaPaquete));
  }

  @Test
  void postularOfertaLaboralConOfertaDosVecesThrows() {
    ControladorOferta controlador = new ControladorOferta();

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(postulante, password1);
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteUsuario | YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(oferta1.getNombre(),
        oferta1.getDescOL(), oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(),
        oferta1.getDepartamento(), oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(),
        nombreEmpresa, StatusOfertaLaboral.Confirmada, "", nombrePaquete));

    assertDoesNotThrow(() -> controlador.postulacionOfertaLaboral(nombreOferta,
        postulante.getNickname(), curriculum, motivacion, fechaPaquete));
    assertThrows(YaExistePostulacion.class, () -> controlador.postulacionOfertaLaboral(nombreOferta,
        postulante.getNickname(), curriculum, motivacion, fechaPaquete));
  }

  @Test
  void postularOfertaLaboralVencidaThrows() {

    LocalDate momentoDePostulacion = LocalDate.of(2023, 6, 20);
    LocalDate momentoDePublicacion = LocalDate.of(2023, 6, 5);

    // Asegurarse de que la oferta esta vencida
    // O sea que la diferencia entre el momento de postulacion y el de publicacion
    // sea mas grande que la duracion de la postulacion
    assertTrue(momentoDePostulacion.isAfter(momentoDePublicacion));

    ControladorOferta controlador = new ControladorOferta();

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(postulante, password1);
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteUsuario | YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(oferta1.getNombre(),
        oferta1.getDescOL(), oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(),
        oferta1.getDepartamento(), momentoDePublicacion, keywords1, tipoPublicacion1.getNombre(),
        nombreEmpresa, StatusOfertaLaboral.Ingresada, "", nombrePaquete));

    assertTrue(
        momentoDePostulacion.compareTo(momentoDePublicacion) > tipoPublicacion1.getDuracion());

    assertThrows(OfertaLaboralNoVigente.class,
        () -> controlador.postulacionOfertaLaboral(nombreOferta, postulante.getNickname(),
            curriculum, motivacion, momentoDePostulacion));
  }

  @Test
  void postularOfertaLaboralAntesDePublicadaThrows() {

    LocalDate momentoDePostulacion = LocalDate.of(2023, 3, 20);
    LocalDate momentoDePublicacion = LocalDate.of(2023, 6, 5);

    // Asegurarse de que la oferta esta vencida
    // O sea que la diferencia entre el momento de postulacion y el de publicacion
    // sea mas grande que la duracion de la postulacion
    assertTrue(momentoDePublicacion.isAfter(momentoDePostulacion));

    ControladorOferta controlador = new ControladorOferta();

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(postulante, password1);
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteUsuario | YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(oferta1.getNombre(),
        oferta1.getDescOL(), oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(),
        oferta1.getDepartamento(), momentoDePublicacion, keywords1, tipoPublicacion1.getNombre(),
        nombreEmpresa, StatusOfertaLaboral.Ingresada, "", nombrePaquete));

    assertThrows(OfertaLaboralNoVigente.class,
        () -> controlador.postulacionOfertaLaboral(nombreOferta, postulante.getNickname(),
            curriculum, motivacion, momentoDePostulacion));
  }

  @Test
  void postularOfertaLaboralNoConfirmadaThrows() {

    LocalDate momentoDePostulacion = LocalDate.of(2023, 3, 20);
    LocalDate momentoDePublicacion = LocalDate.of(2023, 1, 5);
    StatusOfertaLaboral status = StatusOfertaLaboral.Ingresada;

    ControladorOferta controlador = new ControladorOferta();

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(postulante, password1);
      controlador.insertarTipoPublicacion(tipoPublicacion1);
      controlador.insertarDatosOferta(oferta1.getNombre(), oferta1.getDescOL(),
          oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(), oferta1.getDepartamento(),
          momentoDePublicacion, keywords1, tipoPublicacion1.getNombre(), nombreEmpresa, status, "",
          null);
    } catch (YaExisteUsuario | YaExisteTipoPublicacion | YaExisteOferta | NoExisteTipoPublicacion
        | NoExisteKeyword | NoExisteEmpresa | RemuneracionNegativa | NoExistePaquete
        | NoSuficienteTipoPubliEnPaquete e) {
      e.printStackTrace();
      fail(e.getMessage());
      return;
    }

    assertThrows(OfertaLaboralNoVigente.class,
        () -> controlador.postulacionOfertaLaboral(nombreOferta, postulante.getNickname(),
            curriculum, motivacion, momentoDePostulacion));
  }

  @Test
  void listarKeywordsListaKeywords() {
    ControladorOferta controlador = new ControladorOferta();

    assertEquals(5, controlador.listarKeywords().size());
  }

  @Test
  void listarOfertasLaboralesListaOfertasLaboralesVacio() {
    ControladorOferta controlador = new ControladorOferta();

    assertTrue(controlador.listarOfertasLaborales().isEmpty());
  }

  @Test
  void listarOfertasLaboralesListaOfertasLaborales() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    assertDoesNotThrow(() -> controlador.insertarDatosOferta(oferta1.getNombre(),
        oferta1.getDescOL(), oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(),
        oferta1.getDepartamento(), oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(),
        nombreEmpresa, StatusOfertaLaboral.Confirmada, "", null));

    assertEquals(1, controlador.listarOfertasLaborales().size());
  }

  @Test
  void listarOfertasLaboralesPorKeywordVacio() {
    ControladorOferta controlador = new ControladorOferta();

    String keyword = keywords1.iterator().next();

    try {
      assertTrue(controlador.listarOfertasLaboralesPorKeyword(keyword).isEmpty());
    } catch (NoExisteKeyword e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void listarOfertasLaboralesPorKeywordThrows() {
    ControladorOferta controlador = new ControladorOferta();

    String keyword = "No existe";

    assertThrows(NoExisteKeyword.class,
        () -> controlador.listarOfertasLaboralesPorKeyword(keyword).isEmpty());
  }

  @Test
  void listarOfertasLaboralesPorKeyword() {
    ControladorOferta controlador = new ControladorOferta();

    String keyword = keywords1.iterator().next();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
      controlador.insertarDatosOferta(oferta1.getNombre(), oferta1.getDescOL(),
          oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(), oferta1.getDepartamento(),
          oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(), nombreEmpresa,
          StatusOfertaLaboral.Confirmada, "", null);
    } catch (YaExisteTipoPublicacion | YaExisteOferta | NoExisteTipoPublicacion | NoExisteKeyword
        | NoExisteEmpresa | RemuneracionNegativa | NoExistePaquete
        | NoSuficienteTipoPubliEnPaquete e) {
      e.printStackTrace();
      fail(e.getMessage());
    }

    try {
      assertEquals(1, controlador.listarOfertasLaboralesPorKeyword(keyword).size());
    } catch (NoExisteKeyword e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void listarPostulacionesDeOfertaNoEsVacio() {
    ControladorOferta controlador = new ControladorOferta();

    ControladorUsuario controladorUsuario = new ControladorUsuario();

    try {
      controladorUsuario.alta(postulante, password1);
      controlador.insertarTipoPublicacion(tipoPublicacion1);
    } catch (YaExisteUsuario | YaExisteTipoPublicacion e) {
      e.printStackTrace();
      fail(e.getMessage());

    }

    try {
      controlador.insertarDatosOferta(oferta1.getNombre(), oferta1.getDescOL(),
          oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(), oferta1.getDepartamento(),
          oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(), nombreEmpresa,
          StatusOfertaLaboral.Confirmada, "", null);
      controlador.postulacionOfertaLaboral(nombreOferta, postulante.getNickname(), curriculum,
          motivacion, LocalDate.of(2023, 9, 1));
      assertFalse(controlador.listarPostulaciones(oferta1.getNombre()).isEmpty());
    } catch (NoExisteOferta | YaExisteOferta | NoExisteTipoPublicacion | NoExisteKeyword
        | NoExisteEmpresa | RemuneracionNegativa | NoExistePostulante | YaExistePostulacion
        | OfertaLaboralNoVigente | NoExistePaquete | NoSuficienteTipoPubliEnPaquete e) {
      fail(e.getMessage());
      e.printStackTrace();
    }
  }

  @Test
  void cambiarEstadoOfertaAConfirmada() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
      controlador.insertarDatosOferta(oferta1.getNombre(), oferta1.getDescOL(),
          oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(), oferta1.getDepartamento(),
          oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(), nombreEmpresa,
          StatusOfertaLaboral.Ingresada, "", null);
      controlador.cambiarEstadoOferta(oferta1.getNombre(), "cualquier cosa que no sea rechazada");

    } catch (NoExisteOferta | YaExisteOferta | NoExisteTipoPublicacion | NoExisteKeyword
        | NoExisteEmpresa | RemuneracionNegativa | YaExisteTipoPublicacion | NoExistePaquete
        | NoSuficienteTipoPubliEnPaquete e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    try {
      DataOfertaLaboral ofertaCambiada = controlador.encontrarOfertaLaboral(oferta1.getNombre());
      assertEquals(StatusOfertaLaboral.Confirmada, ofertaCambiada.getStatus());
    } catch (NoExisteOferta e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

  }

  @Test
  void cambiarEstadoOfertaARechazada() {
    ControladorOferta controlador = new ControladorOferta();

    try {
      controlador.insertarTipoPublicacion(tipoPublicacion1);
      controlador.insertarDatosOferta(oferta1.getNombre(), oferta1.getDescOL(),
          oferta1.getHorario(), oferta1.getRemu(), oferta1.getCiudad(), oferta1.getDepartamento(),
          oferta1.getFecAlta(), keywords1, tipoPublicacion1.getNombre(), nombreEmpresa,
          StatusOfertaLaboral.Ingresada, "", null);
      controlador.cambiarEstadoOferta(oferta1.getNombre(), "Rechazada");

    } catch (NoExisteOferta | YaExisteOferta | NoExisteTipoPublicacion | NoExisteKeyword
        | NoExisteEmpresa | RemuneracionNegativa | YaExisteTipoPublicacion | NoExistePaquete
        | NoSuficienteTipoPubliEnPaquete e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

    try {
      DataOfertaLaboral ofertaCambiada = controlador.encontrarOfertaLaboral(oferta1.getNombre());
      assertEquals(StatusOfertaLaboral.Rechazada, ofertaCambiada.getStatus());
    } catch (NoExisteOferta e) {
      fail(e.getMessage());
      e.printStackTrace();
    }

  }
}
