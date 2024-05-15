package main.java.presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IUsuario;
import main.java.logica.datatypes.DataEmpresa;
import main.java.logica.datatypes.DataPostulante;
import main.java.logica.excepciones.YaExisteUsuario;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.Action;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;

public class AltaUsuario {

  private JInternalFrame frame;
  
  public JInternalFrame getFrame() {
    return frame;
  }
  
  private JTextField nickTextField;
  private JTextField nombreTextField;
  private JTextField apellidoTextField;
  private JTextField emailTextField;
  private JPanel ingresoUsuario;
  private JPanel panel;
  private JPanel panelBtns;
  private JButton btnAceptar;
  private JButton btnCancelar;
  private JPanel panelIngreso;
  private final Action aceptar = new AceptarAltaUsuario();
  private final Action cancelar = new CancelarAltaUsuario();
  // ------
  private JPanel tipoUsuario;
  private JLabel nacODescLabel;
  private JLabel tipoUsuarioLabel;
  private JLabel nacOSitioLabel;
  private JComboBox<String> tipoUsuarioComboBox;
  private JTextField nacODescTextField;
  private JTextField nacOSitioTextField;
  private JPasswordField passField;
  private JPasswordField passField2;
  private JLabel lblConfirmPass;


  public AltaUsuario() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JInternalFrame();
    frame.setTitle("Alta de Usuario");
    frame.setClosable(true);
    frame.setResizable(true);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

    panel = new JPanel();
    frame.getContentPane().add(panel);
    panel.setLayout(new BorderLayout(0, 0));

    panelIngreso = new JPanel();
    panel.add(panelIngreso, BorderLayout.CENTER);
    panelIngreso.setLayout(new BoxLayout(panelIngreso, BoxLayout.Y_AXIS));

    ingresoUsuario = new JPanel();
    panelIngreso.add(ingresoUsuario);
    ingresoUsuario.setLayout(new GridLayout(0, 2, 0, 0));

    JLabel nickLabel = new JLabel("Nick");
    ingresoUsuario.add(nickLabel);
    nickLabel.setHorizontalAlignment(SwingConstants.CENTER);

    nickTextField = new JTextField();
    ingresoUsuario.add(nickTextField);
    nickTextField.setColumns(10);

    JLabel nombreLabel = new JLabel("Nombre");
    ingresoUsuario.add(nombreLabel);
    nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);

    nombreTextField = new JTextField();
    ingresoUsuario.add(nombreTextField);
    nombreTextField.setColumns(10);

    JLabel apellidoLabel = new JLabel("Apellido");
    ingresoUsuario.add(apellidoLabel);
    apellidoLabel.setHorizontalAlignment(SwingConstants.CENTER);

    apellidoTextField = new JTextField();
    ingresoUsuario.add(apellidoTextField);
    apellidoTextField.setColumns(10);

    JLabel emailLabel = new JLabel("Email");
    ingresoUsuario.add(emailLabel);
    emailLabel.setHorizontalAlignment(SwingConstants.CENTER);

    emailTextField = new JTextField();
    ingresoUsuario.add(emailTextField);
    emailTextField.setColumns(10);
    
    JLabel passwordLabel = new JLabel("Contraseña");
    passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
    ingresoUsuario.add(passwordLabel);
    emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
    passField = new JPasswordField();
    ingresoUsuario.add(passField);
    
    lblConfirmPass = new JLabel("Confirmar contraseña");
    lblConfirmPass.setHorizontalAlignment(SwingConstants.CENTER);
    ingresoUsuario.add(lblConfirmPass);
    
    passField2 = new JPasswordField();
    ingresoUsuario.add(passField2);

    tipoUsuario = new JPanel();
    panelIngreso.add(tipoUsuario);
    tipoUsuario.setLayout(new GridLayout(0, 2, 0, 0));

    tipoUsuarioLabel = new JLabel("Tipo de Usuario");
    tipoUsuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
    tipoUsuario.add(tipoUsuarioLabel);

    tipoUsuarioComboBox = new JComboBox<String>();
    tipoUsuarioComboBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (tipoUsuarioComboBox.getSelectedItem() == "Postulante") {
          nacOSitioLabel.setText("Nacimiento (yyyy-mm-dd)");
          nacODescLabel.setText("Nacionalidad");
        } else {
          nacOSitioLabel.setText("Sitio Web");
          nacODescLabel.setText("Descripción");
        }
        nacOSitioTextField.setText(null);
        nacODescTextField.setText(null);
      }
    });
    tipoUsuarioComboBox
        .setModel(new DefaultComboBoxModel<String>(new String[] { "Postulante", "Empresa" }));
    tipoUsuario.add(tipoUsuarioComboBox);

    nacOSitioLabel = new JLabel("Nacimiento (yyyy-mm-dd)");
    nacOSitioLabel.setHorizontalAlignment(SwingConstants.CENTER);
    tipoUsuario.add(nacOSitioLabel);

    nacOSitioTextField = new JTextField();
    nacOSitioTextField.setColumns(10);
    tipoUsuario.add(nacOSitioTextField);

    nacODescLabel = new JLabel("Nacionalidad");
    nacODescLabel.setHorizontalAlignment(SwingConstants.CENTER);
    tipoUsuario.add(nacODescLabel);

    nacODescTextField = new JTextField();
    tipoUsuario.add(nacODescTextField);
    nacODescTextField.setColumns(10);

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

  private class AceptarAltaUsuario extends AbstractAction {
    private static final long serialVersionUID = 1L;

    public AceptarAltaUsuario() {
      putValue(NAME, "Aceptar");
      putValue(SHORT_DESCRIPTION, "Some short description");
    }

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent event) {
		IUsuario iusuario = Fabrica.getInstance().getIusuario();
		if (nickTextField.getText().isBlank() || nombreTextField.getText().isBlank()
				|| apellidoTextField.getText().isBlank() || emailTextField.getText().isBlank()
				|| nacOSitioTextField.getText().isBlank() || nacODescTextField.getText().isBlank()
				|| passField.getText().toString().isEmpty() || passField2.getText().toString().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "No pueden haber campos vacios");
		}else {
			if (new String(passField.getPassword()).equals(new String(passField2.getPassword()))) {
				try {
					if (tipoUsuarioComboBox.getSelectedItem() == "Postulante") {
						DataPostulante data = new DataPostulante(nickTextField.getText(), nombreTextField.getText(),
								apellidoTextField.getText(), emailTextField.getText(),
								LocalDate.parse(nacOSitioTextField.getText()), nacODescTextField.getText(), null);
						iusuario.alta(data, passField.getPassword().toString());
					}
					if (tipoUsuarioComboBox.getSelectedItem() == "Empresa") {
						DataEmpresa data = new DataEmpresa(nickTextField.getText(), nombreTextField.getText(),
								apellidoTextField.getText(), emailTextField.getText(), nacODescTextField.getText(),
								nacOSitioTextField.getText(), null);

						iusuario.alta(data, passField.getPassword().toString());
					}
					JOptionPane.showMessageDialog(frame, "Usuario `" + nickTextField.getText() + "` creado");
					borrarCampos();
					frame.setVisible(false);

				} catch (DateTimeParseException exception) {
					JOptionPane.showMessageDialog(frame, "No se pudo parsear la fecha");
					nacOSitioTextField.setText("");
				} catch (YaExisteUsuario exception) {
					JOptionPane.showMessageDialog(frame, exception.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden");
				passField.setText("");
				passField2.setText("");

			}
		}
	}
}

  private class CancelarAltaUsuario extends AbstractAction {
    private static final long serialVersionUID = 1L;

    public CancelarAltaUsuario() {
      putValue(NAME, "Cancelar");
      putValue(SHORT_DESCRIPTION, "Cancelar el caso de uso");
    }

    public void actionPerformed(ActionEvent event) {
      frame.setVisible(false);
      borrarCampos();
    }
  }

  private void borrarCampos() {
    // If the text is null or empty, has the effect of simply deleting the old text.
    nickTextField.setText(null);
    nombreTextField.setText(null);
    apellidoTextField.setText(null);
    emailTextField.setText(null);
    nacOSitioTextField.setText(null);
    nacODescTextField.setText(null);
    passField.setText(null);
    passField2.setText(null);    

  }
}
