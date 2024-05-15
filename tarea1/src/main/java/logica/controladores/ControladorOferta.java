package main.java.logica.controladores;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import main.java.logica.clasesbasicas.Empresa;
import main.java.logica.clasesbasicas.Keyword;
import main.java.logica.clasesbasicas.OfertaLaboral;
import main.java.logica.clasesbasicas.Paquete;
import main.java.logica.clasesbasicas.Postulacion;
import main.java.logica.clasesbasicas.Postulante;
import main.java.logica.clasesbasicas.TipoPublicacion;
import main.java.logica.datatypes.DataOfertaLaboral;
import main.java.logica.datatypes.DataPaquete;
import main.java.logica.datatypes.DataPostulacion;
import main.java.logica.datatypes.DataTipoPublicacion;
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
import main.java.logica.interfaces.IOferta;
import main.java.logica.manejadores.ManejadorO;
import main.java.logica.manejadores.ManejadorP;
import main.java.logica.manejadores.ManejadorUsuarios;

public class ControladorOferta implements IOferta {
  public ControladorOferta() {
  }

  @Override
  public void insertarDatosOferta(String nombre, String desc, String horario, float remu,
      String ciudad, String departamento, LocalDate fechaAlta, Set<String> ofertaKeywords,
      String tipo, String nickEmpresa, StatusOfertaLaboral status, String img, String nomPaq)
      throws YaExisteOferta, NoExisteTipoPublicacion, NoExisteKeyword, NoExisteEmpresa,
      RemuneracionNegativa, NoExistePaquete, NoSuficienteTipoPubliEnPaquete {

    ManejadorO manejadorOferta = ManejadorO.getInstancia();
    ManejadorUsuarios manejaforUsuarios = ManejadorUsuarios.getInstancia();
    ManejadorP manejadorPaquete = ManejadorP.getInstancia();

    boolean existeOf = manejadorOferta.existeOferta(nombre);

    boolean existeTipo = manejadorOferta.existeTP(tipo);

    boolean existeEmpresa = manejaforUsuarios.existePorNick(nickEmpresa);
    
    boolean existePaquete = manejadorPaquete.existePaquete(nomPaq);

    Iterator<String> kIt = ofertaKeywords.iterator();

    while (kIt.hasNext()) {
      String keywordOferta = kIt.next();
      Iterator<Keyword> keywords = manejadorOferta.listarKeywords().iterator();

      boolean encontrada = false;

      while (keywords.hasNext()) {
        encontrada = keywordOferta.equals(keywords.next().getNombreKeyword()) || encontrada;
      }

      if (!encontrada) {
        throw new NoExisteKeyword("No existe la keyword `" + keywordOferta + "`");
      }
    }

    if (existeOf) {
      throw new YaExisteOferta("Ya existe una oferta laboral con nombre `" + nombre + "`");
    } else if (!existeTipo) {
      throw new NoExisteTipoPublicacion("No existe tipo de publicacion `" + tipo + "`");
    } else if (remu < 0) {
      throw new RemuneracionNegativa("La remuneración no puede ser negativa");
    } else if (!existeEmpresa) {
      throw new NoExisteEmpresa("No existe tal empresa con nick `" + nickEmpresa + "`");
    }else {
    
      TipoPublicacion tipoPublicacion = manejadorOferta.obtenerTP(tipo);
      Empresa empresa = manejaforUsuarios.encontrarEmpresa(nickEmpresa);
      if (!existePaquete) {
    	  OfertaLaboral oferta = new OfertaLaboral(nombre, desc, remu, horario, ciudad, departamento,
    	          fechaAlta, ofertaKeywords, tipoPublicacion, status, img, empresa, null);
    	  Iterator<String> keywordNames = ofertaKeywords.iterator();

	      while (keywordNames.hasNext()) {
	        Keyword keyword = manejadorOferta.encontrarKeyword(keywordNames.next());
	        keyword.ofertasConKeyword().add(oferta);
	      }

	      manejadorOferta.agregarOferta(oferta);
	      empresa.agregarOferta(oferta);
      }else { //Acá tengo que verificar que siga teniendo cantidad tipoPubli disponibles
    	  Paquete paqueteSel;
    	  if (manejaforUsuarios.restantesTipoPubliPaqueteEmp(tipo, nomPaq, nickEmpresa)>0) {
    		  try {
    				paqueteSel = manejadorPaquete.encontrarPaquete(nomPaq);
    				OfertaLaboral oferta = new OfertaLaboral(nombre, desc, remu, horario, ciudad, departamento,
    		    	          fechaAlta, ofertaKeywords, tipoPublicacion, status, img, empresa, paqueteSel);
    				Iterator<String> keywordNames = ofertaKeywords.iterator();

    			      while (keywordNames.hasNext()) {
    			        Keyword keyword = manejadorOferta.encontrarKeyword(keywordNames.next());
    			        keyword.ofertasConKeyword().add(oferta);
    			      }

    			      manejadorOferta.agregarOferta(oferta);
    			      empresa.agregarOferta(oferta);
    			} catch (NoExistePaquete e) { //No debería llegar a acá
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	  }else {
    		  throw new NoSuficienteTipoPubliEnPaquete("Has agotado la cantidad de Tipos de publicacion: " +tipo +" del paquete: "+nomPaq);
    	  }
      }
 
    }
  }

  public DataTipoPublicacion encontrarTP(String nombre) throws NoExisteTipoPublicacion {
    ManejadorO manejaforOferta = ManejadorO.getInstancia();
    DataTipoPublicacion dtp = (manejaforOferta.obtenerTP(nombre)).data();
    return dtp;
  }

  /*
   * Me parece que listarTipos cuando no hay tipos deberia devlover el set vacio,
   * no tirar una excepcion. //public Set<String> listarTipos() throws
   * NoHayParaListarTipos {
   */
  public Set<DataTipoPublicacion> listarTipos() {
    ManejadorO manejaforOferta = ManejadorO.getInstancia();
    Set<DataTipoPublicacion> resu = new HashSet<DataTipoPublicacion>();

    Iterator<TipoPublicacion> tipos = manejaforOferta.listarTipos().iterator();

    while (tipos.hasNext()) {
      resu.add(tipos.next().data());
    }

    return resu;
  }

  public void nomKeyword(String nombre) throws YaExisteKeyword {
    ManejadorO manejaforOferta = ManejadorO.getInstancia();
    boolean existeK = manejaforOferta.existeKeyword(nombre);
    if (!existeK) {
      Keyword keyword = new Keyword(nombre);
      manejaforOferta.agregarKeyword(keyword);
    } else {
      throw new YaExisteKeyword("Ya existe la keyword `" + nombre + "`");
    }
  }

  // public Set<String> listarPaquetes() throws NoHayParaListarPaquetes {
  public Set<DataPaquete> listarPaquetes() {
    ManejadorP manejadorPaquete = ManejadorP.getInstancia();
    Iterator<Paquete> listP = manejadorPaquete.listarPaquetes().iterator();

    Set<DataPaquete> resu = new HashSet<DataPaquete>();

    while (listP.hasNext()) {
      resu.add(listP.next().getDataPaquete());
    }

    return resu;
  }

  public void agregarTipo(String nomPaquete, String tipoPaquete, int cant)
      throws YaExisteTipoEnPaquete, NoExisteTipoPaquete, NoExistePaquete, NoExisteTipoPublicacion {
    ManejadorP manejadorPaquete = ManejadorP.getInstancia();
    Paquete paquete = manejadorPaquete.encontrarPaquete(nomPaquete);

    if (paquete == null) {
      throw new NoExisteTipoPaquete("No existe el tipo de paquete `" + nomPaquete + "`");
    } else if (paquete.existeTP(tipoPaquete)) {
      throw new YaExisteTipoEnPaquete("Ya existe el tipo en el paquete");
    } else {
      manejadorPaquete.agregarTipo(cant, tipoPaquete, nomPaquete);
    }
  }

  public void postulacionOfertaLaboral(String nomOf, String nick, String curriculum,
      String motivacion, LocalDate fecha)
      throws NoExisteOferta, NoExistePostulante, YaExistePostulacion, OfertaLaboralNoVigente {
    ManejadorO manejaforOferta = ManejadorO.getInstancia();
    OfertaLaboral oferta = manejaforOferta.encontrarOferta(nomOf);
    ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
    Postulante paquete = manejadorUsuarios.encontrarPostulante(nick);

    if (oferta.getFecAlta().isAfter(fecha)) {
      throw new OfertaLaboralNoVigente(
          "La oferta `" + oferta.getNombre() + "` todavia no esta de alta");
    }

    if (fecha.compareTo(oferta.getFecAlta()) > oferta.getTipo().getDuracion()) {
      throw new OfertaLaboralNoVigente("La oferta `" + oferta.getNombre() + "` ya no esta vigente");
    }
    
    if (oferta.getStatus() != StatusOfertaLaboral.Confirmada) {
      throw new OfertaLaboralNoVigente("La oferta `" + oferta.getNombre() + "` no fue confirmada por nuestro equipo de moderacion");
    }

    Iterator<Postulacion> iter = paquete.getPostulaciones().iterator();

    while (iter.hasNext()) {
      if (iter.next().getO().getNombre().equals(nomOf)) {
        throw new YaExistePostulacion(
            "El postulante `" + nick + "` ya esta postulado a `" + nomOf + "`");
      }
    }

    Postulacion post = new Postulacion(fecha, curriculum, motivacion, paquete, oferta);
    oferta.agregarPostulacionOf(post);
    paquete.agregarPostulacionP(post);
  }

  public void insertarDatosPaquete(String nombre, String desc, int duracion, float descuento,
      LocalDate fecha, String imagen) throws YaExistePaquete {
    ManejadorP manejadorPaquete = ManejadorP.getInstancia();
    Paquete paquete = new Paquete(nombre, desc, duracion, descuento, fecha, imagen);
    manejadorPaquete.agregarPaquete(paquete);
  }

  @Override
  public void insertarTipoPublicacion(DataTipoPublicacion data) throws YaExisteTipoPublicacion {
    ManejadorO manejaforOferta = ManejadorO.getInstancia();

    manejaforOferta.agregarPublicacion(new TipoPublicacion(data));
  }

  @Override
  public Set<String> listarPostulantesDeOferta(String nombreOferta) throws NoExisteOferta {
    ManejadorO manejaforOferta = ManejadorO.getInstancia();

    OfertaLaboral oferta = manejaforOferta.encontrarOferta(nombreOferta);

    Set<String> postulantes = new HashSet<String>();

    Iterator<Postulacion> postulaciones = oferta.getPostu().iterator();

    while (postulaciones.hasNext()) {
      Postulacion paquete = postulaciones.next();
      postulantes.add(paquete.getNickUsuario());
    }

    return postulantes;
  }

  @Override
  public Set<String> listarKeywords() {
    ManejadorO manejaforOferta = ManejadorO.getInstancia();
    Set<String> resu = new HashSet<String>();

    Iterator<Keyword> tipos = manejaforOferta.listarKeywords().iterator();

    while (tipos.hasNext()) {
      resu.add(tipos.next().getNombreKeyword());
    }

    return resu;
  }

  @Override
  public Set<DataOfertaLaboral> listarOfertasLaborales() {
    ManejadorO manejador = ManejadorO.getInstancia();

    Iterator<OfertaLaboral> ofertas = manejador.listarOfertas().iterator();

    Set<DataOfertaLaboral> resultado = new HashSet<DataOfertaLaboral>();

    while (ofertas.hasNext()) {
      OfertaLaboral oferta = ofertas.next();
      if (oferta.getStatus() == StatusOfertaLaboral.Confirmada) {
        resultado.add(oferta.data());
      }
    }

    return resultado;
  }

  @Override
  public Set<DataOfertaLaboral> listarOfertasLaboralesPorKeyword(String keywordName)
      throws NoExisteKeyword {
    ManejadorO manejador = ManejadorO.getInstancia();

    Keyword keyword = manejador.encontrarKeyword(keywordName);

    Iterator<OfertaLaboral> ofertas = keyword.ofertasConKeyword().iterator();

    Set<DataOfertaLaboral> resultado = new HashSet<DataOfertaLaboral>();

    while (ofertas.hasNext()) {
      OfertaLaboral oferta = ofertas.next();
      if (oferta.getStatus() == StatusOfertaLaboral.Confirmada) {
        resultado.add(oferta.data());
      }
    }

    return resultado;
  }

  public void cambiarEstadoOferta(String nombreOferta, String status) throws NoExisteOferta {
    ManejadorO manejadorO = ManejadorO.getInstancia();
    OfertaLaboral oflab = manejadorO.encontrarOferta(nombreOferta);
    if (status != null && status.equals("Rechazada")) {
      oflab.setStatus(StatusOfertaLaboral.Rechazada);
    } else {
      oflab.setStatus(StatusOfertaLaboral.Confirmada);
    }

  }

  public Set<DataPostulacion> listarPostulaciones(String nombreOferta) throws NoExisteOferta {
    ManejadorO manejaforOferta = ManejadorO.getInstancia();

    OfertaLaboral oferta = manejaforOferta.encontrarOferta(nombreOferta);

    Set<DataPostulacion> dtpostulaciones = new HashSet<DataPostulacion>();

    Iterator<Postulacion> postulaciones = oferta.getPostu().iterator();

    while (postulaciones.hasNext()) {
      Postulacion postu = postulaciones.next();
      dtpostulaciones.add(postu.getDataPostulacion());
    }

    return dtpostulaciones;
  }

  @Override
  public DataOfertaLaboral encontrarOfertaLaboral(String nombreOferta) throws NoExisteOferta {
    ManejadorO manejador = ManejadorO.getInstancia();

    return manejador.encontrarOferta(nombreOferta).data();
  }
  
  public Paquete encontrarPaquete(String nombre) throws NoExistePaquete {
	  ManejadorP manejadorP = ManejadorP.getInstancia();
	  return manejadorP.encontrarPaquete(nombre);
  }
  
  public DataPaquete encontrarDataPaquete(String nombre) throws NoExistePaquete {
	  ManejadorP manejadorP = ManejadorP.getInstancia();
	  return manejadorP.encontrarPaquete(nombre).getDataPaquete();
  }
  
  public Set<DataPaquete> listarPaquetesNoComprados(){
	  ManejadorP manejadorP = ManejadorP.getInstancia();
	  return manejadorP.listarPaquetesNoComprados();
  }

}
