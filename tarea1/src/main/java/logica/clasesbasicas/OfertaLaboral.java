package main.java.logica.clasesbasicas;

import java.util.Set;

import main.java.logica.datatypes.DataOfertaLaboral;
import main.java.logica.enums.StatusOfertaLaboral;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;

public class OfertaLaboral {

  private String nombre;
  private String descOL;
  private float remu;
  private String horario;
  private String ciudad;
  private String departamento;
  private LocalDate fecAlta;
  private Set<String> keyw;
  private Set<Postulacion> postu;
  private TipoPublicacion tipo;
  private StatusOfertaLaboral status;
  private String img;
  private Empresa publicador;
  private Paquete paquetePago;

  public OfertaLaboral(String nombre, String descOL, float remu, String horario, String ciudad,
      String departamento, LocalDate fecAlta, Set<String> keyw, TipoPublicacion tipo,
      StatusOfertaLaboral stat, String imagen, Empresa publicador, Paquete paq) {
    setNombre(nombre);
    setDescOL(descOL);
    setRemu(remu);
    setHorario(horario);
    setCiudad(ciudad);
    setDepartamento(departamento);
    setFecAlta(fecAlta);
    setKeyw(keyw);
    postu = new HashSet<Postulacion>();
    setTipo(tipo);
    setImg(imagen);
    setStatus(stat);
    this.publicador = publicador;
    this.paquetePago = paq;
  }

  public String getNombre() {
    return nombre;
  }

  public LocalDate getFecAlta() {
    return fecAlta;
  }

  public Set<Postulacion> getPostu() {
    return postu;
  }

  public StatusOfertaLaboral getStatus() {
    return status;
  }

  public void setStatus(StatusOfertaLaboral status) {
    this.status = status;
  }


  public void setImg(String img) {
    this.img = img;
  }

  private void setTipo(TipoPublicacion tipo) {
    this.tipo = tipo;
  }

  public TipoPublicacion getTipo() {
    return tipo;
  }

  public String getNombreOferta() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDescOL(String descOL) {
    this.descOL = descOL;
  }

  public void setRemu(float remu) {
    this.remu = remu;
  }

  public void setHorario(String horario) {
    this.horario = horario;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public void setFecAlta(LocalDate fecAlta) {
    this.fecAlta = fecAlta;
  }

  public void setKeyw(Set<String> keyw) {
    this.keyw = keyw;
  }
  
  public Empresa getPublicador() {
    return publicador;
  }
  
  public Paquete getPaquetePago() {
	  return paquetePago;
  }

  public Set<String> listarPostulantesO() {
    Set<String> resu = new HashSet<String>();
    for (Iterator<Postulacion> i = postu.iterator(); i.hasNext();) {
      Postulacion postulacion = (Postulacion) i.next();
      resu.add(postulacion.getNickUsuario());
    }
    return resu;
  }

  public void nuevaPost(Postulacion postulacion) {
    postu.add(postulacion);
  }

  public void agregarPostulacionOf(Postulacion post) {
    postu.add(post);
  }

  public DataOfertaLaboral data() {
	  if (paquetePago == null) {
		  return new DataOfertaLaboral(nombre, descOL, remu, horario, ciudad, departamento, fecAlta,
			        tipo.data(), new HashSet<String>(keyw), status, img, publicador.data(), null);
	  }else {
		  return new DataOfertaLaboral(nombre, descOL, remu, horario, ciudad, departamento, fecAlta,
			        tipo.data(), new HashSet<String>(keyw), status, img, publicador.data(), paquetePago.getNombre());
	  }
    
  }
}
