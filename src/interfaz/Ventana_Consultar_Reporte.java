package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import com.toedter.calendar.JDateChooser;

import modelo.Participante;
import procesamiento.GeneradorReporte;

@SuppressWarnings("serial")
public class Ventana_Consultar_Reporte extends JFrame implements ActionListener {
	
	private Ventana_Opciones ventanaOpciones;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldCorreoParticipante;
	private JDateChooser calendario;
	private JComboBox<String> tipo;

	public Ventana_Consultar_Reporte(Ventana_Opciones padre) {
		ventanaOpciones = padre;
		
		addNorthLabel();
		addTextField();
		addButtons();
		
		setSize(500,300);
		setTitle("Consultar el reporte de un participante");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txt = new JLabel("Consultar el reporte de un participante");
		panelNorte.add(txt);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtCorreoParticipante = new JLabel("Correo del participante:");
		JLabel txtSolicitud = new JLabel("Ingrese los siguientes datos sobre la actividad...");
		JLabel txtTipo = new JLabel("Tipo:");
		JLabel txtFecha = new JLabel("Fecha de realizacion:");
		JLabel txtNull = new JLabel();
		
		String[] optionsToChoose = {"Documentacion", "Implementacion", "Pruebas", "Investigacion", "Diseño", "Analisis"};
		tipo = new JComboBox<String>(optionsToChoose);
		
		txtFieldCorreoParticipante = new JTextField();
		calendario = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		
		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtCorreoParticipante).addComponent(txtSolicitud).addComponent(txtTipo).addComponent(txtFecha));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldCorreoParticipante).addComponent(txtNull).addComponent(tipo).addComponent(calendario));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtCorreoParticipante).addComponent(txtFieldCorreoParticipante));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtSolicitud).addComponent(txtNull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtTipo).addComponent(tipo));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtFecha).addComponent(calendario));
		layout.setVerticalGroup(vGroup);
		
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		JButton btnAceptar = new JButton("Aceptar");
		
		panelSur.add(btnVolver);
		panelSur.add(btnAceptar);
		
		btnVolver.addActionListener(this);
		btnAceptar.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		LocalDate fecha = calendario.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String correo = txtFieldCorreoParticipante.getText();
		String tipoActividad = (String) tipo.getSelectedItem();

		if (comando.equals("Aceptar")) {
			if (fecha == null) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar la fecha de la actividad", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else if (Enrutador.getProyecto().isParticipantePorCorreo(correo) == false) {
				JOptionPane.showMessageDialog(this, "No se tiene registro de este participante", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Participante participante = Enrutador.getProyecto().getParticipantePorCorreo(correo);
				GeneradorReporte.getReporte(Enrutador.getProyecto(), participante, tipoActividad, fecha);
			}
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaOpciones.setVisible(true);
		}
	}
}
