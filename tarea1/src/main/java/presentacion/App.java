package main.java.presentacion;

import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import main.java.logica.excepciones.NoExistePaquete;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class App extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final Action elegirAltaDeKeyword = new ElegirAltaDeKeyword();
	private final AltaDeKeyword altaDeKeyword = new AltaDeKeyword();

	private final Action elegirPostulacionAOfertaLaboral = new ElegirPostulacionAOfertaLaboral();
	private final PostulacionAOfertaLaboral postulacionAOfertaLaboral = new PostulacionAOfertaLaboral();
	
	private final Action elegirAltaUsuario = new ElegirAltaUsuario();
	private final AltaUsuario altaUsuario = new AltaUsuario();
	
	private final Action elegirAltaTipoPublicacionOferta = new ElegirAltaTipoPublicacionOferta();
	private final AltaTipoPublicacionOferta altaTipoPublicacionOferta = new AltaTipoPublicacionOferta();
	
	private final Action elegirConsultaUsuario = new ElegirConsultaUsuario();
	private final ConsultaUsuario consultaUsuario = new ConsultaUsuario();
	
	private final Action elegirModificarUsuario = new ElegirModificarUsuario();
	private final ModificarUsuario modificarUsuario = new ModificarUsuario();
	
	private final Action elegirAltaOfertaLaboral = new ElegirAltaOfertaLaboral();
	private final AltaOfertaLaboral altaOfertaLaboral = new AltaOfertaLaboral();
	
	private final Action elegirConsultaOfertaLaboral = new ElegirConsultaOfertaLaboral();
	private final ConsultaOfertaLaboral consultaOfertaLaboral = new ConsultaOfertaLaboral();
	
	private final Action elegirCrearPaquete = new ElegirCrearPaquete();
	private final CrearPaquete crearPaquete = new CrearPaquete();
	
	private final Action elegirAgregarTipoAPaquete = new ElegirAgregarTipoAPaquete();
	private final AgregarTipoAPaquete agregarTipoAPaquete = new AgregarTipoAPaquete();
	
	private final Action elegirConsultaPaquete = new ElegirConsultaPaquete();
	private final ConsultaPaquete consultaPaquete = new ConsultaPaquete();
	
	private final Action elegirModificarEstadoOferta = new ElegirModificarEstadoOferta();
	private final StatusOferta statusOferta = new StatusOferta();
	
	private final Action elegirCompraPaquete = new ElegirCompraPaquete();
	private final CompraPaquete compraPaquete = new CompraPaquete();

	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 250, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu Menu_inicio = new JMenu("Inicio");
		menuBar.add(Menu_inicio);
		
		JMenuItem MenuItem_salir = new JMenuItem("Salir");
		Menu_inicio.add(MenuItem_salir);
		
		JMenuItem MenuItem_cargarDatos = new JMenuItem("Cargar Datos");
		MenuItem_cargarDatos.addActionListener(new IngresarDatosAutomatico());
		Menu_inicio.add(MenuItem_cargarDatos);
		
		JMenu Menu_registros = new JMenu("Registros");
		menuBar.add(Menu_registros);
		
		getContentPane().add(altaUsuario.getFrame());
		JMenuItem MenuItem_altaUsuario = new JMenuItem("Alta Usuario");
		MenuItem_altaUsuario.setAction(elegirAltaUsuario);
		Menu_registros.add(MenuItem_altaUsuario);
		
		getContentPane().add(altaOfertaLaboral.getFrame());
		JMenuItem MenuItem_altaOfertaLab = new JMenuItem("Alta Oferta Laboral");
		MenuItem_altaOfertaLab.addActionListener(elegirAltaOfertaLaboral);
		Menu_registros.add(MenuItem_altaOfertaLab);
		
		getContentPane().add(altaTipoPublicacionOferta.getFrame());
		JMenuItem MenuItem_altaTipoPublicacion = new JMenuItem("Alta de Tipo de publicación de Oferta Laboral");
		MenuItem_altaTipoPublicacion.setAction(elegirAltaTipoPublicacionOferta);
		Menu_registros.add(MenuItem_altaTipoPublicacion);
		
		getContentPane().add(crearPaquete);
		JMenuItem MenuItem_altaPaquete = new JMenuItem("Crear Paquete de Tipos de publicación");
		MenuItem_altaPaquete.setAction(elegirCrearPaquete);
		Menu_registros.add(MenuItem_altaPaquete);
		
		getContentPane().add(altaDeKeyword.getFrame());
		JMenuItem MenuItem_altaKeyword = new JMenuItem("Alta de Keyword");
		MenuItem_altaKeyword.addActionListener(elegirAltaDeKeyword);
		Menu_registros.add(MenuItem_altaKeyword);
		
		getContentPane().add(postulacionAOfertaLaboral.getFrame());
		JMenuItem MenuItem_postulacionOfertaLaboral = new JMenuItem("Postulación a Oferta Laboral");
		MenuItem_postulacionOfertaLaboral.addActionListener(elegirPostulacionAOfertaLaboral);
		Menu_registros.add(MenuItem_postulacionOfertaLaboral);
		
		getContentPane().add(compraPaquete);
		JMenuItem mntmCompraPaquete = new JMenuItem("Compra Paquete");
		Menu_registros.add(mntmCompraPaquete);
		mntmCompraPaquete.setAction(elegirCompraPaquete);
		
		JMenu Menu_modificar = new JMenu("Modificar");
		menuBar.add(Menu_modificar);
		
		getContentPane().add(agregarTipoAPaquete);
		JMenuItem MenuItem_altaTipoPublicAPaquete = new JMenuItem("Agregar Tipo de publicación a paquete");
		Menu_modificar.add(MenuItem_altaTipoPublicAPaquete);
		MenuItem_altaTipoPublicAPaquete.setAction(elegirAgregarTipoAPaquete);
		
		
		getContentPane().add(modificarUsuario.getFrame());
		JMenuItem MenuItem_modificarDatosUser = new JMenuItem("Modificar Datos de Usuario");
		Menu_modificar.add(MenuItem_modificarDatosUser);
		MenuItem_modificarDatosUser.setAction(elegirModificarUsuario);
		
		JMenu Menu_consultas = new JMenu("Consultas");
		menuBar.add(Menu_consultas);
		
		getContentPane().add(consultaUsuario.getFrame());
		JMenuItem MenuItem_consultaUsuario = new JMenuItem("Consulta de Usuario");
		MenuItem_consultaUsuario.setAction(elegirConsultaUsuario);
		Menu_consultas.add(MenuItem_consultaUsuario);

		getContentPane().add(consultaOfertaLaboral);
		JMenuItem MenuItem_consultaOfertaLaboral = new JMenuItem("Consulta de Oferta Laboral");
		MenuItem_consultaOfertaLaboral.setAction(elegirConsultaOfertaLaboral);
		Menu_consultas.add(MenuItem_consultaOfertaLaboral);
		
		getContentPane().add(consultaPaquete);
		JMenuItem MenuItem_consultaPaquete = new JMenuItem("Consulta de Paquete");
		MenuItem_consultaPaquete.setAction(elegirConsultaPaquete);
		Menu_consultas.add(MenuItem_consultaPaquete);
		
		getContentPane().add(statusOferta);
		JMenuItem menuItemStatusOferta = new JMenuItem("Modificar estado oferta laboral");
		Menu_modificar.add(menuItemStatusOferta);
		//menuItemStatusOferta.addActionListener(new ElegirModificarEstadoOferta());
		menuItemStatusOferta.setAction(elegirModificarEstadoOferta);
		
		getContentPane().setSize(800, 600);
	}
	
	private class ElegirAltaUsuario extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ElegirAltaUsuario() {
			putValue(NAME, "Alta Usuario");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de alta usuario");
		}
		public void actionPerformed(ActionEvent event) {
			altaUsuario.getFrame().setVisible(!altaUsuario.getFrame().isVisible());
		}
	}
	
	private class ElegirConsultaUsuario extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ElegirConsultaUsuario() {
			putValue(NAME, "Consulta Usuario");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de consulta de usuario");
		}
		public void actionPerformed(ActionEvent event) {
			if (consultaUsuario.getFrame().isVisible()) {
				consultaUsuario.getFrame().setVisible(false);
			} else {
				consultaUsuario.getFrame().setVisible(true);
				consultaUsuario.actualizar();
			}
		}
	}

	private class ElegirModificarUsuario extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ElegirModificarUsuario() {
			putValue(NAME, "Modificar Usuario");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de consulta de usuario");
		}
		public void actionPerformed(ActionEvent event) {
			if (modificarUsuario.getFrame().isVisible()) {
				modificarUsuario.getFrame().setVisible(false);
			} else {
				modificarUsuario.getFrame().setVisible(true);
				modificarUsuario.actualizar();
			}
		}
	}
	
	private class ElegirAltaTipoPublicacionOferta extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ElegirAltaTipoPublicacionOferta() {
			putValue(NAME, "Alta de Tipo de publicación de Oferta Laboral");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de Alta de Tipo de publicación de Oferta Laboral");
		}
		public void actionPerformed(ActionEvent event) {
			if (altaTipoPublicacionOferta.getFrame().isVisible()) {
				altaTipoPublicacionOferta.getFrame().setVisible(false);
			} else {
				altaTipoPublicacionOferta.getFrame().setVisible(true);
			}
		}
	}
	
	private class ElegirAltaOfertaLaboral extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ElegirAltaOfertaLaboral() {
			putValue(NAME, "Alta de Oferta Laboral");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de Alta de Oferta Laboral");
		}
		public void actionPerformed(ActionEvent event) {
			if (altaOfertaLaboral.getFrame().isVisible()) {
				altaOfertaLaboral.getFrame().setVisible(false);
			} else {
				altaOfertaLaboral.getFrame().setVisible(true);
				altaOfertaLaboral.actualizar();
			}
		}
	}
	
	private class ElegirConsultaOfertaLaboral extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ElegirConsultaOfertaLaboral() {
			putValue(NAME, "Consulta de Oferta Laboral");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de Consulta de Oferta Laboral");
		}
		public void actionPerformed(ActionEvent event) {
			if (consultaOfertaLaboral.isVisible()) {
				consultaOfertaLaboral.setVisible(false);
				consultaOfertaLaboral.dispose();
			} else {
				consultaOfertaLaboral.setVisible(true);
				consultaOfertaLaboral.actualizar();
			}
		}
	}
	
	private class ElegirCrearPaquete extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ElegirCrearPaquete() {
			putValue(NAME, "Crear Paquete");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de Crear Paquete");
		}
		public void actionPerformed(ActionEvent event) {
			if (crearPaquete.isVisible()) {
				crearPaquete.setVisible(false);
				crearPaquete.dispose();
			} else {
				crearPaquete.setVisible(true);
			}
		}
	}
	
	private class ElegirConsultaPaquete extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ElegirConsultaPaquete() {
			putValue(NAME, "Consulta de Paquete de Tipos de publicación de Ofertas Laborales");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de Consulta de Paquete de Tipos de publicacion");
		}
		public void actionPerformed(ActionEvent event) {
			if (consultaPaquete.isVisible()) {
				consultaPaquete.setVisible(false);
				consultaPaquete.dispose();
			} else {
				consultaPaquete.setVisible(true);
				consultaPaquete.eliminarDatos();

				consultaPaquete.actualizar();
			}
		}
	}

	private class 	ElegirPostulacionAOfertaLaboral extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public 	ElegirPostulacionAOfertaLaboral() {
			putValue(NAME, "Postulacion a Oferta Laboral");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de Postulacion a oferta laboral");
		}
		public void actionPerformed(ActionEvent event) {
			if (postulacionAOfertaLaboral.getFrame().isVisible()) {
				postulacionAOfertaLaboral.getFrame().setVisible(false);
				postulacionAOfertaLaboral.getFrame().dispose();
			} else {
				postulacionAOfertaLaboral.getFrame().setVisible(true);
				postulacionAOfertaLaboral.actualizar();
			}
		}
	}
	
	private class ElegirAgregarTipoAPaquete extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ElegirAgregarTipoAPaquete() {
			putValue(NAME, "Agregar tipo a paquete");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de Agregar tipo publicacion a paquete");
		}
		public void actionPerformed(ActionEvent event) {
			if (agregarTipoAPaquete.isVisible()) {
				agregarTipoAPaquete.setVisible(false);
				agregarTipoAPaquete.dispose();
			} else {
				agregarTipoAPaquete.setVisible(true);
				agregarTipoAPaquete.actualizar();
			}
		}
	}

	private class ElegirAltaDeKeyword extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public 	ElegirAltaDeKeyword() {
			putValue(NAME, "Postulacion a Oferta Laboral");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de Postulacion a oferta laboral");
		}
		public void actionPerformed(ActionEvent event) {
			if (altaDeKeyword.getFrame().isVisible()) {
				altaDeKeyword.getFrame().setVisible(false);
				altaDeKeyword.getFrame().dispose();
			} else {
				altaDeKeyword.getFrame().setVisible(true);
			}
		}
	}
	
	private class ElegirModificarEstadoOferta extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public 	ElegirModificarEstadoOferta() {
			putValue(NAME, "Modificar estado de la oferta laboral");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de cambiar estado de oferta laboral");
		}
		public void actionPerformed(ActionEvent event) {
			if (statusOferta.isVisible()) {
				statusOferta.setVisible(false);
				statusOferta.dispose();
			} else {
				statusOferta.setVisible(true);
				statusOferta.actualizar();
			}
		}
	}
	
	private class ElegirCompraPaquete extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public 	ElegirCompraPaquete() {
			putValue(NAME, "Comprar paquete");
			putValue(SHORT_DESCRIPTION, "Mostrar la ventana de compra paquete");
		}
		public void actionPerformed(ActionEvent event) {
			if (compraPaquete.isVisible()) {
				compraPaquete.setVisible(false);
				compraPaquete.dispose();
			} else {
				compraPaquete.setVisible(true);
				try {
					compraPaquete.actualizar();
				} catch (NoExistePaquete e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
