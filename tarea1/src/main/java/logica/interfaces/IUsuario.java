package main.java.logica.interfaces;

import java.time.LocalDate;

import java.util.Set;

import main.java.logica.clasesbasicas.Empresa;
import main.java.logica.clasesbasicas.Postulacion;
import main.java.logica.clasesbasicas.Postulante;
import main.java.logica.datatypes.DataCompraPaquete;
import main.java.logica.datatypes.DataEmpresa;
import main.java.logica.datatypes.DataOfertaLaboral;
import main.java.logica.datatypes.DataPaquete;
import main.java.logica.datatypes.DataPostulacion;
import main.java.logica.datatypes.DataPostulante;
import main.java.logica.datatypes.DataUsuario;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.excepciones.NoExistePostulante;
import main.java.logica.excepciones.NoExisteUsuario;
import main.java.logica.excepciones.YaExisteUsuario;


public interface IUsuario {
	
	public Set<String> listarEmpresas();
				
	public void agregarPostulacionAPostulante(Postulante postulante, Postulacion postulacion);
	
	public void actualizarPostulante(LocalDate fecNac, String nacionalidad, String nick, String nombre, String apellido, String email, String foto) throws NoExistePostulante;
	
	public void actualizarEmpresa(String desc, String sitioWeb, String nick, String nombre, String apellido, String email, String foto) throws NoExisteEmpresa;

	public Set<DataOfertaLaboral> listarOfertasLaborales(String nickEmpresa, boolean soloConfirmadas) throws NoExisteEmpresa;
	
	public Set<DataOfertaLaboral> listarOfertasLaboralesIngresadas(String nick) throws NoExisteEmpresa; 
		
	void alta(DataEmpresa dataEmpresa, String password) throws YaExisteUsuario;

	void alta(DataPostulante dataPostulante, String password) throws YaExisteUsuario;

	Set<DataUsuario> listarDataUsuarios();
	
	public DataUsuario encontrarUser(String nick) throws NoExisteUsuario;
	
	public Set<DataOfertaLaboral> postulacionesDe(String nick) throws NoExistePostulante;
	
	public Set<DataPostulacion> postulacionesPostu(String nick) throws NoExistePostulante;
	
	public DataUsuario verificarCredenciales(String nick, String password);
	
	abstract Set<DataCompraPaquete> listarPaquetesDeEmpresa(String nick) throws NoExisteEmpresa;
	
	abstract Set<DataPaquete> listarPaquetesDisponiblesDeEmpresa(String nick, String nomTipoPubli) throws NoExisteEmpresa, NoExistePaquete;
	
	public abstract void agregarPaquete(String nomP, String nomE) throws NoExisteEmpresa, NoExistePaquete;
	
	abstract Empresa encontrarEmpresa(String nick) throws NoExisteEmpresa;
	
	abstract boolean empresaYaTienePaquete(String nomEmp, String nomPaq) throws NoExisteEmpresa;

}
