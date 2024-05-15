package main.java.presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;


import main.java.logica.datatypes.DataPaquete;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IUsuario;
import main.java.logica.interfaces.IOferta;

import javax.swing.JButton;

public class CompraPaquete extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboPaquete = new JComboBox<String>();
	private JComboBox<String> comboEmpresa = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				CompraPaquete frame = new CompraPaquete();
				frame.setVisible(true);
			}
			
			
		});
	}
	

	
	public void actualizar() throws NoExistePaquete {
		  comboPaquete.removeAllItems();
		  IUsuario iusuario = Fabrica.getInstance().getIusuario();
		  IOferta ioferta = Fabrica.getInstance().getIOferta();
		  Set<DataPaquete> listaPaq =  ioferta.listarPaquetes();
		  Set<String> listaE = iusuario.listarEmpresas();
		  
		  Iterator<DataPaquete> iterPaq = listaPaq.iterator();
		  while (iterPaq.hasNext()) {
			  DataPaquete dtpaq = iterPaq.next();
			  comboPaquete.addItem(dtpaq.getNombre());
		  }
		  
		  Iterator<String> iterE = listaE.iterator();
		  while (iterE.hasNext()) {
			  comboEmpresa.addItem(iterE.next());
		  }
		  
	  }

	/**
	 * Create the frame.
	 */
	public CompraPaquete() {
		setTitle("Compra paquete");
		setResizable(true);
	    setIconifiable(true);
	    setMaximizable(true);
	    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    setClosable(true);
	    
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.anchor = GridBagConstraints.EAST;
		gbc_lblEmpresa.gridx = 0;
		gbc_lblEmpresa.gridy = 0;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);
		
		
		GridBagConstraints gbc_comboBoxEmpresa = new GridBagConstraints();
		gbc_comboBoxEmpresa.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEmpresa.gridx = 1;
		gbc_comboBoxEmpresa.gridy = 0;
		getContentPane().add(comboEmpresa, gbc_comboBoxEmpresa);
		
		JLabel lblPaquete = new JLabel("Paquete");
		GridBagConstraints gbc_lblPaquete = new GridBagConstraints();
		gbc_lblPaquete.anchor = GridBagConstraints.EAST;
		gbc_lblPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaquete.gridx = 0;
		gbc_lblPaquete.gridy = 1;
		getContentPane().add(lblPaquete, gbc_lblPaquete);
		
		GridBagConstraints gbc_comboBoxPaquete = new GridBagConstraints();
		gbc_comboBoxPaquete.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquete.gridx = 1;
		gbc_comboBoxPaquete.gridy = 1;
		getContentPane().add(comboPaquete, gbc_comboBoxPaquete);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		
		JButton btnAceptar = new JButton("Comprar");
		panel.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				IUsuario iusuario = Fabrica.getInstance().getIusuario();
				if (comboEmpresa.getSelectedIndex() != -1 && comboPaquete.getSelectedIndex() != -1) {
					try {
						String nomEmp = comboEmpresa.getSelectedItem().toString();
						String nomPaq = comboPaquete.getSelectedItem().toString();
						if (!iusuario.empresaYaTienePaquete(nomEmp, nomPaq)) {
							iusuario.agregarPaquete(nomPaq, nomEmp);
							JOptionPane.showMessageDialog(getContentPane(), "Paquete comprado con exito", "Compra paquete", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "La empresa seleccionada ya compró ese paquete", "Compra paquete", JOptionPane.INFORMATION_MESSAGE);
						}
						
					} catch (NoExisteEmpresa | NoExistePaquete e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "Faltan datos", "Compra paquete", JOptionPane.INFORMATION_MESSAGE);
				}
					
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);

	}

}
