package main.java.logica.manejadores;

import java.util.Set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

import main.java.logica.clasesbasicas.Paquete;
import main.java.logica.clasesbasicas.TipoPublicacion;
import main.java.logica.clasesbasicas.TipoPublicacionPaquete;
import main.java.logica.datatypes.DataPaquete;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.excepciones.NoExisteTipoPublicacion;
import main.java.logica.excepciones.YaExistePaquete;

public class ManejadorP {
  private static ManejadorP instancia = null;
  private Map<String, Paquete> paquetes;

  private ManejadorP() {
    paquetes = new HashMap<String, Paquete>();
  }

  public static ManejadorP getInstancia() {
    if (instancia == null) {
      instancia = new ManejadorP();
    }
    return instancia;
  }

  public void clear() {
    paquetes.clear();
  }

  public Set<Paquete> listarPaquetes() {
    return new HashSet<Paquete>(paquetes.values());

  }

  public boolean existePaquete(String nombre) {
    return paquetes.get(nombre) != null;
  }

  public Paquete encontrarPaquete(String nombre) throws NoExistePaquete {
    Paquete paquete = paquetes.get(nombre);

    if (paquete == null) {
      throw new NoExistePaquete("No existe un paquete con nombre `" + nombre + "`");
    }

    return paquete;
  }

  public void agregarTipo(int cant, String nomtp, String nomPaquete)
      throws NoExisteTipoPublicacion {
    // Buscar paquete y tipo publi
    ManejadorO manejadorOferta = ManejadorO.getInstancia();
    if (existePaquete(nomPaquete) && manejadorOferta.existeTP(nomtp)) {
      Paquete paquete = paquetes.get(nomPaquete);
      TipoPublicacion tipoPaquete = manejadorOferta.obtenerTP(nomtp);

      // busco si ya no existe el tipo en el paquete
      if (!paquete.existeTP(nomtp)) {
        TipoPublicacionPaquete tpp = new TipoPublicacionPaquete(paquete, tipoPaquete, cant);
        paquete.agregarTipo(nomtp, tpp);
      }
    }
  }

  public void agregarPaquete(Paquete paquete) throws YaExistePaquete {
    String nom = paquete.getNombre();

    if (paquetes.get(nom) != null) {
      throw new YaExistePaquete("Ya existe paquete con nombre `" + nom + "`");
    }

    paquetes.put(nom, paquete);
  }
  
  public Set<DataPaquete> listarPaquetesNoComprados() {
	    Set<DataPaquete> resu = new HashSet<DataPaquete>();
	    Iterator<Map.Entry<String, Paquete>> iterP = paquetes.entrySet().iterator();
	    while (iterP.hasNext()) {
	    	Map.Entry<String, Paquete> entry = iterP.next(); 
	    	if (!entry.getValue().getComprado()) {
	    		resu.add(entry.getValue().getDataPaquete());
	    	}
	    }
	    return resu;
  }
  
  

}