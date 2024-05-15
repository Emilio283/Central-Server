
package main.java.presentacion;

import javax.swing.JFrame;

import javax.swing.JInternalFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.Action;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import main.java.logica.datatypes.DataTipoPublicacion;
import main.java.logica.excepciones.YaExisteTipoPublicacion;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;

public class AltaTipoPublicacionOferta {

  private JInternalFrame frame;

  JInternalFrame getFrame() {
    return frame;
  }

  private JPanel panel;
  private JPanel panelBtns;
  private JButton btnAceptar;
  private JButton btnCancelar;
  private JPanel panelIngreso;
  private final Action aceptar = new Aceptar();
  private final Action cancelar = new Cancelar();
  private JLabel nombreLabel;
  private JTextField nombreTextField;
  private JLabel descripcionLabel;
  private JEditorPane descripcionEditorPane;
  private JLabel exposicionLabel;
  private JLabel duracionLabel;
  private JLabel costoLabel;
  private JLabel fechaDeAltaLabel;
  private JTextField fechaDeAltaTextField;
  private JTextField textFieldexposicion;
  private JTextField textFieldduracion;
  private JTextField textFieldcosto;

  public AltaTipoPublicacionOferta() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JInternalFrame();
    frame.setTitle("Alta de Tipo de publicación de Oferta Laboral");
    frame.setClosable(true);
    frame.setResizable(true);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

    panel = new JPanel();
    frame.getContentPane().add(panel);
    panel.setLayout(new BorderLayout(0, 0));

    panelIngreso = new JPanel();
    panel.add(panelIngreso, BorderLayout.CENTER);
    panelIngreso.setLayout(new GridLayout(0, 2, 0, 10));

    nombreLabel = new JLabel("Nombre");
    nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelIngreso.add(nombreLabel);

    nombreTextField = new JTextField();
    panelIngreso.add(nombreTextField);
    nombreTextField.setColumns(10);

    descripcionLabel = new JLabel("Descripción");
    descripcionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelIngreso.add(descripcionLabel);

    descripcionEditorPane = new JEditorPane();
    panelIngreso.add(descripcionEditorPane);
    // descripcionEditorPane.setColumns(10);

    exposicionLabel = new JLabel("Exposicion");
    exposicionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelIngreso.add(exposicionLabel);

    textFieldexposicion = new JTextField();
    panelIngreso.add(textFieldexposicion);
    textFieldexposicion.setColumns(10);

    duracionLabel = new JLabel("Duración (días)");
    duracionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelIngreso.add(duracionLabel);

    textFieldduracion = new JTextField();
    panelIngreso.add(textFieldduracion);
    textFieldduracion.setColumns(10);

    costoLabel = new JLabel("Costo");
    costoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelIngreso.add(costoLabel);

    textFieldcosto = new JTextField();
    panelIngreso.add(textFieldcosto);
    textFieldcosto.setColumns(10);

    fechaDeAltaLabel = new JLabel("Fecha de alta (yyyy-mm-dd)");
    fechaDeAltaLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelIngreso.add(fechaDeAltaLabel);

    fechaDeAltaTextField = new JTextField();
    panelIngreso.add(fechaDeAltaTextField);
    fechaDeAltaTextField.setColumns(10);

    panelBtns = new JPanel();
    FlowLayout flowLayout = (FlowLayout) panelBtns.getLayout();
    flowLayout.setAlignment(FlowLayout.RIGHT);
    panel.add(panelBtns, BorderLayout.SOUTH);

    btnAceptar = new JButton("Aceptar");
    btnAceptar.setAction(aceptar);
    panelBtns.add(btnAceptar);

    btnCancelar = new JButton("Cancelar");
    btnCancelar.setAction(cancelar);
    panelBtns.add(btnCancelar);

    frame.pack();
  }

  private class Aceptar extends AbstractAction {
    private static final long serialVersionUID = 1L;

    public Aceptar() {
      putValue(NAME, "Aceptar");
      putValue(SHORT_DESCRIPTION, "Some short description");
    }

    public void actionPerformed(ActionEvent event) {

      String exp = textFieldexposicion.getText();
      String dur = textFieldduracion.getText();
      String cos = textFieldcosto.getText();

      int duracion;
      float costo;
      int exposicion;

      if (textFieldexposicion.getText().isBlank() || textFieldduracion.getText().isBlank()
          || textFieldcosto.getText().isBlank() || nombreTextField.getText().isBlank()
          || descripcionEditorPane.getText().isBlank()
          || fechaDeAltaTextField.getText().isBlank()) {
        JOptionPane.showMessageDialog(frame, "No puede haber campos vacíos.", "Error:",
            JOptionPane.ERROR_MESSAGE);
      } else {
        try {
          exposicion = Integer.parseInt(exp);
          if (exposicion < 0) {
            JOptionPane.showMessageDialog(frame,
                "`" + textFieldexposicion.getText()
                    + "` no es una exposicion valida. Ingrese un número positivo.",
                "Error:", JOptionPane.ERROR_MESSAGE);
            textFieldexposicion.setText("");
            return;
          }
        } catch (NumberFormatException exception) {
          JOptionPane.showMessageDialog(frame,
              "`" + textFieldexposicion.getText() + "` no es una exposicion valida", "Error:",
              JOptionPane.ERROR_MESSAGE);
          textFieldexposicion.setText("");
          return;
        }

        try {
          duracion = Integer.parseInt(dur);
          if (duracion < 1) {
            JOptionPane.showMessageDialog(frame,
                "`" + textFieldduracion.getText()
                    + "` no es una duracion valida. Ingrese un número mayor que 0",
                "Error:", JOptionPane.ERROR_MESSAGE);
            textFieldduracion.setText("");
            return;
          }
        } catch (NumberFormatException exception) {
          JOptionPane.showMessageDialog(frame,
              "`" + textFieldduracion.getText() + "` no es una duracion valida", "Error:",
              JOptionPane.ERROR_MESSAGE);
          textFieldduracion.setText("");
          return;
        }

        try {
          costo = Float.parseFloat(cos);
          if (costo < 0) {
            JOptionPane.showMessageDialog(frame,
                "`" + textFieldcosto.getText()
                    + "` no es un costo valido. Ingrese un número positivo.",
                "Error:", JOptionPane.ERROR_MESSAGE);
            textFieldcosto.setText("");
            return;
          }
        } catch (NumberFormatException exception) {
          JOptionPane.showMessageDialog(frame,
              "`" + textFieldcosto.getText() + "` no es un costo valido", "Error:",
              JOptionPane.ERROR_MESSAGE);
          textFieldcosto.setText("");
          return;
        }

        try {
          LocalDate fecha = LocalDate.parse(fechaDeAltaTextField.getText());

          DataTipoPublicacion dtp = new DataTipoPublicacion(nombreTextField.getText(),
              descripcionEditorPane.getText(), exposicion, duracion, costo, fecha);

          IOferta ioferta = Fabrica.getInstance().getIOferta();

          ioferta.insertarTipoPublicacion(dtp);
          JOptionPane.showMessageDialog(frame, "Tipo de publicación agregado con exito.");
          limpiarDatos();

        } catch (DateTimeParseException exception) {
          JOptionPane.showMessageDialog(frame, "No se pudo parsear la fecha de alta");
          fechaDeAltaTextField.setText("");
        } catch (IllegalArgumentException exception) {
          JOptionPane.showMessageDialog(frame, exception.getMessage());
        } catch (YaExisteTipoPublicacion exception) {
          JOptionPane.showMessageDialog(frame, exception.getMessage());
        }
      }
    }
  }

  private void limpiarDatos() {
    nombreTextField.setText("");
    descripcionEditorPane.setText("");
    textFieldexposicion.setText("");
    textFieldduracion.setText("");
    textFieldcosto.setText("");
    fechaDeAltaTextField.setText("");

  }

  private class Cancelar extends AbstractAction {
    private static final long serialVersionUID = 1L;

    public Cancelar() {
      putValue(NAME, "Cancelar");
      putValue(SHORT_DESCRIPTION, "Cancelar el caso de uso");
    }

    public void actionPerformed(ActionEvent event) {
      limpiarDatos();
      frame.setVisible(false);
      borrarCampos();
    }
  }

  private void borrarCampos() {
    // If the text is null or empty, has the effect of simply deleting the old text.
    nombreTextField.setText(null);
    descripcionEditorPane.setText(null);
    fechaDeAltaTextField.setText(null);
    textFieldexposicion.setText(null);
    textFieldduracion.setText(null);
    textFieldcosto.setText(null);
  }
}
