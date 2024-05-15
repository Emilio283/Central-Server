package main.java.presentacion.reutilizables;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.logica.datatypes.DataEmpresa;
import main.java.logica.datatypes.DataPostulante;
import main.java.logica.datatypes.DataUsuario;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExistePostulante;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IUsuario;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;

public class VerUsuario {

  final private JInternalFrame frame = new JInternalFrame();
  
  public JInternalFrame getFrame() {
    return frame;
  }
  
  private JComboBox<DataUsuario> elegirUsuarioComboBox = new JComboBox<DataUsuario>();
  private final JLabel nickLabel = new JLabel("Nick");
  private final JTextField nickTextField = new JTextField();
  private final JLabel nombreLabel = new JLabel("Nombre");
  private final JTextField nombreTextField = new JTextField();
  private final JLabel apellidoLabel = new JLabel("Apellido");
  private final JTextField apellidoTextField = new JTextField();
  private final JLabel emailLabel = new JLabel("Email");
  private final JTextField emailTextField = new JTextField();
  private final JPanel panel = new JPanel();
  private final JLabel nacimientoLabel = new JLabel("Nacimiento");
  private final JTextField nacimientoTextField = new JTextField();
  private final JLabel nacionalidadLabel = new JLabel("Nacionalidad");
  private final JTextField nacionalidadTextField = new JTextField();
  private final JLabel tipoUsuarioLabel = new JLabel("Tipo de usuario");
  private final JTextField tipoUsuarioTextField = new JTextField();
  private final JPanel panelBotones = new JPanel();
  private final JButton btnAceptar = new JButton("Aceptar");
  private final JButton btnCancelar = new JButton("Cancelar");
  private final Component horizontalGlue = Box.createHorizontalGlue();

  protected VerUsuario(boolean modificable) {
    frame.setClosable(true);
    frame.setResizable(true);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    if (modificable) {
      frame.setTitle("Modificar Usuario");
    } else {
      frame.setTitle("Consultar Usuario");
    }

    tipoUsuarioTextField.setEditable(false);
    tipoUsuarioTextField.setColumns(10);
    nacionalidadTextField.setEditable(modificable);
    nacionalidadTextField.setColumns(10);
    nacimientoTextField.setEditable(modificable);
    nacimientoTextField.setColumns(10);
    frame.setBounds(100, 100, 450, 300);
    frame.getContentPane().setLayout(new BorderLayout(0, 0));

    frame.getContentPane().add(panel);
    panel.setLayout(new GridLayout(0, 2, 0, 5));

    JLabel elegirUsuarioLabel = new JLabel("Elegir Usuario");
    panel.add(elegirUsuarioLabel);
    elegirUsuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
    elegirUsuarioComboBox.addActionListener(new ElegirUsuarioComboBoxListener());
    panel.add(elegirUsuarioComboBox);
    tipoUsuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);

    panel.add(tipoUsuarioLabel);

    panel.add(tipoUsuarioTextField);
    panel.add(nickLabel);
    nickLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(nickTextField);
    nickTextField.setEditable(false);
    nickTextField.setColumns(10);
    panel.add(nombreLabel);
    nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(nombreTextField);
    nombreTextField.setEditable(modificable);
    nombreTextField.setColumns(10);
    panel.add(apellidoLabel);
    apellidoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(apellidoTextField);
    apellidoTextField.setEditable(modificable);
    apellidoTextField.setColumns(10);
    panel.add(emailLabel);
    emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(emailTextField);
    emailTextField.setEditable(false);
    emailTextField.setColumns(10);
    nacimientoLabel.setHorizontalAlignment(SwingConstants.CENTER);

    panel.add(nacimientoLabel);

    panel.add(nacimientoTextField);
    nacionalidadLabel.setHorizontalAlignment(SwingConstants.CENTER);

    panel.add(nacionalidadLabel);

    panel.add(nacionalidadTextField);

    frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

    panelBotones.add(horizontalGlue);

    btnAceptar.setVisible(modificable);
    if (modificable) {
      btnAceptar.addActionListener(new ModificarDatosUsuariosListener());
    }
    panelBotones.add(btnAceptar);

    btnCancelar.addActionListener(new Cancelar());
    btnCancelar.setVisible(modificable);
    panelBotones.add(btnCancelar);

    frame.pack();
  }

  public void actualizar() {
    elegirUsuarioComboBox.removeAllItems();
    IUsuario iusuario = Fabrica.getInstance().getIusuario();
    Iterator<DataUsuario> usuarios = iusuario.listarDataUsuarios().iterator();

    if (usuarios.hasNext()) {
      while (usuarios.hasNext()) {
        elegirUsuarioComboBox.addItem(usuarios.next());
      }
      elegirUsuarioComboBox.setSelectedIndex(0);
    }
  }

  private class Cancelar implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
      frame.setVisible(false);
    }
  }

  private class ModificarDatosUsuariosListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      DataUsuario actual = (DataUsuario) elegirUsuarioComboBox.getSelectedItem();

      if (actual == null) {
        return;
      }

      IUsuario iusuario = Fabrica.getInstance().getIusuario();
      try {
        if (actual instanceof DataPostulante) {
          iusuario.actualizarPostulante(LocalDate.parse(nacimientoTextField.getText()),
              nacionalidadTextField.getText(), nickTextField.getText(), nombreTextField.getText(),
              apellidoTextField.getText(), emailTextField.getText(), null);
        }
        if (actual instanceof DataEmpresa) {
          iusuario.actualizarEmpresa(nacimientoTextField.getText(), nacionalidadTextField.getText(),
              nickTextField.getText(), nombreTextField.getText(), apellidoTextField.getText(),
              emailTextField.getText(), null);
          // TODO: no reutilizar los textfields para empresa, usar distintos
        }

        JOptionPane.showMessageDialog(frame, "Usuario modificado con exito");
        frame.setVisible(false);

      } catch (DateTimeParseException exception) {
        JOptionPane.showMessageDialog(frame, "No se pudo parsear la fecha");
        nacimientoTextField.setText("");
      } catch (NoExistePostulante exception) {
        JOptionPane.showMessageDialog(frame, "No se pudo parsear la fecha");
      } catch (NoExisteEmpresa exception) {
        JOptionPane.showMessageDialog(frame, "No se pudo parsear la fecha");
      }

    }
  }

  private class ElegirUsuarioComboBoxListener implements ActionListener {
    private void usuarioSeleccionado(DataUsuario dataUsuario) {
      nickTextField.setText(dataUsuario.getNickname());
      nombreTextField.setText(dataUsuario.getNombre());
      apellidoTextField.setText(dataUsuario.getApellido());
      emailTextField.setText(dataUsuario.getEmail());
      if (dataUsuario instanceof DataPostulante) {
        tipoUsuarioTextField.setText("Postulante");
        DataPostulante dataPostulante = (DataPostulante) dataUsuario;
        nacimientoLabel.setText("Nacimiento");
        nacimientoTextField.setText(dataPostulante.getNacimiento().toString());
        nacionalidadLabel.setText("Nacionalidad");
        nacionalidadTextField.setText(dataPostulante.getNacionalidad());
      }
      if (dataUsuario instanceof DataEmpresa) {
        tipoUsuarioTextField.setText("Empresa");
        DataEmpresa dataEmpresa = (DataEmpresa) dataUsuario;
        nacimientoLabel.setText("Descripción");
        nacimientoTextField.setText(dataEmpresa.getDescripcion());
        nacionalidadLabel.setText("Sitio Web");
        nacionalidadTextField.setText(dataEmpresa.getSitioWeb());
      }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
      Object item = elegirUsuarioComboBox.getSelectedItem();

      if (item instanceof DataUsuario) {
        usuarioSeleccionado((DataUsuario) item);
      }
    }
  }
}
