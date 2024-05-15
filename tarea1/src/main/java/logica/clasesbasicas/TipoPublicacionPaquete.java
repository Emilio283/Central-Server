package main.java.logica.clasesbasicas;

import main.java.logica.datatypes.DataTipoPublicacion;
import main.java.logica.datatypes.DataTipoPublicacionPaquete;

public class TipoPublicacionPaquete {
	private TipoPublicacion tipo;
	private int cant;
	
	public TipoPublicacionPaquete(Paquete paquete, TipoPublicacion tipo, int cant) {
		setTp(tipo);
		setCant(cant);
	}

	public void setTp(TipoPublicacion tipo) {
		this.tipo = tipo;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}
	
	public int getCant() {
		return cant;
	}
	
	public DataTipoPublicacionPaquete getDataTipoPublicacionPaquete(){
		//DataPaquete dp = p.getDataPaquete();
		DataTipoPublicacion dtp = tipo.data();
		return new DataTipoPublicacionPaquete(cant, dtp);
	}
}
