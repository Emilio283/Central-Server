package main.java.presentacion;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.logica.datatypes.DataOfertaLaboral;
import main.java.logica.datatypes.DataPostulante;
import main.java.logica.datatypes.DataUsuario;
import main.java.logica.excepciones.NoExisteOferta;
import main.java.logica.excepciones.NoExistePostulante;
import main.java.logica.excepciones.OfertaLaboralNoVigente;
import main.java.logica.excepciones.YaExistePostulacion;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;
import main.java.logica.interfaces.IUsuario;
import main.java.presentacion.reutilizables.VerOfertaLaboral;

public class PostulacionAOfertaLaboral {

  private JInternalFrame frame;
  
  JInternalFrame getFrame() {
    return frame;
  }
  
  private VerOfertaLaboral ofertaPanel;
  
  VerOfertaLaboral getOfertaPanel() {
    return ofertaPanel;
  }
  
  private JEditorPane motivacionEditorPane;
  private JEditorPane cvEditorPane;
  private JTextField fechaPostulacionTextField;
  private JComboBox<DataPostulante> postulanteComboBox;

  /**
   * Create the frame.
   */
  public PostulacionAOfertaLaboral() {
    frame = new JInternalFrame("Postulacion a Oferta Laboral");
    frame.setClosable(true);
    frame.setResizable(true);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    frame.setBounds(100, 100, 600, 800);
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

    ofertaPanel = new VerOfertaLaboral(frame);
    frame.getContentPane().add(ofertaPanel);

    JPanel postulantePanel = new JPanel();
    frame.getContentPane().add(postulantePanel);
    postulantePanel.setLayout(new GridLayout(0, 2, 0, 5));

    JLabel posultanteLabel = new JLabel("Postulante");
    posultanteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    postulantePanel.add(posultanteLabel);

    postulanteComboBox = new JComboBox<DataPostulante>();
    postulantePanel.add(postulanteComboBox);

    JLabel cvLabel = new JLabel("CV");
    cvLabel.setHorizontalAlignment(SwingConstants.CENTER);
    postulantePanel.add(cvLabel);

    cvEditorPane = new JEditorPane();
    postulantePanel.add(cvEditorPane);

    JLabel motivacionLabel = new JLabel("Motivacion");
    motivacionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    postulantePanel.add(motivacionLabel);

    motivacionEditorPane = new JEditorPane();
    postulantePanel.add(motivacionEditorPane);

    JLabel fechaPostulacionLabel = new JLabel("Fecha de postulacion (yyyy-mm-dd)");
    fechaPostulacionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    postulantePanel.add(fechaPostulacionLabel);

    fechaPostulacionTextField = new JTextField();
    postulantePanel.add(fechaPostulacionTextField);
    fechaPostulacionTextField.setColumns(10);

    JPanel panelBtns = new JPanel();
    frame.getContentPane().add(panelBtns);
    panelBtns.setLayout(new BoxLayout(panelBtns, BoxLayout.X_AXIS));

    Component horizontalGlue = Box.createHorizontalGlue();
    panelBtns.add(horizontalGlue);

    JButton aceptarBtn = new JButton("Aceptar");
    aceptarBtn.setAction(new Aceptar());
    panelBtns.add(aceptarBtn);

    JButton cancelarBtn = new JButton("Cancelar");
    cancelarBtn.setAction(new Cancelar());
    panelBtns.add(cancelarBtn);
  }

  public void actualizar() {
    ofertaPanel.actualizar();

    postulanteComboBox.removeAll();

    IUsuario iusuario = Fabrica.getInstance().getIusuario();

    Iterator<DataUsuario> iter = iusuario.listarDataUsuarios().iterator();
    while (iter.hasNext()) {
      DataUsuario dataUsuario = iter.next();
      if (dataUsuario instanceof DataPostulante) {
        postulanteComboBox.addItem((DataPostulante) dataUsuario);
      }
    }
  }

  @SuppressWarnings("serial")
  private class Cancelar extends AbstractAction {
    Cancelar() {
      putValue(NAME, "Cancelar");
      putValue(SHORT_DESCRIPTION, "Cancelar el caso de uso");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
      frame.setVisible(false);
      borrarCampos();
    }

  }

  @SuppressWarnings("serial")
  private class Aceptar extends AbstractAction {
    Aceptar() {
      putValue(NAME, "Aceptar");
      putValue(SHORT_DESCRIPTION, "Aceptar el caso de uso");
    }

    @Override
    public void actionPerformed(ActionEvent event) {
      String curriculum = cvEditorPane.getText();
      String motivacion = motivacionEditorPane.getText();
      String fechaPostulacion = fechaPostulacionTextField.getText();

      if (curriculum.isBlank() || motivacion.isBlank() || fechaPostulacion.isBlank()) {
        JOptionPane.showMessageDialog(frame, "Faltan campos por completar");
        return;
      }

      DataPostulante dataPostulante;
      DataOfertaLaboral dol;

      Object selectedItem = postulanteComboBox.getSelectedItem();

      if (!(selectedItem instanceof DataPostulante) || selectedItem == null) {
        JOptionPane.showMessageDialog(frame, "Tiene que seleccionar empresa y oferta");
        return;
      }

      dataPostulante = (DataPostulante) selectedItem;
      dol = ofertaPanel.getOfertaSeleccionada();

      try {
        LocalDate fecha = LocalDate.parse(fechaPostulacion);

        IOferta ioferta = Fabrica.getInstance().getIOferta();

        ioferta.postulacionOfertaLaboral(dol.getNombre(), dataPostulante.getNickname(), curriculum, motivacion, fecha);
        JOptionPane.showMessageDialog(frame, "Postulación creada con exito",
            "Postulacion a oferta laboral", JOptionPane.INFORMATION_MESSAGE);
        borrarCampos();
        frame.setVisible(false);

      } catch (IllegalArgumentException exception) {
        JOptionPane.showMessageDialog(frame, exception.getMessage());
      } catch (DateTimeParseException exception) {
        JOptionPane.showMessageDialog(frame, "No se pudo parsear la fecha");
        fechaPostulacionTextField.setText("");
      } catch (NoExisteOferta e1) {
        JOptionPane.showMessageDialog(frame, "No existe la oferta seleccionada");
      } catch (NoExistePostulante e1) {
        JOptionPane.showMessageDialog(frame, "No existe el postulante seleccionado");
      } catch (YaExistePostulacion e1) {
        JOptionPane.showMessageDialog(frame, e1.getMessage());
      } catch (OfertaLaboralNoVigente e1) {
        JOptionPane.showMessageDialog(frame, e1.getMessage());
      }
    }
  }

  private void borrarCampos() {
    // If the text is null or empty, has the effect of simply deleting the old text.
    cvEditorPane.setText(null);
    motivacionEditorPane.setText(null);
    fechaPostulacionTextField.setText(null);
  }

}
