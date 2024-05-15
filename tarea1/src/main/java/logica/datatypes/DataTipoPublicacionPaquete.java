package main.java.logica.datatypes;

public class DataTipoPublicacionPaquete {
	private int cant;
	private DataTipoPublicacion dtp;
	//private DataPaquete dp;
	
	public DataTipoPublicacionPaquete(int cant, DataTipoPublicacion dtp){
		this.cant = cant;
		//this.dp = dp;
		this.dtp = dtp;
	};
	
	public int getCant() {
		return cant;
	}

	public DataTipoPublicacion getDtp() {
		return dtp;
	}

	/*public DataPaquete getDp() {
		return dp;
	}
*/
}
