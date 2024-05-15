package main.java.logica.manejadores;

import java.util.HashMap;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import main.java.logica.clasesbasicas.TipoPublicacion;
import main.java.logica.clasesbasicas.OfertaLaboral;
import main.java.logica.excepciones.NoExisteKeyword;
import main.java.logica.excepciones.NoExisteOferta;
import main.java.logica.excepciones.NoExisteTipoPublicacion;
import main.java.logica.excepciones.YaExisteKeyword;
import main.java.logica.excepciones.YaExisteOferta;
import main.java.logica.excepciones.YaExisteTipoPublicacion;
import main.java.logica.clasesbasicas.Keyword;

public class ManejadorO {
	private Map<String, OfertaLaboral> ofertas;
	private Map<String, TipoPublicacion> tPublicaciones;
	private Map<String, Keyword> keywords;
	private static ManejadorO instancia = null;
	
	private ManejadorO() {
		ofertas = new HashMap<String, OfertaLaboral>();
		tPublicaciones = new HashMap<String, TipoPublicacion>();
		keywords = new HashMap<String, Keyword>();
	}
	
	public static ManejadorO getInstancia() {
		if (instancia == null)
			instancia = new ManejadorO();
		return instancia;
	}
	
	public void agregarOferta(OfertaLaboral oferta) throws YaExisteOferta {
		String nom = oferta.getNombreOferta();
		
		if (ofertas.get(nom) != null) {
			throw new YaExisteOferta("Ya existe una oferta laboral con nombre `" + nom + "`");
		}

		ofertas.put(nom, oferta);
	}
	
	public void agregarKeyword(Keyword keyword) throws YaExisteKeyword {
		String nom = keyword.getNombreKeyword();
		
		if (keywords.get(nom) != null) {
			throw new YaExisteKeyword("Ya existe la keyword `" + nom + "`");
		}
		
		keywords.put(nom, keyword);
	}
	
	public void agregarPublicacion(TipoPublicacion tipoPublicacion) throws YaExisteTipoPublicacion {
		String nom = tipoPublicacion.getNombreTP();
		
		if (tPublicaciones.get(nom) != null ) {
			throw new YaExisteTipoPublicacion("Ya existe un tipo de publicacion con nombre `" + nom + "`");
		}
		
		tPublicaciones.put(nom, tipoPublicacion);
	}
	
	public TipoPublicacion obtenerTP(String nom) throws NoExisteTipoPublicacion {
		TipoPublicacion tipoPublicacion = tPublicaciones.get(nom);
		
		if (tipoPublicacion == null) {
			throw new NoExisteTipoPublicacion("No existe el tipo de publicacion `" + nom + "`");
		}
		return tipoPublicacion;
	}
	
	public Set<TipoPublicacion> listarTipos() {
		Set<TipoPublicacion> resu = new HashSet<TipoPublicacion>();
		Iterator<TipoPublicacion> iter = tPublicaciones.values().iterator();
		
		while (iter.hasNext()) {
			resu.add(iter.next());
		}
		return resu;
	}

	public boolean existeOferta(String nombre) {
		return ofertas.get(nombre) != null;
	}

	public boolean existeKeyword(String nombre) {
		return keywords.get(nombre) != null;		
	}
	
	public OfertaLaboral encontrarOferta(String nombre) throws NoExisteOferta {
		OfertaLaboral oferta = ofertas.get(nombre);
		
		if (oferta == null) {
			throw new NoExisteOferta("No existe oferta laboral con nombre `" + nombre + "`");
		}
		
		return oferta;
	}
	
	public boolean existeTP(String nombre) {
		return tPublicaciones.get(nombre) != null;
	}

	public void clear() {
		if (ofertas != null) {
			ofertas.clear();
		}
		if (tPublicaciones != null) {
			tPublicaciones.clear();
		}
		if (keywords != null) {
			keywords.clear();
		}
	}

	public Set<Keyword> listarKeywords() {
		Set<Keyword> resu = new HashSet<Keyword>();
		Iterator<Keyword> iter = keywords.values().iterator();
		
		while (iter.hasNext()) {
			resu.add(iter.next());
		}
		
		return resu;
	}

  public Set<OfertaLaboral> listarOfertas() {
    return new HashSet<OfertaLaboral>(ofertas.values());
  }

  public Keyword encontrarKeyword(String keywordName) throws NoExisteKeyword {
    Keyword res = keywords.get(keywordName);
    
    if (res == null) throw new NoExisteKeyword("No existe la keyword `" + keywordName + "`");
    
    return res;
  }
}
