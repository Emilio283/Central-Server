package main.java.presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.logica.datatypes.DataOfertaLaboral;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExisteOferta;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;
import main.java.logica.interfaces.IUsuario;

public class StatusOferta extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxEmpresa;
	private JComboBox<DataOfertaLaboral> comboBoxOfertaLaboral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				StatusOferta frame = new StatusOferta();
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StatusOferta() {
		IUsuario iusuario = Fabrica.getInstance().getIusuario();
		IOferta ioferta = Fabrica.getInstance().getIOferta();
		
		setTitle("Modificar estado de oferta laboral");

	    setResizable(true);
	    setIconifiable(true);
	    setMaximizable(true);
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    setClosable(true);

		
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.anchor = GridBagConstraints.EAST;
		gbc_lblEmpresa.gridx = 0;
		gbc_lblEmpresa.gridy = 0;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);
		
		comboBoxEmpresa = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxEmpresa = new GridBagConstraints();
		gbc_comboBoxEmpresa.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEmpresa.gridx = 1;
		gbc_comboBoxEmpresa.gridy = 0;
		getContentPane().add(comboBoxEmpresa, gbc_comboBoxEmpresa);
		comboBoxEmpresa.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent arg0) {
		    	    comboBoxOfertaLaboral.removeAllItems();
		    	    String nomEmpresa = (String) comboBoxEmpresa.getSelectedItem();
		    	    Set<DataOfertaLaboral> listaOfertas;
					try {
						listaOfertas = iusuario.listarOfertasLaboralesIngresadas(nomEmpresa);
						Iterator<DataOfertaLaboral> iterOfertas = listaOfertas.iterator();
						 if (!listaOfertas.isEmpty()) {
							 while (iterOfertas.hasNext()) {
						        comboBoxOfertaLaboral.addItem(iterOfertas.next());
							 }
					        comboBoxOfertaLaboral.setSelectedIndex(0);
						 }
					} catch (NoExisteEmpresa e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		      }
		});
		
		
		JLabel lblOfertaLaboral = new JLabel("Oferta Laboral:");
		GridBagConstraints gbc_lblOfertaLaboral = new GridBagConstraints();
		gbc_lblOfertaLaboral.anchor = GridBagConstraints.EAST;
		gbc_lblOfertaLaboral.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertaLaboral.gridx = 0;
		gbc_lblOfertaLaboral.gridy = 1;
		getContentPane().add(lblOfertaLaboral, gbc_lblOfertaLaboral);
		
		comboBoxOfertaLaboral = new JComboBox<DataOfertaLaboral>();
		GridBagConstraints gbc_comboBoxOfertaLaboral = new GridBagConstraints();
		gbc_comboBoxOfertaLaboral.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxOfertaLaboral.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOfertaLaboral.gridx = 1;
		gbc_comboBoxOfertaLaboral.gridy = 1;
		getContentPane().add(comboBoxOfertaLaboral, gbc_comboBoxOfertaLaboral);
		
		JLabel lblStatus = new JLabel("Estado:");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 2;
		getContentPane().add(lblStatus, gbc_lblStatus);
		
		JComboBox<String> comboBoxStatus = new JComboBox<String>();
		comboBoxStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Aceptada", "Rechazada"}));
		GridBagConstraints gbc_comboBoxStatus = new GridBagConstraints();
		gbc_comboBoxStatus.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStatus.gridx = 1;
		gbc_comboBoxStatus.gridy = 2;
		getContentPane().add(comboBoxStatus, gbc_comboBoxStatus);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		getContentPane().add(panel, gbc_panel);
		
		JButton btnAceptar = new JButton("Aceptar");
		panel.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent arg0) {
		    	 if (!comboBoxEmpresa.getSelectedItem().equals(-1) && !comboBoxOfertaLaboral.getSelectedItem().equals(-1) && !comboBoxStatus.getSelectedItem().equals(-1)){
		    		 try {
		    			DataOfertaLaboral dtOferta = (DataOfertaLaboral) comboBoxOfertaLaboral.getSelectedItem();
		    			String nombreOferta = dtOferta.getNombre();
						ioferta.cambiarEstadoOferta(nombreOferta, (String) comboBoxStatus.getSelectedItem().toString());
						JOptionPane.showMessageDialog(getContentPane(), "Se ha cambiado el status de la oferta laboral " + nombreOferta + " a estado " + (String) comboBoxStatus.getSelectedItem().toString() );
				        dispose();
		    		 } catch (NoExisteOferta e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	 }else {
		    		 JOptionPane.showMessageDialog(getContentPane(), "No puede haber campos vacíos.", "Error:", JOptionPane.ERROR_MESSAGE);
		    	 }
		      }
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
	    btnCancelar.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	    	  eliminarDatos();
	        dispose();
	      }
	    });


	}
	
	public void actualizar() {
		comboBoxOfertaLaboral.removeAllItems();
		comboBoxEmpresa.removeAllItems();
	    //IOferta ioferta = Fabrica.getInstance().getIOferta();
	    IUsuario iusuario = Fabrica.getInstance().getIusuario();
	    // Llenar combo paquetes
	    Set<String> listaE = iusuario.listarEmpresas();
	    Iterator<String> iter = listaE.iterator();

	    if (!listaE.isEmpty()) {
	      while (iter.hasNext()) {
	        comboBoxEmpresa.addItem(iter.next());
	      }
	      comboBoxEmpresa.setSelectedIndex(0);
	
	      /*String nomEmpresa = (String) comboBoxEmpresa.getSelectedItem();
	      Set<DataOfertaLaboral> listaOfertas;
			try {
				listaOfertas = iusuario.listarOfertasLaborales(nomEmpresa);
				Iterator<DataOfertaLaboral> iterOfertas = listaOfertas.iterator();
				 if (!listaE.isEmpty()) {
			        while (iterOfertas.hasNext()) {
			        	DataOfertaLaboral dataOf = iterOfertas.next();
			        	if (dataOf.getStatus().equals(StatusOfertaLaboral.Ingresada)) {
				          	comboBoxOfertaLaboral.addItem(dataOf);
			        	}

			        }
			        comboBoxOfertaLaboral.setSelectedIndex(0);
				 }
			} catch (NoExisteEmpresa e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
 
	    }
	  }
	
	  public void eliminarDatos() {
		  comboBoxEmpresa.removeAllItems();
		  comboBoxOfertaLaboral.removeAllItems();
	  }

}
