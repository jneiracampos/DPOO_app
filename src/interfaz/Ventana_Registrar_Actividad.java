package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.tree.TreePath;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import modelo.Actividad;
import modelo.Participante;

@SuppressWarnings("serial")
public class Ventana_Registrar_Actividad extends JFrame implements ActionListener {
	
	private ProyectTree arbol;
	private Ventana_Opciones ventanaOpciones;
	private JDateChooser calendario;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldNombre;
	private JTextField txtFieldDescripcion;
	private JTextField txtFieldCorreoParticipante;
	private TimePicker timeInicio;
	private TimePicker timeFin;

	
	public Ventana_Registrar_Actividad(Ventana_Opciones padre) {
		ventanaOpciones = padre;
		addTextField();
		addButtons();
		addNorthLabel();
		setSize(500, 350);
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
		
		JLabel txtCorreoParticipante = new JLabel("Correo del participante:");
		JLabel txtSolicitud = new JLabel("Ingrese los siguientes datos sobre la actividad...");
		JLabel txtNombre = new JLabel("Nombre:");
		JLabel txtDescripcion = new JLabel("Descripcion:");
		JLabel txtFecha = new JLabel("Fecha de realizacion:");
		JLabel txtHoraInicio = new JLabel("Hora de inicio:");
		JLabel txtHoraFin = new JLabel("Hora de fin:");
		JLabel txtNull = new JLabel();
				
		txtFieldCorreoParticipante = new JTextField();
		txtFieldNombre = new JTextField();
		txtFieldDescripcion = new JTextField();
		calendario = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		timeInicio = new TimePicker();
		timeFin = new TimePicker();
		
		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtCorreoParticipante).addComponent(txtSolicitud).addComponent(txtNombre).addComponent(txtDescripcion).addComponent(txtFecha).addComponent(txtHoraInicio).addComponent(txtHoraFin));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldCorreoParticipante).addComponent(txtNull).addComponent(txtFieldNombre).addComponent(txtFieldDescripcion).addComponent(calendario).addComponent(timeInicio).addComponent(timeFin));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtCorreoParticipante).addComponent(txtFieldCorreoParticipante));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtSolicitud).addComponent(txtNull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNombre).addComponent(txtFieldNombre));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtDescripcion).addComponent(txtFieldDescripcion));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtFecha).addComponent(calendario));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtHoraInicio).addComponent(timeInicio));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtHoraFin).addComponent(timeFin));
		layout.setVerticalGroup(vGroup);
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnRegistrar = new JButton("Registrar");
		JButton btnUbicacion = new JButton("Seleccionar ubicacion");
		JButton btnVolver = new JButton("Volver");
		
		panelSur.add(btnVolver);
		panelSur.add(btnUbicacion);
		panelSur.add(btnRegistrar);
		
		btnRegistrar.addActionListener(this);
		btnUbicacion.addActionListener(this);
		btnVolver.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Registrar")) {
			String nombreActividad = txtFieldNombre.getText();
			String descripcionActividad = txtFieldDescripcion.getText();
			LocalDate fecha = calendario.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalTime horaInicio = timeInicio.getTime();
			LocalTime horaFin = timeFin.getTime();
			Boolean finalizar = false;
			String correo = txtFieldCorreoParticipante.getText();
			Participante participante = Enrutador.getInstance().getProyecto().getParticipantePorCorreo(correo);
			
			if (nombreActividad.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar el nombre de la actividad", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else if (descripcionActividad.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar la descripcion de la actividad", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else if (Enrutador.getInstance().getProyecto().isParticipantePorCorreo(correo) == false) {
				JOptionPane.showMessageDialog(this, "No se tiene registro de este participante", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else if (fecha == null) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar la fecha de la actividad", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else if (horaInicio == null) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar la hora de inicio de la actividad", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else if (horaFin == null) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar la hora de fin de la actividad", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				if (fecha.isAfter(LocalDate.now())) {
					JOptionPane.showMessageDialog(this, "Ingrese una fecha anterior a " + LocalDate.now().toString(), "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
				}
				else if (horaInicio.isAfter(horaFin)) {
					JOptionPane.showMessageDialog(this, "Ingrese una hora de fin anterior a " + horaInicio.toString(), "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Actividad actividad = Enrutador.getInstance().nuevaActividad(nombreActividad, descripcionActividad, fecha, horaInicio, horaFin, finalizar, participante);
					actividad.addTiempo(horaInicio, horaFin);
					
					TreePath ruta = arbol.getRuta();
					if (ruta.getPathCount() == 3) {
						Enrutador.getInstance().getProyecto().getPaquete(ruta.getPathComponent(1).toString()).getTarea(ruta.getPathComponent(2).toString()).addActividad(actividad);
					}
					
					setVisible(false);
					ventanaOpciones.setVisible(true);
				}
			}
		}
		else if (comando.equals("Seleccionar ubicacion")) {
			SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	arbol = new ProyectTree();
	            }
	        });
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaOpciones.setVisible(true);
		}
	}
}
