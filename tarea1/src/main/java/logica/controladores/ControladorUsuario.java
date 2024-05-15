package main.java.logica.controladores;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import main.java.logica.clasesbasicas.Empresa;
import main.java.logica.clasesbasicas.OfertaLaboral;
import main.java.logica.clasesbasicas.Paquete;
import main.java.logica.clasesbasicas.Postulacion;
import main.java.logica.clasesbasicas.Postulante;
import main.java.logica.clasesbasicas.Usuario;
import main.java.logica.datatypes.DataCompraPaquete;
import main.java.logica.datatypes.DataEmpresa;
import main.java.logica.datatypes.DataOfertaLaboral;
import main.java.logica.datatypes.DataPaquete;
import main.java.logica.datatypes.DataPostulacion;
import main.java.logica.datatypes.DataPostulante;
import main.java.logica.datatypes.DataTipoPublicacion;
import main.java.logica.datatypes.DataTipoPublicacionPaquete;
import main.java.logica.datatypes.DataUsuario;
import main.java.logica.enums.StatusOfertaLaboral;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.excepciones.NoExistePostulante;
import main.java.logica.excepciones.NoExisteUsuario;
import main.java.logica.excepciones.YaExisteUsuario;
import main.java.logica.interfaces.IUsuario;
import main.java.logica.manejadores.ManejadorUsuarios;
import main.java.logica.manejadores.ManejadorP;

public class ControladorUsuario implements IUsuario {

  public DataUsuario encontrarUser(String nick) throws NoExisteUsuario {
    ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();
    Usuario usuario = manejadorUsuarios.encontrarUsuario(nick);
    return usuario.data();
  }

  public void actualizarPostulante(LocalDate fecNac, String nacionalidad, String nick,
      String nombre, String apellido, String email, String foto) throws NoExistePostulante {
    ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();

    Postulante postulante = manejadorUsuarios.encontrarPostulante(nick);

    if (!email.equals(postulante.getEmail())) {
      throw new NoExistePostulante("Postulante incosisntente");
    }
    
    String fotot = foto;
    
    if (fotot == null) {
      fotot = postulante.getFoto();
    }

    postulante.actualizar(new DataPostulante(nick, nombre, apellido, email, fecNac, nacionalidad, fotot));
  }

  public void actualizarEmpresa(String desc, String sitioWeb, String nick, String nombre,
      String apellido, String email, String foto) throws NoExisteEmpresa {
    ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();

    Empresa empresa = manejadorUsuarios.encontrarEmpresa(nick);

    if (!email.equals(empresa.getEmail())) {
      throw new NoExisteEmpresa("Empresa incosisntente");
    }
    
    String fotot = foto;
    
    if (fotot == null) {
      fotot = empresa.getFoto();
    }

    empresa.actualizar(new DataEmpresa(nick, nombre, apellido, email, desc, sitioWeb, fotot));
  }

  public Set<DataUsuario> listarDataUsuarios() {
    Set<DataUsuario> resu = new HashSet<DataUsuario>();

    Iterator<Usuario> usuarios = ManejadorUsuarios.getInstancia().listarUsuarios().iterator();

    while (usuarios.hasNext()) {
      resu.add(usuarios.next().data());
    }

    return resu;
  }

  /*
   * public Set<DataEmpresa> listarDataEmpresas() { return
   * ManejadorUsuarios.instancia.listarEmpresas(); }
   */

  /*
   * public Set<DataPostulante> listarDataPostulantes() { return
   * ManejadorUsuarios.instancia.listarPostulantes(); }
   */

  public Set<DataOfertaLaboral> postulacionesDe(String nick) throws NoExistePostulante {
    ManejadorUsuarios manejdorUsuarios = ManejadorUsuarios.getInstancia();

    Postulante postulante = manejdorUsuarios.encontrarPostulante(nick);

    Iterator<Postulacion> iter = postulante.getPostulaciones().iterator();

    Set<DataOfertaLaboral> resultado = new HashSet<DataOfertaLaboral>();

    while (iter.hasNext()) {
      resultado.add(iter.next().getO().data());
    }

    return resultado;
  }

  public Set<DataPostulacion> postulacionesPostu(String nick) throws NoExistePostulante {
    ManejadorUsuarios manejdorUsuarios = ManejadorUsuarios.getInstancia();

    Postulante postulante = manejdorUsuarios.encontrarPostulante(nick);

    Iterator<Postulacion> iter = postulante.getPostulaciones().iterator();

    Set<DataPostulacion> resultado = new HashSet<DataPostulacion>();

    while (iter.hasNext()) {
      resultado.add(iter.next().getDataPostulacion());
    }

    return resultado;
  }

  @Override
  public void alta(DataPostulante dataPostulante, String password) throws YaExisteUsuario {
    ManejadorUsuarios manejadorUsuarios = ManejadorUsuarios.getInstancia();

    boolean existePorNick = manejadorUsuarios.existePorNick(dataPostulante.getNickname());
    boolean existePorEmail = manejadorUsuarios.existePorEmail(dataPostulante.getEmail());

    if (existePorNick) {
      throw new YaExisteUsuario(
          "Ya existe usuario con nick `" + dataPostulante.getNickname() + "`");
    } else if (existePorEmail) {
      throw new YaExisteUsuario("Ya existe usuario con email `" + dataPostulante.getEmail() + "`");
    }

    Postulante postulante = new Postulante(dataPostulante, password);

    manejadorUsuarios.add(postulante);
  }

  @Override
  public void alta(DataEmpresa dataEmpresa, String password) throws YaExisteUsuario {
    ManejadorUsuarios manejdorUsuarios = ManejadorUsuarios.getInstancia();

    boolean existePorNick = manejdorUsuarios.existePorNick(dataEmpresa.getNickname());
    boolean existePorEmail = manejdorUsuarios.existePorEmail(dataEmpresa.getEmail());

    if (existePorNick) {
      throw new YaExisteUsuario("Ya existe usuario con nick `" + dataEmpresa.getNickname() + "`");
    } else if (existePorEmail) {
      throw new YaExisteUsuario("Ya existe usuario con email `" + dataEmpresa.getEmail() + "`");
    }

    Empresa empresa = new Empresa(dataEmpresa, password);

    manejdorUsuarios.add(empresa);
  }

  public Set<DataOfertaLaboral> listarOfertasLaborales(String nickEmpresa, boolean soloConfirmadas)
      throws NoExisteEmpresa {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    Empresa empresa = manejador.encontrarEmpresa(nickEmpresa);

    Iterator<OfertaLaboral> ofertas = empresa.listarOfertasLaborales().iterator();

    Set<DataOfertaLaboral> resultado = new HashSet<DataOfertaLaboral>();

    while (ofertas.hasNext()) {
      OfertaLaboral oferta = ofertas.next();
      
      if (!soloConfirmadas || oferta.getStatus() == StatusOfertaLaboral.Confirmada) {
        resultado.add(oferta.data());
      }
    }

    return resultado;
  }

  public Set<DataOfertaLaboral> listarOfertasLaboralesIngresadas(String nick)
      throws NoExisteEmpresa {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    Empresa empresa = manejador.encontrarEmpresa(nick);

    Iterator<OfertaLaboral> itofertas = empresa.listarOfertasLaborales().iterator();

    Set<DataOfertaLaboral> resultado = new HashSet<DataOfertaLaboral>();

    while (itofertas.hasNext()) {
      OfertaLaboral oferta = itofertas.next();
      if (oferta.getStatus().equals(StatusOfertaLaboral.Ingresada)) {
        resultado.add(oferta.data());
      }
    }
    return resultado;
  }

  @Override
  public Set<String> listarEmpresas() {
    Set<String> resu = new HashSet<String>();

    ManejadorUsuarios manejdorUsuarios = ManejadorUsuarios.getInstancia();
    Iterator<Empresa> empresas = manejdorUsuarios.listarEmpresas().iterator();

    while (empresas.hasNext()) {
      resu.add(empresas.next().getNick());
    }

    return resu;
  }

  @Override
  public void agregarPostulacionAPostulante(Postulante postulante, Postulacion postu) {
    postulante.agregarPostulacionP(postu);
  }

  @Override
  public DataUsuario verificarCredenciales(String nick, String password) {
    ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();

    try {
      Usuario usuario = manejador.encontrarUsuario(nick);

      if (usuario.isPassword(password)) {
        return usuario.data();
      } else {
        return null;
      }
    } catch (NoExisteUsuario e) {
      return null;
    }
  }
  
  public Set<DataCompraPaquete> listarPaquetesDeEmpresa(String nick) throws NoExisteEmpresa{
	  ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();
	  Empresa emp = manejador.encontrarEmpresa(nick);
	  return emp.paquetesVigentes();
  }
  
  public void agregarPaquete(String nomP, String nomE) throws NoExisteEmpresa, NoExistePaquete {
	  ManejadorUsuarios manejadorU = ManejadorUsuarios.getInstancia();
	  ManejadorP manejadorP = ManejadorP.getInstancia();
	  
	  Empresa emp = manejadorU.encontrarEmpresa(nomE);
	  Paquete paq = manejadorP.encontrarPaquete(nomP);
	  
	  
	  paq.setComprado(true);
	  emp.agregarPaquete(paq);
	  
  }
  
  public Set<DataPaquete> listarPaquetesDisponiblesDeEmpresa(String nick, String nomTipoPubli) throws NoExisteEmpresa, NoExistePaquete{
	  Set<DataPaquete> resu = new HashSet<DataPaquete>();
	  ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();
	  Empresa emp = manejador.encontrarEmpresa(nick);
	  Set<DataCompraPaquete> paquetesv = emp.paquetesVigentes();
	  Iterator<DataCompraPaquete> itercompaq = paquetesv.iterator();
	  while (itercompaq.hasNext()) {
		  DataPaquete dtp = itercompaq.next().getDtPaquete();
		  Set<DataTipoPublicacionPaquete> listaTipoPubli = dtp.getTipoPublicaciones();
		  Iterator<DataTipoPublicacionPaquete> iterTipoPub = listaTipoPubli.iterator();
		  while (iterTipoPub.hasNext()) {
			  DataTipoPublicacionPaquete dtpp = iterTipoPub.next();
			  DataTipoPublicacion dttp = dtpp.getDtp();
			  if (dttp.getNombre().equals(nomTipoPubli) && manejador.restantesTipoPubliPaqueteEmp(dttp.getNombre(), dtp.getNombre(), nick) > 0){
				  resu.add(dtp);
			  }
		  }
	  }
	  
	  return resu;
  }
  
  public Empresa encontrarEmpresa(String nick) throws NoExisteEmpresa{
	  ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();
	  return manejador.encontrarEmpresa(nick);
  }
  
  public boolean empresaYaTienePaquete(String nomEmp, String nomPaq) throws NoExisteEmpresa {
	  ManejadorUsuarios manejador = ManejadorUsuarios.getInstancia();
	  Empresa emp = manejador.encontrarEmpresa(nomEmp);
	  return emp.empresaYaTienePaquete(nomPaq);
  }
  
  
}
