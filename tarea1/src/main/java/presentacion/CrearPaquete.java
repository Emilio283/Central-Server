package main.java.presentacion;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.logica.excepciones.YaExistePaquete;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;

public class CrearPaquete extends JInternalFrame {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JTextField textFieldnombre;
  private JEditorPane editorPanedescripcion;
  private JTextField textFieldfechaAlta;
  private JTextField textFieldvalidez;
  private JTextField textFielddescuento;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        CrearPaquete frame = new CrearPaquete();
        frame.setVisible(true);
      }
    });
  }

  /**
   * Create the frame.
   */
  public CrearPaquete() {
    IOferta ioferta = Fabrica.getInstance().getIOferta();
    setTitle("Crear Paquete");

    setResizable(true);
    setIconifiable(true);
    setMaximizable(true);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setClosable(true);

    setBounds(100, 100, 461, 195);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
    gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
    gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
    getContentPane().setLayout(gridBagLayout);

    JLabel lblNewLabel = new JLabel("Nombre:");
    GridBagConstraints gbclblNewLabel = new GridBagConstraints();
    gbclblNewLabel.insets = new Insets(0, 0, 5, 5);
    gbclblNewLabel.anchor = GridBagConstraints.EAST;
    gbclblNewLabel.gridx = 0;
    gbclblNewLabel.gridy = 0;
    getContentPane().add(lblNewLabel, gbclblNewLabel);

    textFieldnombre = new JTextField();
    GridBagConstraints gbctextFieldnombre = new GridBagConstraints();
    gbctextFieldnombre.insets = new Insets(0, 0, 5, 0);
    gbctextFieldnombre.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldnombre.gridx = 1;
    gbctextFieldnombre.gridy = 0;
    getContentPane().add(textFieldnombre, gbctextFieldnombre);
    textFieldnombre.setColumns(10);

    JLabel lblNewLabel1 = new JLabel("Descripcion:");
    GridBagConstraints gbclblNewLabel1 = new GridBagConstraints();
    gbclblNewLabel1.anchor = GridBagConstraints.EAST;
    gbclblNewLabel1.insets = new Insets(0, 0, 5, 5);
    gbclblNewLabel1.gridx = 0;
    gbclblNewLabel1.gridy = 1;
    getContentPane().add(lblNewLabel1, gbclblNewLabel1);

    editorPanedescripcion = new JEditorPane();
    GridBagConstraints gbctextFielddescripcion = new GridBagConstraints();
    gbctextFielddescripcion.insets = new Insets(0, 0, 5, 0);
    gbctextFielddescripcion.fill = GridBagConstraints.HORIZONTAL;
    gbctextFielddescripcion.gridx = 1;
    gbctextFielddescripcion.gridy = 1;
    getContentPane().add(editorPanedescripcion, gbctextFielddescripcion);
    // editorPanedescripcion.setColumns(10);

    JLabel lblNewLabel2 = new JLabel("Validez (Días):");
    GridBagConstraints gbclblNewLabel2 = new GridBagConstraints();
    gbclblNewLabel2.anchor = GridBagConstraints.EAST;
    gbclblNewLabel2.insets = new Insets(0, 0, 5, 5);
    gbclblNewLabel2.gridx = 0;
    gbclblNewLabel2.gridy = 2;
    getContentPane().add(lblNewLabel2, gbclblNewLabel2);

    textFieldvalidez = new JTextField();
    GridBagConstraints gbctextFieldvalidez = new GridBagConstraints();
    gbctextFieldvalidez.insets = new Insets(0, 0, 5, 0);
    gbctextFieldvalidez.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldvalidez.gridx = 1;
    gbctextFieldvalidez.gridy = 2;
    getContentPane().add(textFieldvalidez, gbctextFieldvalidez);
    textFieldvalidez.setColumns(10);

    JLabel lblNewLabel3 = new JLabel("Descuento");
    GridBagConstraints gbclblNewLabel3 = new GridBagConstraints();
    gbclblNewLabel3.anchor = GridBagConstraints.EAST;
    gbclblNewLabel3.insets = new Insets(0, 0, 5, 5);
    gbclblNewLabel3.gridx = 0;
    gbclblNewLabel3.gridy = 3;
    getContentPane().add(lblNewLabel3, gbclblNewLabel3);

    textFielddescuento = new JTextField();
    GridBagConstraints gbctextFielddescuento = new GridBagConstraints();
    gbctextFielddescuento.insets = new Insets(0, 0, 5, 0);
    gbctextFielddescuento.fill = GridBagConstraints.HORIZONTAL;
    gbctextFielddescuento.gridx = 1;
    gbctextFielddescuento.gridy = 3;
    getContentPane().add(textFielddescuento, gbctextFielddescuento);
    textFielddescuento.setColumns(10);

    JLabel lblNewLabel4 = new JLabel("Fecha");
    GridBagConstraints gbclblNewLabel4 = new GridBagConstraints();
    gbclblNewLabel4.anchor = GridBagConstraints.EAST;
    gbclblNewLabel4.insets = new Insets(0, 0, 5, 5);
    gbclblNewLabel4.gridx = 0;
    gbclblNewLabel4.gridy = 4;
    getContentPane().add(lblNewLabel4, gbclblNewLabel4);

    textFieldfechaAlta = new JTextField();
    GridBagConstraints gbctextFieldfechaAlta = new GridBagConstraints();
    gbctextFieldfechaAlta.insets = new Insets(0, 0, 5, 0);
    gbctextFieldfechaAlta.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldfechaAlta.gridx = 1;
    gbctextFieldfechaAlta.gridy = 4;
    getContentPane().add(textFieldfechaAlta, gbctextFieldfechaAlta);
    textFieldfechaAlta.setColumns(10);

    JPanel panel = new JPanel();
    GridBagConstraints gbcpanel = new GridBagConstraints();
    gbcpanel.fill = GridBagConstraints.BOTH;
    gbcpanel.gridx = 1;
    gbcpanel.gridy = 5;
    getContentPane().add(panel, gbcpanel);

    JButton btnAceptar = new JButton("Aceptar");
    panel.add(btnAceptar);
    btnAceptar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        if (textFieldnombre.getText().isBlank() || editorPanedescripcion.getText().isBlank()
            || textFieldvalidez.getText().isBlank() || textFieldfechaAlta.getText().isBlank()
            || textFielddescuento.getText().isBlank()) {
          JOptionPane.showMessageDialog(getContentPane(), "No puede haber campos vacíos.", "Error:",
              JOptionPane.ERROR_MESSAGE);
        } else {
          String val = textFieldvalidez.getText();
          String des = textFielddescuento.getText();
          int validez;
          float descuento;

          try {
            descuento = Float.parseFloat(des);
            if (descuento < 0.0) {
              JOptionPane.showMessageDialog(getContentPane(), "`" + textFielddescuento.getText()
                  + "` no es un descuento valido. Ingrese un número positivo (El número puede ser con punto).",
                  "Error:", JOptionPane.ERROR_MESSAGE);
              textFielddescuento.setText("");
              return;
            }
          } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(getContentPane(), "`" + textFielddescuento.getText()
                + "` no es un descuento valido. Inserte un número positivo (El número puede ser con punto).",
                "Error:", JOptionPane.ERROR_MESSAGE);
            textFielddescuento.setText("");
            return;
          }

          try {
            validez = Integer.parseInt(val);
            if (validez < 1) {
              JOptionPane.showMessageDialog(getContentPane(),
                  "`" + textFieldvalidez.getText()
                      + "` no es una validez valida. Ingrese un número positivo mayor a 0.",
                  "Error:", JOptionPane.ERROR_MESSAGE);
              textFieldvalidez.setText("");
              return;
            }
          } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(getContentPane(),
                "`" + textFieldvalidez.getText() + "` no es una validez valida", "Error:",
                JOptionPane.ERROR_MESSAGE);
            textFieldvalidez.setText("");
            return;
          }

          try {
            LocalDate fecha = LocalDate.parse(textFieldfechaAlta.getText());
            ioferta.insertarDatosPaquete(textFieldnombre.getText(), editorPanedescripcion.getText(),
                validez, descuento, fecha, null);
            JOptionPane.showMessageDialog(getContentPane(), "Paquete creado con exito",
                "Crear Paquete", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            dispose();

          } catch (YaExistePaquete e1) {
            JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "Crear Paquete",
                JOptionPane.INFORMATION_MESSAGE);
          } catch (DateTimeParseException exception) {
            JOptionPane.showMessageDialog(getContentPane(), "No se pudo parsear la fecha",
                "Alta de Usuario", JOptionPane.ERROR_MESSAGE);
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

  private void limpiarFormulario() {
    textFieldnombre.setText("");
    editorPanedescripcion.setText("");
    textFieldfechaAlta.setText("");
    textFieldvalidez.setText("");
    textFielddescuento.setText("");
  }

}
