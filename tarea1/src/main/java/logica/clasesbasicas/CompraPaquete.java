package main.java.logica.clasesbasicas;

import java.time.LocalDate;

import main.java.logica.datatypes.DataCompraPaquete;
import main.java.logica.datatypes.DataPaquete;

public class CompraPaquete {
	private Paquete paquete;
	private LocalDate fecCompra;
	private LocalDate fecVencimiento;
	
	public CompraPaquete(Paquete paq, LocalDate fecC) {
		this.paquete=paq;
		this.fecCompra=fecC;
		this.fecVencimiento= fecC.plusDays(paquete.getDuracion());
	}
	
	public LocalDate getFecVencimiento() {
		return fecVencimiento;
	}
	
	public DataCompraPaquete dataCompraPaquete() {
		DataPaquete dtPaq = this.paquete.getDataPaquete();
		return new DataCompraPaquete(dtPaq, fecCompra , fecVencimiento);
	}
	
}
