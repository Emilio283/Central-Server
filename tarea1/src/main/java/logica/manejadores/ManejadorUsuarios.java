package main.java.logica.manejadores;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import main.java.logica.clasesbasicas.Empresa;
import main.java.logica.clasesbasicas.Paquete;
import main.java.logica.clasesbasicas.Postulante;
import main.java.logica.clasesbasicas.Usuario;
import main.java.logica.datatypes.DataTipoPublicacionPaquete;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.excepciones.NoExistePostulante;
import main.java.logica.excepciones.NoExisteUsuario;
import main.java.logica.excepciones.YaExisteUsuario;

public class ManejadorUsuarios {
  private Map<String, Usuario> usuarios;
  private Map<String, Postulante> postulantes;
  private Map<String, Empresa> empresas;
  private static ManejadorUsuarios instancia = null;

  public static ManejadorUsuarios getInstancia() {
    if (instancia == null)
      instancia = new ManejadorUsuarios();
    return instancia;
  }

  private ManejadorUsuarios() {
    this.usuarios = new HashMap<String, Usuario>();
    this.postulantes = new HashMap<String, Postulante>();
    this.empresas = new HashMap<String, Empresa>();
  };

  public void add(Postulante postulante) throws YaExisteUsuario {
    String nick = postulante.getNick();

    if (usuarios.get(nick) != null) {
      throw new YaExisteUsuario("Ya existe usuario con nick `" + nick + "`");
    }

    usuarios.put(nick, postulante);
    postulantes.put(nick, postulante);
  }

  public void add(Empresa empresa) throws YaExisteUsuario {
    String nick = empresa.getNick();

    if (usuarios.get(nick) != null) {
      throw new YaExisteUsuario("Ya existe usuario con nick `" + nick + "`");
    }

    usuarios.put(nick, empresa);
    empresas.put(nick, empresa);
  }

  public Postulante encontrarPostulante(String nick) throws NoExistePostulante {
    Postulante postulante = postulantes.get(nick);

    if (postulante == null) {
      throw new NoExistePostulante("No existe postulante con nick `" + nick + "`");
    }

    return postulante;
  }

  public Empresa encontrarEmpresa(String nick) throws NoExisteEmpresa {
    Empresa empresa = empresas.get(nick);

    if (empresa == null) {
      throw new NoExisteEmpresa("No existe empresa con nick `" + nick + "`");
    }

    return empresa;
  }

  public Usuario encontrarUsuario(String nick) throws NoExisteUsuario {
    Usuario usuario = usuarios.get(nick);

    if (usuario == null) {
      throw new NoExisteUsuario("No existe usuario con nick `" + nick + "`");
    }

    return usuario;
  }

  public Set<Empresa> listarEmpresas() {
    return new HashSet<Empresa>(empresas.values());
  }

  public boolean existePorEmail(String email) {
    Iterator<Usuario> iter = usuarios.values().iterator();

    while (iter.hasNext()) {
      Usuario usuario = iter.next();
      if (email.equals(usuario.getEmail())) {
        return true;
      }
    }

    return false;
  }

  public boolean existePorNick(String nick) {
    return usuarios.get(nick) != null;
  }

  public void clear() {
    usuarios.clear();
    postulantes.clear();
    empresas.clear();
  }

  public Set<Usuario> listarUsuarios() {
    return new HashSet<Usuario>(usuarios.values());
  }

  public Set<Postulante> listarPostulantes() {
    return new HashSet<Postulante>(postulantes.values());
  }
  
  public int restantesTipoPubliPaqueteEmp(String nomTipoPubli, String nomPaquete, String nomEmpresa) throws NoExisteEmpresa, NoExistePaquete {
	  Empresa emp = encontrarEmpresa(nomEmpresa);
	  ManejadorP manejadorP = ManejadorP.getInstancia();
	  Paquete paq = manejadorP.encontrarPaquete(nomPaquete);
	  if (paq.existeTP(nomTipoPubli)) {
		  DataTipoPublicacionPaquete dtpp =  paq.getTipoPubliPaquete(nomTipoPubli);
		  int max = dtpp.getCant();
		  int usos = emp.usadosTipoPubliPaquete(nomPaquete, nomTipoPubli);
		  
		  return max-usos;
	  }else {
		  return 0;
	  }
	  
  }

  
  
  
  
}
