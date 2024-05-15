package main.java.presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;

import main.java.logica.datatypes.DataPaquete;
import main.java.logica.datatypes.DataTipoPublicacion;
import main.java.logica.enums.StatusOfertaLaboral;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExisteKeyword;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.excepciones.NoExisteTipoPublicacion;
import main.java.logica.excepciones.NoSuficienteTipoPubliEnPaquete;
import main.java.logica.excepciones.RemuneracionNegativa;
import main.java.logica.excepciones.YaExisteOferta;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;
import main.java.logica.interfaces.IUsuario;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class AltaOfertaLaboral extends JInternalFrame {

  private JInternalFrame frame;
  
  JInternalFrame getFrame() {
    return frame;
  }
  
  private JTextField textFieldRemu;
  private JTextField textFieldNombre;
  private JEditorPane editorPaneDescripcion;
  private JTextField textFieldHorario;
  private JTextField textFieldCiudad;
  private JTextField textFieldDepto;
  private JTextField textFieldFecAlta;
  private DefaultListModel<String> keywordsListModel = new DefaultListModel<String>();
  private JList<String> list;
  private JComboBox<String> comboBoxPaquete = new JComboBox<String>();
  private JRadioButton rdbtnFormaGeneral;
  private JRadioButton rdbtnPaquete;
  private JPanel panelSelPaquete;

  /**
   * Create the frame.
   */
  private JComboBox<String> listarEmpresas = new JComboBox<String>();
  private JComboBox<DataTipoPublicacion> listarTipos = new JComboBox<DataTipoPublicacion>();

  public AltaOfertaLaboral() {
    initialize();
  }

  public void actualizar(){
    listarEmpresas.removeAllItems();
    IUsuario iusuario = Fabrica.getInstance().getIusuario();
    Iterator<String> empresas = iusuario.listarEmpresas().iterator();

    while (empresas.hasNext()) {
      listarEmpresas.addItem(empresas.next());
    }

    listarTipos.removeAllItems();
    IOferta ioferta = Fabrica.getInstance().getIOferta();

    Iterator<DataTipoPublicacion> tiposP = ioferta.listarTipos().iterator();

    while (tiposP.hasNext()) {
      listarTipos.addItem(tiposP.next());
    }

    keywordsListModel.removeAllElements();

    Iterator<String> keywords = ioferta.listarKeywords().iterator();

    while (keywords.hasNext()) {
      keywordsListModel.addElement(keywords.next());
    }
    
    try {
		actualizarPaq();
	} catch (NoExistePaquete e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  
  public void actualizarPaq() throws NoExistePaquete {
	  comboBoxPaquete.removeAllItems();
	  IUsuario iusuario = Fabrica.getInstance().getIusuario();
		if (listarEmpresas.getSelectedIndex() != -1) {
			Set<DataPaquete> listaPaq;
			try {
				if (listarTipos.getSelectedIndex() != -1) {
					listaPaq = iusuario.listarPaquetesDisponiblesDeEmpresa(listarEmpresas.getSelectedItem().toString(), listarTipos.getSelectedItem().toString());
					Iterator<DataPaquete> iter = listaPaq.iterator();

					if (!listaPaq.isEmpty()) {
						while (iter.hasNext()) {
							String nomPaq = iter.next().getNombre();
							comboBoxPaquete.addItem(nomPaq);
						}
						comboBoxPaquete.setSelectedIndex(0);
					}
				}
				
			} catch (NoExisteEmpresa e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
  }
  
  private void initialize() {

    frame = new JInternalFrame();
    frame.setClosable(true);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.setBounds(100, 100, 550, 394);
    frame.setTitle("Alta Oferta Laboral");
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
    gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0,
        0.0, 1.0, Double.MIN_VALUE };
    frame.getContentPane().setLayout(gridBagLayout);

    JLabel LabelEmpresa = new JLabel("Empresas");
    GridBagConstraints gbcLabelEmpresa = new GridBagConstraints();
    gbcLabelEmpresa.insets = new Insets(0, 0, 5, 5);
    gbcLabelEmpresa.anchor = GridBagConstraints.EAST;
    gbcLabelEmpresa.gridx = 0;
    gbcLabelEmpresa.gridy = 0;
    frame.getContentPane().add(LabelEmpresa, gbcLabelEmpresa);

    // JComboBox listarEmpresas = new JComboBox();
    GridBagConstraints gbclistarEmpresas = new GridBagConstraints();
    gbclistarEmpresas.insets = new Insets(0, 0, 5, 0);
    gbclistarEmpresas.fill = GridBagConstraints.HORIZONTAL;
    gbclistarEmpresas.gridx = 2;
    gbclistarEmpresas.gridy = 0;
    frame.getContentPane().add(listarEmpresas, gbclistarEmpresas);
    // ---------------------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------------------
    JLabel LabelTipoPub = new JLabel("Tipos Publicacion");
    GridBagConstraints gbcLabelTipoPub = new GridBagConstraints();
    gbcLabelTipoPub.anchor = GridBagConstraints.EAST;
    gbcLabelTipoPub.insets = new Insets(0, 0, 5, 5);
    gbcLabelTipoPub.gridx = 0;
    gbcLabelTipoPub.gridy = 1;
    frame.getContentPane().add(LabelTipoPub, gbcLabelTipoPub);

    GridBagConstraints gbclistarTipos = new GridBagConstraints();
    gbclistarTipos.insets = new Insets(0, 0, 5, 0);
    gbclistarTipos.fill = GridBagConstraints.HORIZONTAL;
    gbclistarTipos.gridx = 2;
    gbclistarTipos.gridy = 1;
    frame.getContentPane().add(listarTipos, gbclistarTipos);

    JLabel LabelNombre = new JLabel("Nombre");
    GridBagConstraints gbcLabelNombre = new GridBagConstraints();
    gbcLabelNombre.anchor = GridBagConstraints.EAST;
    gbcLabelNombre.insets = new Insets(0, 0, 5, 5);
    gbcLabelNombre.gridx = 0;
    gbcLabelNombre.gridy = 2;
    frame.getContentPane().add(LabelNombre, gbcLabelNombre);

    textFieldNombre = new JTextField();
    GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
    gbctextFieldNombre.insets = new Insets(0, 0, 5, 0);
    gbctextFieldNombre.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldNombre.gridx = 2;
    gbctextFieldNombre.gridy = 2;
    frame.getContentPane().add(textFieldNombre, gbctextFieldNombre);
    textFieldNombre.setColumns(10);

    JLabel LabelDesc = new JLabel("Descripcion");
    GridBagConstraints gbcLabelDesc = new GridBagConstraints();
    gbcLabelDesc.anchor = GridBagConstraints.EAST;
    gbcLabelDesc.insets = new Insets(0, 0, 5, 5);
    gbcLabelDesc.gridx = 0;
    gbcLabelDesc.gridy = 3;
    frame.getContentPane().add(LabelDesc, gbcLabelDesc);

    editorPaneDescripcion = new JEditorPane();
    GridBagConstraints gbceditorPaneDescripcion = new GridBagConstraints();
    gbceditorPaneDescripcion.insets = new Insets(0, 0, 5, 0);
    gbceditorPaneDescripcion.fill = GridBagConstraints.BOTH;
    gbceditorPaneDescripcion.gridx = 2;
    gbceditorPaneDescripcion.gridy = 3;
    frame.getContentPane().add(editorPaneDescripcion, gbceditorPaneDescripcion);

    JLabel LabelHorario = new JLabel("Horario");
    GridBagConstraints gbcLabelHorario = new GridBagConstraints();
    gbcLabelHorario.anchor = GridBagConstraints.SOUTHEAST;
    gbcLabelHorario.insets = new Insets(0, 0, 5, 5);
    gbcLabelHorario.gridx = 0;
    gbcLabelHorario.gridy = 4;
    frame.getContentPane().add(LabelHorario, gbcLabelHorario);

    textFieldHorario = new JTextField();
    GridBagConstraints gbctextFieldHorario = new GridBagConstraints();
    gbctextFieldHorario.insets = new Insets(0, 0, 5, 0);
    gbctextFieldHorario.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldHorario.gridx = 2;
    gbctextFieldHorario.gridy = 4;
    frame.getContentPane().add(textFieldHorario, gbctextFieldHorario);
    textFieldHorario.setColumns(10);

    JLabel LabelRemu = new JLabel("Remuneracion");
    GridBagConstraints gbcLabelRemu = new GridBagConstraints();
    gbcLabelRemu.insets = new Insets(0, 0, 5, 5);
    gbcLabelRemu.anchor = GridBagConstraints.EAST;
    gbcLabelRemu.gridx = 0;
    gbcLabelRemu.gridy = 5;
    frame.getContentPane().add(LabelRemu, gbcLabelRemu);

    textFieldRemu = new JTextField();
    textFieldRemu.setText("");
    GridBagConstraints gbctextFieldRemu = new GridBagConstraints();
    gbctextFieldRemu.insets = new Insets(0, 0, 5, 0);
    gbctextFieldRemu.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldRemu.gridx = 2;
    gbctextFieldRemu.gridy = 5;
    frame.getContentPane().add(textFieldRemu, gbctextFieldRemu);
    textFieldRemu.setColumns(10);

    JLabel LabelCiudad = new JLabel("Ciudad");
    GridBagConstraints gbcLabelCiudad = new GridBagConstraints();
    gbcLabelCiudad.anchor = GridBagConstraints.EAST;
    gbcLabelCiudad.insets = new Insets(0, 0, 5, 5);
    gbcLabelCiudad.gridx = 0;
    gbcLabelCiudad.gridy = 6;
    frame.getContentPane().add(LabelCiudad, gbcLabelCiudad);

    textFieldCiudad = new JTextField();
    GridBagConstraints gbctextFieldCiudad = new GridBagConstraints();
    gbctextFieldCiudad.insets = new Insets(0, 0, 5, 0);
    gbctextFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldCiudad.gridx = 2;
    gbctextFieldCiudad.gridy = 6;
    frame.getContentPane().add(textFieldCiudad, gbctextFieldCiudad);
    textFieldCiudad.setColumns(10);

    JLabel LabelDepto = new JLabel("Departamento");
    GridBagConstraints gbcLabelDepto = new GridBagConstraints();
    gbcLabelDepto.anchor = GridBagConstraints.EAST;
    gbcLabelDepto.insets = new Insets(0, 0, 5, 5);
    gbcLabelDepto.gridx = 0;
    gbcLabelDepto.gridy = 7;
    frame.getContentPane().add(LabelDepto, gbcLabelDepto);

    textFieldDepto = new JTextField();
    textFieldDepto.setText("");
    GridBagConstraints gbctextFieldDepto = new GridBagConstraints();
    gbctextFieldDepto.insets = new Insets(0, 0, 5, 0);
    gbctextFieldDepto.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldDepto.gridx = 2;
    gbctextFieldDepto.gridy = 7;
    frame.getContentPane().add(textFieldDepto, gbctextFieldDepto);
    textFieldDepto.setColumns(10);

    JLabel LabelFecAlta = new JLabel("Fecha alta");
    GridBagConstraints gbcLabelFecAlta = new GridBagConstraints();
    gbcLabelFecAlta.anchor = GridBagConstraints.EAST;
    gbcLabelFecAlta.insets = new Insets(0, 0, 5, 5);
    gbcLabelFecAlta.gridx = 0;
    gbcLabelFecAlta.gridy = 8;
    frame.getContentPane().add(LabelFecAlta, gbcLabelFecAlta);

    textFieldFecAlta = new JTextField();
    GridBagConstraints gbctextFieldFecAlta = new GridBagConstraints();
    gbctextFieldFecAlta.insets = new Insets(0, 0, 5, 0);
    gbctextFieldFecAlta.fill = GridBagConstraints.HORIZONTAL;
    gbctextFieldFecAlta.gridx = 2;
    gbctextFieldFecAlta.gridy = 8;
    frame.getContentPane().add(textFieldFecAlta, gbctextFieldFecAlta);
    textFieldFecAlta.setColumns(10);

    JLabel LabelKeywords = new JLabel("Keywords");
    GridBagConstraints gbcLabelKeywords = new GridBagConstraints();
    gbcLabelKeywords.insets = new Insets(0, 0, 5, 5);
    gbcLabelKeywords.gridx = 0;
    gbcLabelKeywords.gridy = 9;
    frame.getContentPane().add(LabelKeywords, gbcLabelKeywords);

    list = new JList<String>();
    list.setModel(keywordsListModel);
    GridBagConstraints gbclist = new GridBagConstraints();
    gbclist.gridheight = 2;
    gbclist.insets = new Insets(0, 0, 5, 0);
    gbclist.fill = GridBagConstraints.BOTH;
    gbclist.gridx = 2;
    gbclist.gridy = 9;
    frame.getContentPane().add(new JScrollPane(list), gbclist);
    
    JLabel lblPago = new JLabel("Forma de pago:");
    GridBagConstraints gbc_lblPago = new GridBagConstraints();
    gbc_lblPago.insets = new Insets(0, 0, 5, 5);
    gbc_lblPago.gridx = 0;
    gbc_lblPago.gridy = 11;
    frame.getContentPane().add(lblPago, gbc_lblPago);
    
    JPanel panelPago = new JPanel();
    GridBagConstraints gbc_panelPago = new GridBagConstraints();
    gbc_panelPago.insets = new Insets(0, 0, 5, 0);
    gbc_panelPago.fill = GridBagConstraints.BOTH;
    gbc_panelPago.gridx = 2;
    gbc_panelPago.gridy = 11;
    frame.getContentPane().add(panelPago, gbc_panelPago);
    
    listarTipos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			try {
				actualizarPaq();
			} catch (NoExistePaquete e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
    
	listarEmpresas.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			
			// Llenar combo paquetes
			try {
				actualizarPaq();
			} catch (NoExistePaquete e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	});
    
    rdbtnFormaGeneral = new JRadioButton("Forma general");
    rdbtnFormaGeneral.setSelected(false);
    panelPago.add(rdbtnFormaGeneral);
    rdbtnFormaGeneral.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
        	rdbtnPaquete.setSelected(!rdbtnFormaGeneral.isSelected());
        	if (rdbtnPaquete.isSelected()) {
        		panelSelPaquete.setVisible(true);
        	}else {
        		panelSelPaquete.setVisible(false);
        	}
        	
        }
      });
    
    rdbtnPaquete = new JRadioButton("Con paquete");
    rdbtnPaquete.setSelected(true);
    panelPago.add(rdbtnPaquete);
    rdbtnPaquete.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
        	rdbtnFormaGeneral.setSelected(!rdbtnPaquete.isSelected());
        	if (rdbtnPaquete.isSelected()) {
        		panelSelPaquete.setVisible(true);
        	}else {
        		panelSelPaquete.setVisible(false);
        	}
        	
        }
      });
    
    panelSelPaquete = new JPanel();
    panelSelPaquete.setVisible(true);
    GridBagConstraints gbc_panelSelPaquete = new GridBagConstraints();
    gbc_panelSelPaquete.insets = new Insets(0, 0, 5, 0);
    gbc_panelSelPaquete.fill = GridBagConstraints.BOTH;
    gbc_panelSelPaquete.gridx = 2;
    gbc_panelSelPaquete.gridy = 12;
    frame.getContentPane().add(panelSelPaquete, gbc_panelSelPaquete);
    

    panelSelPaquete.add(comboBoxPaquete);

    JPanel panel = new JPanel();
    GridBagConstraints gbcpanel = new GridBagConstraints();
    gbcpanel.insets = new Insets(0, 0, 5, 0);
    gbcpanel.fill = GridBagConstraints.BOTH;
    gbcpanel.gridx = 2;
    gbcpanel.gridy = 13;
    frame.getContentPane().add(panel, gbcpanel);

    JButton ButtonAceptar = new JButton("Aceptar");
    ButtonAceptar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
      }
    });
    panel.add(ButtonAceptar);

    JButton ButtonCancelar = new JButton("Cancelar");
    panel.add(ButtonCancelar);

    ButtonAceptar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        String nombre = textFieldNombre.getText();
        String desc = editorPaneDescripcion.getText();
        String horario = textFieldHorario.getText();
        String remuneracionString = textFieldRemu.getText();
        String ciudad = textFieldCiudad.getText();
        String depto = textFieldDepto.getText();
        String fecAltaString = textFieldFecAlta.getText();

        int[] keywordsIndeces = list.getSelectedIndices();

        Set<Integer> indeces = Arrays.stream(keywordsIndeces).boxed().collect(Collectors.toSet());

        Iterator<Integer> iter = indeces.iterator();
        Set<String> keywordsSeleccionadas = new HashSet<String>();

        while (iter.hasNext()) {
          keywordsSeleccionadas.add(keywordsListModel.get(iter.next()));
        }

        if (nombre.isBlank() || (desc.isBlank()) || (horario.isBlank())
            || (remuneracionString.isBlank()) || (ciudad.isBlank()) || (depto.isBlank())
            || (fecAltaString.isBlank())) {
          JOptionPane.showMessageDialog(getContentPane(), "Faltan datos", "Alta de Usuario",
              JOptionPane.INFORMATION_MESSAGE);
          return;
        } else {
          IOferta ioferta = Fabrica.getInstance().getIOferta();

          String tipo = (String) listarTipos.getSelectedItem().toString();
          String empresa = (String) listarEmpresas.getSelectedItem();

          try {
            float remuneracion = Float.parseFloat(remuneracionString);

            LocalDate fecAlta = LocalDate.parse(fecAltaString);

            StatusOfertaLaboral stat = StatusOfertaLaboral.Ingresada;
            if (rdbtnPaquete.isSelected()) {
            	ioferta.insertarDatosOferta(nombre, desc, horario, remuneracion, ciudad, depto, fecAlta,
                        keywordsSeleccionadas, tipo, empresa, stat, "", comboBoxPaquete.getSelectedItem().toString());
            }else {
            	ioferta.insertarDatosOferta(nombre, desc, horario, remuneracion, ciudad, depto, fecAlta,
                        keywordsSeleccionadas, tipo, empresa, stat, "", null);
            }
            

            JOptionPane.showMessageDialog(getContentPane(), "Exito", "Alta de Usuario",
                JOptionPane.INFORMATION_MESSAGE);
            borrarCampos();
            frame.setVisible(false);
          } catch (DateTimeParseException exception) {
            JOptionPane.showMessageDialog(getContentPane(), "No se pudo parsear la fecha",
                "Alta de Usuario", JOptionPane.ERROR_MESSAGE);
          } catch (NoExisteTipoPublicacion exception) {
            JOptionPane.showMessageDialog(getContentPane(),
                "No existe el tipo de publicacion seleccionado", "Alta de Usuario",
                JOptionPane.ERROR_MESSAGE);
          } catch (YaExisteOferta excepcion) {
            JOptionPane.showMessageDialog(getContentPane(),
                "Ya existe oferta con nombre `" + nombre + "`", "Alta de Usuario",
                JOptionPane.ERROR_MESSAGE);
          } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(getContentPane(), "No se pudo parsear la remuneracion",
                "Alta de Usuario", JOptionPane.ERROR_MESSAGE);
          } catch (NoExisteKeyword e1) {
            JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "No existe keyword",
                JOptionPane.ERROR_MESSAGE);
          } catch (NoExisteEmpresa e1) {
            JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "No existe keyword",
                JOptionPane.ERROR_MESSAGE);
          } catch (RemuneracionNegativa e1) {
            JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), e1.getMessage(),
                JOptionPane.ERROR_MESSAGE);
          } catch (NoExistePaquete e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuficienteTipoPubliEnPaquete e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
      }

      private Component getContentPane() {
        // TODO Auto-generated method stub
        return null;
      }
    });

    ButtonCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        // dispose();
        frame.setVisible(false);
        borrarCampos();
      }
    });

    frame.pack();
  }

  private void borrarCampos() {
    // If the text is null or empty, has the effect of simply deleting the old text.
    textFieldNombre.setText(null);
    editorPaneDescripcion.setText(null);
    textFieldHorario.setText(null);
    textFieldRemu.setText(null);
    textFieldCiudad.setText(null);
    textFieldDepto.setText(null);
    textFieldFecAlta.setText(null);
  }
  

}
