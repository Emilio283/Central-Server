package main.java.logica.interfaces;

import java.time.LocalDate;
import java.util.Set;

import main.java.logica.clasesbasicas.Paquete;
import main.java.logica.datatypes.DataEmpresa;
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

public abstract interface IOferta {

  abstract void nomKeyword(String nombre) throws YaExisteKeyword;

  abstract void agregarTipo(String nomPaquete, String tipoPaquete, int cant)
      throws YaExisteTipoEnPaquete, NoExisteTipoPaquete, NoExistePaquete, NoExisteTipoPublicacion;

  abstract Set<DataTipoPublicacion> listarTipos();

  abstract Set<DataPaquete> listarPaquetes();

  abstract void insertarDatosOferta(String nombre, String desc, String horario, float remuneracion,
      String ciudad, String departamento, LocalDate fechAlta, Set<String> keywords, String tipo,
      String nickEmpresa, StatusOfertaLaboral status, String img, String nomPaq) throws YaExisteOferta,
      NoExisteTipoPublicacion, NoExisteKeyword, NoExisteEmpresa, RemuneracionNegativa, NoExistePaquete, NoSuficienteTipoPubliEnPaquete;
  
  default void insertarOfertaLaboral(DataOfertaLaboral dol, DataEmpresa empresa)
	      throws YaExisteOferta, NoExisteTipoPublicacion, NoExisteKeyword, NoExisteEmpresa,
	      RemuneracionNegativa, NoExistePaquete, NoSuficienteTipoPubliEnPaquete {
	    insertarDatosOferta(dol.getNombre(), dol.getDescOL(), dol.getHorario(), dol.getRemu(),
	        dol.getCiudad(), dol.getDepartamento(), dol.getFecAlta(), dol.getKeyw(),
	        dol.getTipo().getNombre(), empresa.getNickname(), dol.getStatus(), dol.getImg(), dol.getPaquetePago());
  }

  abstract void insertarDatosPaquete(String nombre, String desc, int duracion, float descuento,
      LocalDate fecha, String imagen) throws YaExistePaquete;

  abstract void postulacionOfertaLaboral(String nombreOferta, String nick, String curriculum,
      String motivacion, LocalDate fecha)
      throws NoExisteOferta, NoExistePostulante, YaExistePostulacion, OfertaLaboralNoVigente;

  abstract void insertarTipoPublicacion(DataTipoPublicacion data) throws YaExisteTipoPublicacion;

  abstract Set<String> listarPostulantesDeOferta(String nombreOferta) throws NoExisteOferta;

  abstract Set<String> listarKeywords();
  
  abstract Set<DataOfertaLaboral> listarOfertasLaborales();
  
  abstract Set<DataOfertaLaboral> listarOfertasLaboralesPorKeyword(String keyword) throws NoExisteKeyword;
  
  abstract void cambiarEstadoOferta(String nombreOferta, String status) throws NoExisteOferta;
  
  abstract Set<DataPostulacion> listarPostulaciones(String nombreOferta) throws NoExisteOferta;

  abstract DataOfertaLaboral encontrarOfertaLaboral(String nombreOferta) throws NoExisteOferta;
  
  abstract Paquete encontrarPaquete(String nombre) throws NoExistePaquete;
  
  abstract Set<DataPaquete> listarPaquetesNoComprados();
  
  abstract DataPaquete encontrarDataPaquete(String nombre) throws NoExistePaquete;
  
  
}
