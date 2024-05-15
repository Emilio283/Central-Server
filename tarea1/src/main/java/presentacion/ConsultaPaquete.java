package main.java.presentacion;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.logica.datatypes.DataPaquete;
import main.java.logica.datatypes.DataTipoPublicacion;
import main.java.logica.datatypes.DataTipoPublicacionPaquete;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;

@SuppressWarnings("serial")
public class ConsultaPaquete extends JInternalFrame {
  private JComboBox<DataPaquete> paqueteComboBox;
  private JComboBox<DataTipoPublicacion> tiposComboBox;

  private JTextField nombreTextField;
  private JTextField duracionTextField;
  private JEditorPane descripcionPEditorPane;
  private JTextField descuentoTextField;
  private JTextField costoTextField;
  private JTextField nombreTipoTextField;
  private JTextField exposicionTextField;
  private JTextField duracionTipoTextField;
  private JTextField costoTipoTextField;
  private JTextField fecAltaTextField;
  private JTextField fechaAltaTextField;
  private JEditorPane descripcionTPEditorPane;

  /**
   * Create the frame.
   */
  public ConsultaPaquete() {
    setClosable(true);
    setResizable(true);
    setTitle("Consulta de Paquete de Tipos de Publicacion de Ofertas Laborales");

    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    setBounds(100, 100, 500, 366);
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    JPanel panel = new JPanel();
    getContentPane().add(panel);
    panel.setLayout(new GridLayout(0, 2, 0, 4));

    JLabel paqueteLabel = new JLabel("Paquete");
    paqueteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(paqueteLabel);

    paqueteComboBox = new JComboBox<DataPaquete>();
    panel.add(paqueteComboBox);

    JLabel nombreLabel = new JLabel("Nombre");
    nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(nombreLabel);

    nombreTextField = new JTextField();
    nombreTextField.setEditable(false);
    panel.add(nombreTextField);
    nombreTextField.setColumns(10);

    JLabel descripcionLabel = new JLabel("Descripcion");
    descripcionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(descripcionLabel);

    descripcionPEditorPane = new JEditorPane();
    descripcionPEditorPane.setEditable(false);
    panel.add(descripcionPEditorPane);

    JLabel duracionLabel = new JLabel("Duracion");
    duracionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(duracionLabel);

    duracionTextField = new JTextField();
    duracionTextField.setEditable(false);
    panel.add(duracionTextField);
    duracionTextField.setColumns(10);

    JLabel descuentoLabel = new JLabel("Descuento");
    descuentoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(descuentoLabel);

    descuentoTextField = new JTextField();
    descuentoTextField.setEditable(false);
    panel.add(descuentoTextField);
    descuentoTextField.setColumns(10);

    JLabel costoLabel = new JLabel("Costo");
    costoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(costoLabel);

    costoTextField = new JTextField();
    costoTextField.setEditable(false);
    panel.add(costoTextField);
    costoTextField.setColumns(10);

    JLabel lblNewLabel = new JLabel("Fecha alta");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblNewLabel);

    fechaAltaTextField = new JTextField();
    fechaAltaTextField.setEditable(false);
    panel.add(fechaAltaTextField);
    fechaAltaTextField.setColumns(10);

    JLabel tiposLabel = new JLabel("Tipo de Publicacion");
    tiposLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(tiposLabel);

    tiposComboBox = new JComboBox<DataTipoPublicacion>();
    panel.add(tiposComboBox);

    JPanel panelTipos = new JPanel();
    getContentPane().add(panelTipos);
    panelTipos.setLayout(new GridLayout(0, 2, 0, 0));

    JLabel nombreTipoLabel = new JLabel("Nombre");
    nombreTipoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelTipos.add(nombreTipoLabel);

    nombreTipoTextField = new JTextField();
    nombreTipoTextField.setEditable(false);
    panelTipos.add(nombreTipoTextField);
    nombreTipoTextField.setColumns(10);

    JLabel descripcionTipoLabel = new JLabel("Descripcion");
    descripcionTipoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelTipos.add(descripcionTipoLabel);

    descripcionTPEditorPane = new JEditorPane();
    descripcionTPEditorPane.setEditable(false);
    panelTipos.add(descripcionTPEditorPane);

    JLabel exposicionLabel = new JLabel("Exposicion");
    exposicionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelTipos.add(exposicionLabel);

    exposicionTextField = new JTextField();
    exposicionTextField.setEditable(false);
    panelTipos.add(exposicionTextField);
    exposicionTextField.setColumns(10);

    JLabel duracionTipoLabel = new JLabel("Duracion");
    duracionTipoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelTipos.add(duracionTipoLabel);

    duracionTipoTextField = new JTextField();
    duracionTipoTextField.setEditable(false);
    panelTipos.add(duracionTipoTextField);
    duracionTipoTextField.setColumns(10);

    JLabel lblNewLabel_1 = new JLabel("Costo");
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    panelTipos.add(lblNewLabel_1);

    costoTipoTextField = new JTextField();
    costoTipoTextField.setEditable(false);
    costoTipoTextField.setText("");
    panelTipos.add(costoTipoTextField);
    costoTipoTextField.setColumns(10);

    JLabel fecAltaLabel = new JLabel("Fecha de alta");
    fecAltaLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panelTipos.add(fecAltaLabel);

    fecAltaTextField = new JTextField();
    fecAltaTextField.setEditable(false);
    panelTipos.add(fecAltaTextField);
    fecAltaTextField.setColumns(10);

    JPanel panelBtn = new JPanel();
    FlowLayout flowLayout = (FlowLayout) panelBtn.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    getContentPane().add(panelBtn);

    paqueteComboBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {

        if (paqueteComboBox.getSelectedItem() != null) {
          if (!paqueteComboBox.getSelectedItem().equals(-1)) {

            DataPaquete dataPaquete = (DataPaquete) paqueteComboBox.getSelectedItem();

            nombreTextField.setText(dataPaquete.getNombre());
            descripcionPEditorPane.setText(dataPaquete.getDescripcion());
            String durac = String.valueOf(dataPaquete.getPeriodo());
            duracionTextField.setText(durac);
            String descuento = String.valueOf(dataPaquete.getDescuento());
            descuentoTextField.setText(descuento);
            String fec = String.valueOf(dataPaquete.getFechaAlta());
            fechaAltaTextField.setText(fec);
            // FALTA FECHA DE ALTA

            tiposComboBox.removeAllItems();
            Set<DataTipoPublicacionPaquete> listaTPP = dataPaquete.getTipoPublicaciones();
            Iterator<DataTipoPublicacionPaquete> iTP = listaTPP.iterator();
            while (iTP.hasNext()) {
              tiposComboBox.addItem(iTP.next().getDtp());
            }
          }
        }
      }
    });

    tiposComboBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (tiposComboBox.getSelectedItem() != null) {
          if (!((tiposComboBox.getSelectedItem()).equals(-1))) {
            DataTipoPublicacion dtp = (DataTipoPublicacion) tiposComboBox.getSelectedItem();

            nombreTipoTextField.setText(dtp.getNombre());
            descripcionTPEditorPane.setText(dtp.getDescripcion());
            int exp = dtp.getExposicion();
            String expo = String.valueOf(exp);
            exposicionTextField.setText(expo);
            int dur = dtp.getDuracion();
            String dura = String.valueOf(dur);
            duracionTipoTextField.setText(dura);
            String costo = String.valueOf(dtp.getCosto());
            costoTipoTextField.setText(costo);
            String fec = String.valueOf(dtp.getFecAlta());
            fecAltaTextField.setText(fec);

          }
        }
      }
    });

    pack();
  }

  public void eliminarDatos() {
    paqueteComboBox.removeAllItems();
    tiposComboBox.removeAllItems();
  }

  public void actualizar() {
    IOferta ioferta = Fabrica.getInstance().getIOferta();
    // Llenar combo paquetes
    Set<DataPaquete> listaP;

    listaP = ioferta.listarPaquetes();
    Iterator<DataPaquete> iter = listaP.iterator();

    if (!listaP.isEmpty()) {
      while (iter.hasNext()) {
        paqueteComboBox.addItem(iter.next());
      }
      paqueteComboBox.setSelectedIndex(0);

    }
    // Hasta acá tamos bien

    // Llenar combo TipoPubli

  }
}
