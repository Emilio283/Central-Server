package main.java.logica.datatypes;

import java.time.LocalDate;


public class DataCompraPaquete {
	private DataPaquete dtPaquete;
	private LocalDate fecCompra;
	private LocalDate fecVencimiento;
	
	public DataCompraPaquete(DataPaquete paquete, LocalDate fecC, LocalDate fecV) {
		this.dtPaquete=paquete;
		this.fecCompra=fecC;
		this.fecVencimiento=fecV;
	}
	
	public DataPaquete getDtPaquete() {
		return dtPaquete;
	}

	public LocalDate getFecCompra() {
		return fecCompra;
	}

	public LocalDate getFecVencimiento() {
		return fecVencimiento;
	}

}
