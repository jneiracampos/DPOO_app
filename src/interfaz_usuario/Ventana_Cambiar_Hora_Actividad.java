package interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import com.github.lgooddatepicker.components.TimePicker;
import modelo.Actividad;

@SuppressWarnings("serial")
public class Ventana_Cambiar_Hora_Actividad extends JFrame implements ActionListener {
	
	private Ventana_Opciones ventanaOpciones;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldNombreActividad;
	private TimePicker timeFin;
	
	public Ventana_Cambiar_Hora_Actividad(Ventana_Opciones padre) {
		ventanaOpciones = padre;
		
		addNorthLabel();
		addButtons();
		addTextField();
		
		setSize(400,200);
		setTitle("Cambiar la hora de fin de una actividad");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txt = new JLabel("Cambiar la hora de fin de una actividad");
		panelNorte.add(txt);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtNombreActividad = new JLabel("Nombre de la actividad:");
		JLabel txtHoraFin = new JLabel("Hora de fin:");
		
		txtFieldNombreActividad = new JTextField();
		timeFin = new TimePicker();
		
		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtNombreActividad).addComponent(txtHoraFin));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldNombreActividad).addComponent(timeFin));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNombreActividad).addComponent(txtFieldNombreActividad));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtHoraFin).addComponent(timeFin));
		layout.setVerticalGroup(vGroup);
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Actualizar");
		JButton btnVolver = new JButton("Volver");
		
		panelSur.add(btnVolver);
		panelSur.add(btnAceptar);
		
		btnAceptar.addActionListener(this);
		btnVolver.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Actualizar")) {
			String nombreActividad = txtFieldNombreActividad.getText();
			if (Registro.isActividad(nombreActividad)) {
				Actividad actividad = Registro.getActividad(nombreActividad);
				LocalTime horaInicio = actividad.getHoraInicio();
				LocalTime horaFin = timeFin.getTime();
				if (horaFin.isBefore(horaInicio)) {
					JOptionPane.showMessageDialog(this, "Ingresa una hora de fin que sea posterior a " + horaInicio.toString(), "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					actividad.setHoraFin(horaFin);
					setVisible(false);
					ventanaOpciones.setVisible(true);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "No se tiene registro de esta actividad", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaOpciones.setVisible(true);
		}
		
	}
}
