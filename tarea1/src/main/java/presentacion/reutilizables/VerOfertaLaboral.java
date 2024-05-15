package main.java.presentacion.reutilizables;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.logica.datatypes.DataEmpresa;
import main.java.logica.datatypes.DataOfertaLaboral;
import main.java.logica.datatypes.DataUsuario;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExisteOferta;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;
import main.java.logica.interfaces.IUsuario;

@SuppressWarnings("serial")
public class VerOfertaLaboral extends JPanel {

  private JInternalFrame frame;
  private JEditorPane descripcionEditorPane;
  private JTextField nombreTextField;
  private JTextField horarioTextField;
  private JTextField remuneracionTextField;
  private JTextField ciudadTextField;
  private JTextField departamentoTextField;
  private JTextField fecAltaTextField;
  private JComboBox<DataEmpresa> empresaComboBox;
  private JComboBox<DataOfertaLaboral> ofertaComboBox;
  private JList<String> keywordsList;
  private DefaultListModel<String> keywordsListModel = new DefaultListModel<String>();
  private JList<String> postulacionesList;
  private DefaultListModel<String> postulantesListModel = new DefaultListModel<String>();
  private JTextField textFieldEstado;
  private JTextField textFieldPago;

  public DataEmpresa getEmpresaSleccionada() {
    return (DataEmpresa) empresaComboBox.getSelectedItem();
  }

  public DataOfertaLaboral getOfertaSeleccionada() {
    return (DataOfertaLaboral) ofertaComboBox.getSelectedItem();
  }

  /**
   * Create the panel.
   */
  public VerOfertaLaboral(JInternalFrame frame) {
    this.frame = frame;
    setBounds(100, 100, 450, 500);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    JPanel panel = new JPanel();
    this.add(panel);
    panel.setLayout(new GridLayout(0, 2, 0, 4));

    JLabel empresaLabel = new JLabel("Empresa");
    empresaLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(empresaLabel);

    empresaComboBox = new JComboBox<DataEmpresa>();
    empresaComboBox.addActionListener(new CargarOfertasDeEmpresaListener());
    panel.add(empresaComboBox);

    JLabel ofertaLabel = new JLabel("Oferta Laboral");
    ofertaLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(ofertaLabel);

    ofertaComboBox = new JComboBox<DataOfertaLaboral>();
    ofertaComboBox.addActionListener(new CargarOfertaLaboralListener());
    panel.add(ofertaComboBox);

    JLabel nomrbeLabel = new JLabel("Nombre");
    nomrbeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(nomrbeLabel);

    nombreTextField = new JTextField();
    nombreTextField.setEditable(false);
    panel.add(nombreTextField);
    nombreTextField.setColumns(10);

    JLabel descripcionLabel = new JLabel("Descripcion");
    descripcionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(descripcionLabel);

    descripcionEditorPane = new JEditorPane();
    descripcionEditorPane.setEditable(false);
    panel.add(descripcionEditorPane);

    JLabel horarioLabel = new JLabel("Horario");
    horarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(horarioLabel);

    horarioTextField = new JTextField();
    horarioTextField.setEditable(false);
    panel.add(horarioTextField);
    horarioTextField.setColumns(10);

    JLabel remuneracionLabel = new JLabel("Remuneracion");
    remuneracionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(remuneracionLabel);

    remuneracionTextField = new JTextField();
    remuneracionTextField.setEditable(false);
    panel.add(remuneracionTextField);
    remuneracionTextField.setColumns(10);

    JLabel ciudadLabel = new JLabel("Ciudad");
    ciudadLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(ciudadLabel);

    ciudadTextField = new JTextField();
    ciudadTextField.setEditable(false);
    panel.add(ciudadTextField);
    ciudadTextField.setColumns(10);

    JLabel deparatamentoLabel = new JLabel("Departamento");
    deparatamentoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(deparatamentoLabel);

    departamentoTextField = new JTextField();
    departamentoTextField.setEditable(false);
    panel.add(departamentoTextField);
    departamentoTextField.setColumns(10);

    JLabel fecAltaLabel = new JLabel("Fecha de Alta");
    fecAltaLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(fecAltaLabel);

    fecAltaTextField = new JTextField();
    panel.add(fecAltaTextField);
    fecAltaTextField.setColumns(10);

    JLabel keywordsLabel = new JLabel("Keywords");
    keywordsLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(keywordsLabel);

    keywordsList = new JList<String>();
    keywordsList.setModel(keywordsListModel);
    panel.add(new JScrollPane(keywordsList));

    JLabel postulacionesLabel = new JLabel("Postulaciones");
    postulacionesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(postulacionesLabel);

    postulacionesList = new JList<String>();
    postulacionesList.setModel(postulantesListModel);
    panel.add(new JScrollPane(postulacionesList));
    
    JLabel lblStatus = new JLabel("Estado:");
    lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblStatus);
    
    textFieldEstado = new JTextField();
    textFieldEstado.setEditable(false);
    panel.add(textFieldEstado);
    textFieldEstado.setColumns(10);
    
    JLabel lblPago = new JLabel("Pago:");
    lblPago.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblPago);
    
    textFieldPago = new JTextField();
    textFieldPago.setEditable(false);
    panel.add(textFieldPago);
    textFieldPago.setColumns(10);

    JPanel panelBtn = new JPanel();
    this.add(panelBtn);
    panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.X_AXIS));

    actualizar();
  }

  public void actualizar() {
    empresaComboBox.removeAllItems();
    IUsuario iusuario = Fabrica.getInstance().getIusuario();
    Iterator<DataUsuario> usuarios = iusuario.listarDataUsuarios().iterator();

    if (usuarios.hasNext()) {
      while (usuarios.hasNext()) {
        DataUsuario data = usuarios.next();

        if (data instanceof DataEmpresa) {
          empresaComboBox.addItem((DataEmpresa) data);
        }
      }
      empresaComboBox.setSelectedIndex(0);
    }
  }

  private class CargarOfertasDeEmpresaListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      ofertaComboBox.removeAllItems();

      nombreTextField.setText("");
      descripcionEditorPane.setText("");
      horarioTextField.setText("");
      remuneracionTextField.setText("");
      ciudadTextField.setText("");
      departamentoTextField.setText("");
      fecAltaTextField.setText("");
      textFieldEstado.setText("");
      textFieldPago.setText("");

      DataEmpresa data = (DataEmpresa) empresaComboBox.getSelectedItem();

      if (data == null) {
        return;
      }

      IUsuario iusuario = Fabrica.getInstance().getIusuario();

      Iterator<DataOfertaLaboral> ofertas;
      try {
        ofertas = iusuario.listarOfertasLaborales(data.getNickname(), false).iterator();
      } catch (NoExisteEmpresa exception) {
        JOptionPane.showMessageDialog(frame, exception.getMessage());
        return;
      }

      if (ofertas.hasNext()) {
        while (ofertas.hasNext()) {
          ofertaComboBox.addItem(ofertas.next());
        }
        ofertaComboBox.setSelectedIndex(0);
      }
    }
  }

  private class CargarOfertaLaboralListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      DataOfertaLaboral dol = (DataOfertaLaboral) ofertaComboBox.getSelectedItem();

      keywordsListModel.removeAllElements();

      postulantesListModel.removeAllElements();

      if (dol == null) {
        nombreTextField.setText("");
        ;
        descripcionEditorPane.setText("");
        ;
        horarioTextField.setText("");
        remuneracionTextField.setText("");
        ciudadTextField.setText("");
        departamentoTextField.setText("");
        fecAltaTextField.setText("");
        textFieldEstado.setText("");
        textFieldPago.setText("");

        return;
      }
      nombreTextField.setText(dol.getNombre());
      descripcionEditorPane.setText(dol.getDescOL());
      horarioTextField.setText(dol.getHorario());
      remuneracionTextField.setText(((Float) dol.getRemu()).toString());
      ciudadTextField.setText(dol.getCiudad());
      departamentoTextField.setText(dol.getDepartamento());
      fecAltaTextField.setText(dol.getFecAlta().toString());
      textFieldEstado.setText(dol.getStatus().toString());
      if (dol.getPaquetePago() == null) {
    	  textFieldPago.setText("Forma general");
      }else {
    	  textFieldPago.setText(dol.getPaquetePago());
      }
      

      Iterator<String> keywords = dol.getKeyw().iterator();

      while (keywords.hasNext()) {
        keywordsListModel.addElement(keywords.next());
      }

      IOferta ioferta = Fabrica.getInstance().getIOferta();

      try {
        Iterator<String> postulantes = ioferta.listarPostulantesDeOferta(dol.getNombre()).iterator();

        while (postulantes.hasNext()) {
          postulantesListModel.addElement(postulantes.next());
        }
      } catch (NoExisteOferta exception) {
        JOptionPane.showMessageDialog(frame, exception.getMessage());
      }
    }
  }

}
