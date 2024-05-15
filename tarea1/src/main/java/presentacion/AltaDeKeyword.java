package main.java.presentacion;

import javax.swing.JFrame;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.java.logica.excepciones.YaExisteKeyword;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;

public class AltaDeKeyword {
	
	private JInternalFrame frame;
	
	JInternalFrame getFrame() {
	  return frame;
	}
	
	private JTextField keywordTextField;
	
	AltaDeKeyword() {
		initialize();
	}

	private void initialize() {
		frame = new JInternalFrame();
		frame.setClosable(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setTitle("Alta de Keyword");
		
		JPanel ingresoPanel = new JPanel();
		frame.getContentPane().add(ingresoPanel, BorderLayout.CENTER);
		
		JLabel keywordLabel = new JLabel("Keyword");
		ingresoPanel.add(keywordLabel);
		
		keywordTextField = new JTextField();
		ingresoPanel.add(keywordTextField);
		keywordTextField.setColumns(10);
		
		JPanel panelBtns = new JPanel();
		frame.getContentPane().add(panelBtns, BorderLayout.SOUTH);
		panelBtns.setLayout(new BoxLayout(panelBtns, BoxLayout.X_AXIS));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panelBtns.add(horizontalGlue);
		
		JButton aceptarBtn = new JButton("Aceptar");
		aceptarBtn.addActionListener(new Aceptar());
		panelBtns.add(aceptarBtn);
		
		JButton cancelarBtn = new JButton("Cancelar");
		cancelarBtn.addActionListener(new Cancelar());
		panelBtns.add(cancelarBtn);
		
		frame.pack();
	}

	private class Aceptar extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public Aceptar() {
			putValue(NAME, "Aceptar");
			putValue(SHORT_DESCRIPTION, "Aceptar el caso de uso");
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			String keyword = keywordTextField.getText();
			
			if (keyword.isBlank()) {
    			JOptionPane.showMessageDialog(frame, "No se puede ingresar una keyword vacia", "No Keyword", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			IOferta ioferta = Fabrica.getInstance().getIOferta();
			
			try {
			  ioferta.nomKeyword(keyword);
				keywordTextField.setText(null);
				frame.setVisible(false);
			} catch (YaExisteKeyword exception) {
    			JOptionPane.showMessageDialog(frame, "Ya existe la keyword `" + keyword + "`", "Keyowrd repetida", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private class Cancelar extends AbstractAction {
		
		private static final long serialVersionUID = 1L;

		public Cancelar() {
			putValue(NAME, "Cancelar");
			putValue(SHORT_DESCRIPTION, "Cancelar el caso de uso");
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			frame.setVisible(false);
			keywordTextField.setText(null);
		}
		
	}
}
