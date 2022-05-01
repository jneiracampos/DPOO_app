package interfaz_usuario;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import com.toedter.calendar.JDateChooser;
import modelo.Participante;

@SuppressWarnings("serial")
public class Ventana_Crear_Proyecto extends JFrame implements ActionListener {
	
	private Ventana_Inicio ventanaInicio;
	private Ventana_Menu_Principal ventanaMenuPrincipal;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldNombreProyecto;
	private JTextField txtFieldDescripcionProyecto;
	private JDateChooser calendario;
	
	public Ventana_Crear_Proyecto(Ventana_Inicio padre, Ventana_Menu_Principal menu) {
		ventanaInicio = padre;
		ventanaMenuPrincipal = menu;
		addTextField();
		addButtons();
		addNorthLabel();
		setSize(400, 200);
		setTitle("Crear un proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txt = new JLabel("Crear un proyecto");
		panelNorte.add(txt);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtnombre = new JLabel("Nombre del proyecto:");
		JLabel txtdescripcion = new JLabel("Descripcion del proyecto:");
		JLabel txtsolicitud = new JLabel("Fecha de fin del proyecto:");
		calendario = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		txtFieldNombreProyecto = new JTextField();
		txtFieldDescripcionProyecto = new JTextField();

		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtnombre).addComponent(txtdescripcion).addComponent(txtsolicitud));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldNombreProyecto).addComponent(txtFieldDescripcionProyecto).addComponent(calendario));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtnombre).addComponent(txtFieldNombreProyecto));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtdescripcion).addComponent(txtFieldDescripcionProyecto));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtsolicitud).addComponent(calendario));
		layout.setVerticalGroup(vGroup);
		
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Crear");
		JButton btnVolver = new JButton("Volver");
		
		panelSur.add(btnVolver);
		panelSur.add(btnAceptar);
		
		btnAceptar.addActionListener(this);
		btnVolver.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Crear")) {
			String nombreProyecto = txtFieldNombreProyecto.getText();
			String descripcionProyecto = txtFieldDescripcionProyecto.getText();
			if (nombreProyecto.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar el nombre del proyecto", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else if (descripcionProyecto.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar la descripcion del proyecto", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else if (calendario.getDate() == null) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar la fecha de fin del proyecto", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Participante usuario = ventanaInicio.getUsuario();
				LocalDate fechaInicio = LocalDate.now();
				LocalDate fechaFin = calendario.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if (fechaFin.isAfter(fechaInicio)) {
					Registro.nuevoProyecto(nombreProyecto, descripcionProyecto, fechaInicio, fechaFin, usuario);
					setVisible(false);
					new Ventana_Opciones(ventanaMenuPrincipal);
				}
				else {
					JOptionPane.showMessageDialog(this, "Ingresa una fecha de fin que sea posterior a " + fechaInicio.toString(), "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaMenuPrincipal.setVisible(true);
		}
	}
}
