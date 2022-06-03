package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import modelo.Actividad;
import modelo.Participante;

	@SuppressWarnings("serial")
	public class Ventana_Realizar_Actividad extends JFrame implements ActionListener {
		
		private Ventana_Opciones ventanaOpciones;
		private JPanel panelCentro;
		private JPanel panelSur;
		private JPanel panelNorte;
		private JTextField txtFieldNombre;
		private JTextField txtFieldDescripcion;
		private JTextField txtFieldCorreoParticipante;
		private JComboBox<String> finalizarTarea;
		
		public Ventana_Realizar_Actividad(Ventana_Opciones padre) {
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

			JLabel txt = new JLabel("Realizar una actividad");
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
			JLabel txtFinaliza = new JLabel("Seleccione:");
			JLabel txtNull = new JLabel();
			
			txtFieldCorreoParticipante = new JTextField();
			txtFieldNombre = new JTextField();
			txtFieldDescripcion = new JTextField();
			
			String[] optionsToChoose = {"Finaliza la tarea", "No finaliza la tarea"};
			finalizarTarea = new JComboBox<String>(optionsToChoose);
			
			GroupLayout layout = new GroupLayout(panelCentro);
			panelCentro.setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			
			GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
			hGroup.addGroup(layout.createParallelGroup().addComponent(txtCorreoParticipante).addComponent(txtSolicitud).addComponent(txtNombre).addComponent(txtDescripcion).addComponent(txtFinaliza));
			hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldCorreoParticipante).addComponent(txtNull).addComponent(txtFieldNombre).addComponent(txtFieldDescripcion).addComponent(finalizarTarea));
			layout.setHorizontalGroup(hGroup);
			
			GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtCorreoParticipante).addComponent(txtFieldCorreoParticipante));
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtSolicitud).addComponent(txtNull));
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNombre).addComponent(txtFieldNombre));
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtDescripcion).addComponent(txtFieldDescripcion));
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtFinaliza).addComponent(finalizarTarea));
			layout.setVerticalGroup(vGroup);
		}
		
		private void addButtons() {
			panelSur = new JPanel();
			panelSur.setOpaque(true);
			add(panelSur, BorderLayout.SOUTH);
			
			JButton btnFinalizar = new JButton("Iniciar actividad");
			panelSur.add(btnFinalizar);
			btnFinalizar.addActionListener(this);
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
			String comando = e.getActionCommand();
			
			String correo = txtFieldCorreoParticipante.getText();
			String nombreActividad = txtFieldNombre.getText();
			String descripcionActividad = txtFieldDescripcion.getText();
			String finalizar = (String) finalizarTarea.getSelectedItem();
			LocalDate fecha = LocalDate.now();
			LocalTime horaInicio = LocalTime.now();
			LocalTime horaFin = null;
			
			
			if (comando.equals("Iniciar actividad")) {
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
				else {
					Boolean finalizaTarea = false;
					if (finalizar.equals("Finaliza la tarea"))
						finalizaTarea = true;					
					Participante participante = Enrutador.getInstance().getProyecto().getParticipantePorCorreo(correo);
					Actividad actividad = Enrutador.getInstance().nuevaActividad(nombreActividad, descripcionActividad, fecha, horaInicio, horaFin, finalizaTarea, participante);
					new Ventana_Cronometro(actividad, ventanaOpciones);
					setVisible(false);
				}
			}
		}
	}
