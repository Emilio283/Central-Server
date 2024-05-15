package main.java.logica.clasesbasicas;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.time.LocalDate;
import java.util.HashMap;

import main.java.logica.datatypes.DataCompraPaquete;
import main.java.logica.datatypes.DataEmpresa;
import main.java.logica.datatypes.DataPaquete;

public class Empresa extends Usuario {

  private String descripcion;
  private String sitioWeb;
  private Map<String, OfertaLaboral> ofertas = new HashMap<String, OfertaLaboral>();
  private Set<CompraPaquete> paquetes = new HashSet<CompraPaquete>();

  public Empresa(String nick, String nombre, String apellido, String email, String descripcion,
      String sitioWeb, String password, String foto) {
    super(nick, nombre, apellido, email, password, foto);
    this.descripcion = descripcion;
    this.sitioWeb = sitioWeb;
  }

  public Empresa(DataEmpresa dataEmpresa, String password) {
    super(dataEmpresa, password);
    this.descripcion = dataEmpresa.getDescripcion();
    this.sitioWeb = dataEmpresa.getSitioWeb();
    this.ofertas = new HashMap<String, OfertaLaboral>();
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setSitioWeb(String sitioWeb) {
    this.sitioWeb = sitioWeb;
  }

  public void agregarOferta(OfertaLaboral oferta) {
    this.ofertas.put(oferta.getNombre(), oferta);
  }
  
  public void agregarPaquete(Paquete paq) {
	  CompraPaquete comp = new CompraPaquete(paq, LocalDate.now());
	  paquetes.add(comp);
  }
  
  public boolean empresaYaTienePaquete(String nomPaq) {
	  boolean encontrado = false;
	  Iterator<CompraPaquete> iterP = paquetes.iterator();
	  while (iterP.hasNext()) {
		  DataPaquete comp = iterP.next().dataCompraPaquete().getDtPaquete();
		  if (comp.getNombre().equals(nomPaq)) {
			  encontrado = true;
		  }
	  }
	  return encontrado;
  }
  
  public Set<DataCompraPaquete> paquetesComprados(){
	  Iterator<CompraPaquete> iterP = paquetes.iterator();
	  Set<DataCompraPaquete> resu = new HashSet<DataCompraPaquete>();
	  while (iterP.hasNext()) {
		  CompraPaquete comp = iterP.next();
		  resu.add(comp.dataCompraPaquete());
		  
	  }
	  
	  return resu;
  }
  
  public Set<DataCompraPaquete> paquetesVigentes(){
	  Iterator<CompraPaquete> iterP = paquetes.iterator();
	  Set<DataCompraPaquete> resu = new HashSet<DataCompraPaquete>();
	  while (iterP.hasNext()) {
		  CompraPaquete comp = iterP.next();
		  if (comp.getFecVencimiento().compareTo(LocalDate.now()) >= 0) {
			  
			  resu.add(comp.dataCompraPaquete());
		  }
	  }
	  
	  return resu;
  }
  
  @Override
  public DataEmpresa data() {
    return new DataEmpresa(getNick(), getNombre(), getApellido(), getEmail(), descripcion,
        sitioWeb, getFoto());
  }

  public void actualizar(DataEmpresa dte) {
    setNick(dte.getNickname());
    setNombre(dte.getNombre());
    setApellido(dte.getApellido());
    setEmail(dte.getEmail());
    setDescripcion(dte.getDescripcion());
    setSitioWeb(dte.getSitioWeb());
  }

  public Set<OfertaLaboral> listarOfertasLaborales() {
    return new HashSet<OfertaLaboral>(ofertas.values());
  }

  public String getSitioWeb() {
    return sitioWeb;
  }
  
  public int usadosTipoPubliPaquete(String nomPaq, String nomTipoPubli) {
	  Iterator<Map.Entry<String, OfertaLaboral>> iter = ofertas.entrySet().iterator();
	  int usos = 0;
	  while (iter.hasNext()) {
		  Map.Entry<String, OfertaLaboral> entry = iter.next();
		  if (entry.getValue().getPaquetePago() != null) {
			  if (entry.getValue().getPaquetePago().getNombre().equals(nomPaq) && entry.getValue().getTipo().getNombreTP().equals(nomTipoPubli)) {
				  usos++;
			  }
		  }
		  
	  }
	  return usos;
  }
}
