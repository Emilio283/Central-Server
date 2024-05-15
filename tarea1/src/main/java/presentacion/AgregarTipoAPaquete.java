package main.java.presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import main.java.logica.datatypes.DataPaquete;
import main.java.logica.datatypes.DataTipoPublicacion;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.excepciones.NoExisteTipoPaquete;
import main.java.logica.excepciones.NoExisteTipoPublicacion;
import main.java.logica.excepciones.YaExisteTipoEnPaquete;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JComboBox;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AgregarTipoAPaquete extends JInternalFrame {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JComboBox<DataPaquete> paquetes;
  private JComboBox<DataTipoPublicacion> tiposPubli;

  private JTextField textFieldcantidad;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
          AgregarTipoAPaquete frame = new AgregarTipoAPaquete();
          frame.setVisible(true); 
      }
    });
  }

  /**
   * Create the frame.
   */
  public AgregarTipoAPaquete() {
    setBounds(100, 100, 447, 164);
    setTitle("Agregar Tipo a Paquete");
    IOferta ioferta = Fabrica.getInstance().getIOferta();
    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setClosable(true);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
    gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
    gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
    getContentPane().setLayout(gridBagLayout);

    JLabel lblNewLabel = new JLabel("");
    GridBagConstraints gbclblNewLabel = new GridBagConstraints();
    gbclblNewLabel.insets = new Insets(0, 0, 5, 5);
    gbclblNewLabel.gridx = 0;
    gbclblNewLabel.gridy = 0;
    getContentPane().add(lblNewLabel, gbclblNewLabel);

    JLabel lblNewLabel1 = new JLabel("Paquete:");
    GridBagConstraints gbclblNewLabel1 = new GridBagConstraints();
    gbclblNewLabel1.insets = new Insets(0, 0, 5, 5);
    // gbclblNewLabel1.insets = new Insets(0, 0, 5, 5);
    gbclblNewLabel1.anchor = GridBagConstraints.EAST;
    gbclblNewLabel1.gridx = 1;
    gbclblNewLabel1.gridy = 1;
    getContentPane().add(lblNewLabel1, gbclblNewLabel1);

    paquetes = new JComboBox<DataPaquete>();
    GridBagConstraints gbccomboBoxpaquete = new GridBagConstraints();
    gbccomboBoxpaquete.insets = new Insets(0, 0, 5, 0);
    gbccomboBoxpaquete.fill = GridBagConstraints.HORIZONTAL;
    gbccomboBoxpaquete.gridx = 2;
    gbccomboBoxpaquete.gridy = 1;
    getContentPane().add(paquetes, gbccomboBoxpaquete);

    JLabel lblNewLabel2 = new JLabel("Tipo Publicación:");
    GridBagConstraints gbclblNewLabel2 = new GridBagConstraints();
    gbclblNewLabel2.anchor = GridBagConstraints.EAST;
    gbclblNewLabel2.insets = new Insets(0, 0, 5, 5);
    gbclblNewLabel2.gridx = 1;
    gbclblNewLabel2.gridy = 2;
    getContentPane().add(lblNewLabel2, gbclblNewLabel2);

    tiposPubli = new JComboBox<DataTipoPublicacion>();
    GridBagConstraints gbccomboBoxtipoPubli = new GridBagConstraints();
    gbccomboBoxtipoPubli.insets = new Insets(0, 0, 5, 0);
    gbccomboBoxtipoPubli.fill = GridBagConstraints.HORIZONTAL;
    gbccomboBoxtipoPubli.gridx = 2;
    gbccomboBoxtipoPubli.gridy = 2;
    getContentPane().add(tiposPubli, gbccomboBoxtipoPubli);

    JLabel lblNewLabel3 = new JLabel("Cantidad:");
    GridBagConstraints gbclblNewLabel3 = new GridBagConstraints();
    gbclblNewLabel3.anchor = GridBagConstraints.EAST;
    gbclblNewLabel3.insets = new Insets(0, 0, 5, 5);
    gbclblNewLabel3.gridx = 1;
    gbclblNewLabel3.gridy = 3;
    getContentPane().add(lblNewLabel3, gbclblNewLabel3);

    textFieldcantidad = new JTextField();
    GridBagConstraints gbctextFieldcantidad = new GridBagConstraints();
    gbctextFieldcantidad.insets = new Insets(0, 0, 5, 0);
    gbctextFieldcantidad.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldcantidad.gridx = 2;
    gbctextFieldcantidad.gridy = 3;
    getContentPane().add(textFieldcantidad, gbctextFieldcantidad);
    textFieldcantidad.setColumns(10);

    JPanel panel = new JPanel();
    GridBagConstraints gbcpanel = new GridBagConstraints();
    gbcpanel.fill = GridBagConstraints.BOTH;
    gbcpanel.gridx = 2;
    gbcpanel.gridy = 4;
    getContentPane().add(panel, gbcpanel);

    JButton btnAceptar = new JButton("Aceptar");
    panel.add(btnAceptar);
    btnAceptar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (textFieldcantidad.getText().isBlank() || paquetes.getSelectedIndex() == -1
            || tiposPubli.getSelectedIndex() == -1) {
          JOptionPane.showMessageDialog(getContentPane(),
              "Error: Faltan datos o hay datos ingresados inválidos.",
              "Agregar tipo publicacion a paquete", JOptionPane.INFORMATION_MESSAGE);
        } else {
          String can = textFieldcantidad.getText();
          int cantidad;

          try {
            cantidad = Integer.parseInt(can);
            if (cantidad < 1) {
              JOptionPane.showMessageDialog(getContentPane(),
                  "`" + textFieldcantidad.getText()
                      + "` no es una cantidad valida. Ingrese un número mayor que 0",
                  "Error:", JOptionPane.ERROR_MESSAGE);
              textFieldcantidad.setText("");
              return;
            }
          } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(getContentPane(),
                "`" + textFieldcantidad.getText() + "` no es una cantidad valida", "Error:",
                JOptionPane.ERROR_MESSAGE);
            textFieldcantidad.setText("");
            return;
          }

          try {
        	  if (paquetes.getSelectedIndex() != -1 && tiposPubli.getSelectedIndex() != -1) {
        		  ioferta.agregarTipo(paquetes.getSelectedItem().toString(),
        	                (String) tiposPubli.getSelectedItem().toString(), cantidad);
        	            JOptionPane.showMessageDialog(getContentPane(),
        	                "Se ha agregado el tipo de publicacion al paquete con exito",
        	                "Agregar tipo a paquete", JOptionPane.INFORMATION_MESSAGE);
        	            limpiarFormulario();
        	            dispose();
        	  }
          } catch (YaExisteTipoEnPaquete e1) {
            JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(),
                "Agregar tipo a paquete", JOptionPane.INFORMATION_MESSAGE);
          } catch (NoExisteTipoPaquete e1) {
            JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(),
                "Agregar tipo a paquete", JOptionPane.INFORMATION_MESSAGE);
          } catch (NoExistePaquete e1) {
            JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(),
                "Agregar tipo a paquete", JOptionPane.INFORMATION_MESSAGE);
          } catch (NoExisteTipoPublicacion e1) {
            JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(),
                "Agregar tipo a paquete", JOptionPane.INFORMATION_MESSAGE);
          }
        }
      }
    });

    JButton btnCancelar = new JButton("Cancelar");
    panel.add(btnCancelar);
    btnCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        limpiarFormulario();
        dispose();
      }
    });

  }

  public void actualizar() {
    IOferta ioferta = Fabrica.getInstance().getIOferta();
    paquetes.removeAllItems();
    tiposPubli.removeAllItems();

    // Llenar combo paquetes
    Set<DataPaquete> listaP;

    listaP = ioferta.listarPaquetesNoComprados();
    Iterator<DataPaquete> iter = listaP.iterator();
    while (iter.hasNext()) {
      paquetes.addItem(iter.next());
    }

    Iterator<DataTipoPublicacion> iTP = ioferta.listarTipos().iterator();

    while (iTP.hasNext()) {
      tiposPubli.addItem(iTP.next());
    }
  }

  public void limpiarFormulario() {
    paquetes.setSelectedIndex(-1);
    tiposPubli.setSelectedIndex(-1);
    textFieldcantidad.setText("");
  }
}
