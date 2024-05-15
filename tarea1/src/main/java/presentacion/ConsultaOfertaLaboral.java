package main.java.presentacion;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import main.java.presentacion.reutilizables.VerOfertaLaboral;

@SuppressWarnings("serial")
public class ConsultaOfertaLaboral extends JInternalFrame {

	private VerOfertaLaboral verOfertaLaboral = new VerOfertaLaboral(this);
	
	/**
	 * Create the frame.
	 */
	public ConsultaOfertaLaboral() {		
		setBounds(100, 100, 450, 500);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setClosable(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Consulta de Oferta Laboral");

		getContentPane().add(verOfertaLaboral);
	}

	public void actualizar() {
		verOfertaLaboral.actualizar();
	}
}
