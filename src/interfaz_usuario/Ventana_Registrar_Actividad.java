package interfaz_usuario;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;
import javax.swing.GroupLayout.*;

import modelo.Actividad;
import modelo.Participante;
import modelo.Proyecto;

@SuppressWarnings("serial")
public class Ventana_Registrar_Actividad extends JFrame implements ActionListener {
	
	private Ventana_Opciones ventanaOpciones;
	
	private Actividad actividad;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtCorreoA;
	
	
	
	public Ventana_Registrar_Actividad(Ventana_Opciones padre) {
		ventanaOpciones = padre;
		addTextField();
		addButtons();
		addNorthLabel();
		setSize(400, 300);
		setTitle("");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txt = new JLabel("Registrar una actividad");
		panelNorte.add(txt);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel correo = new JLabel("Correo del autor:");
		JLabel nombre = new JLabel("Nombre Actividad:");
		JLabel descripcion = new JLabel("Descripcion:");
		JLabel tipo = new JLabel("Tipo:");
		JLabel fecha = new JLabel("Fecha de realizacion:");
		JLabel horaInicio = new JLabel("Hora de inicio:");
		JLabel horaFin = new JLabel("Hora de fin:");
		
		JLabel txtnull = new JLabel();
		JLabel txtnull1 = new JLabel();
		JLabel txtnull2= new JLabel();
		JLabel txtnull3 = new JLabel();
		
		txtCorreoA = new JTextField();
		txtNombre = new JTextField();
		txtDescripcion = new JTextField();

		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(correo).addComponent(nombre).addComponent(descripcion).addComponent(tipo).addComponent(fecha).addComponent(horaInicio).addComponent(horaFin));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtCorreoA).addComponent(txtDescripcion).addComponent(txtNombre).addComponent(txtnull).addComponent(txtnull1).addComponent(txtnull2).addComponent(txtnull3));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(correo).addComponent(txtCorreoA));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(nombre).addComponent(txtNombre));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(descripcion).addComponent(txtDescripcion));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(tipo).addComponent(txtnull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(fecha).addComponent(txtnull1));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(horaInicio).addComponent(txtnull2));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(horaFin).addComponent(txtnull3));
		layout.setVerticalGroup(vGroup);
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnRegistrar = new JButton("Registrar");
		JButton btnVolver = new JButton("Volver");
		
		panelSur.add(btnVolver);
		panelSur.add(btnRegistrar);
		
		btnRegistrar.addActionListener(this);
		btnVolver.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Registrar")) {
			String correo = txtCorreoA.getText();
			String nombreActividad = txtNombre.getText();
			String descripcionActividad = txtDescripcion.getText();
			String tipo = null;
			LocalDate fecha = null;
			LocalTime horaInicio = null;
			LocalTime horaFin = null;
			
			Participante participante = Registro.getParticipantePorCorreo(correo);
			
			if (nombreActividad.equals("") || descripcionActividad.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar todos los datos", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				actividad = Registro.nuevaActividad(nombreActividad, descripcionActividad, tipo, fecha, horaInicio, horaFin, participante);
				Registro.addOtroParticipante(participante);
				setVisible(false);
				ventanaOpciones.setVisible(true);
			}
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaOpciones.setVisible(true);
		}
	}
}
