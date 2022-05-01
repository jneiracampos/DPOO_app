	package interfaz_usuario;

	import java.awt.*;
	import java.awt.event.*;
	import java.time.LocalDate;
	import java.time.LocalTime;
	import java.time.ZoneId;
	import javax.swing.*;
	import javax.swing.GroupLayout.*;
	import com.github.lgooddatepicker.components.TimePicker;
	import com.toedter.calendar.JDateChooser;
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
			JLabel txtTipo = new JLabel("Tipo:");
			JLabel txtCronometro = new JLabel("Cronometro");
			JLabel txtNull = new JLabel();
			JLabel txtNull1 = new JLabel();

			
			txtFieldCorreoParticipante = new JTextField();
			txtFieldNombre = new JTextField();
			txtFieldDescripcion = new JTextField();
			txtFieldCronometro = new JTextField();
			
			
			GroupLayout layout = new GroupLayout(panelCentro);
			panelCentro.setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			
			GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
			hGroup.addGroup(layout.createParallelGroup().addComponent(txtCorreoParticipante).addComponent(txtSolicitud).addComponent(txtNombre).addComponent(txtDescripcion).addComponent(txtTipo).addComponent(txtCronometro));
			hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldCorreoParticipante).addComponent(txtNull).addComponent(txtFieldNombre).addComponent(txtFieldDescripcion).addComponent(txtNull1).addComponent(txtFieldCronometro));
			layout.setHorizontalGroup(hGroup);
			
			GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtCorreoParticipante).addComponent(txtFieldCorreoParticipante));
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtSolicitud).addComponent(txtNull));
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNombre).addComponent(txtFieldNombre));
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtDescripcion).addComponent(txtFieldDescripcion));
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtTipo).addComponent(txtNull1));
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtCronometro).addComponent(txtFieldCronometro));
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
			
			if (comando.equals("Realizar")) {
				String correo = txtFieldCorreoParticipante.getText();
				String nombreActividad = txtFieldNombre.getText();
				String tipo = null;
				Participante participante = Registro.getParticipantePorCorreo(correo);
				
				if (nombreActividad.equals("")) {
					JOptionPane.showMessageDialog(this, "Recuerde ingresar el nombre de la actividad", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
				}
				else if (descripcionActividad.equals("")) {
					JOptionPane.showMessageDialog(this, "Recuerde ingresar la descripcion de la actividad", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
				
					}
				}
			}
			else if (comando.equals("Volver")) {
				setVisible(false);
				ventanaOpciones.setVisible(true);
			}
		}
	}
