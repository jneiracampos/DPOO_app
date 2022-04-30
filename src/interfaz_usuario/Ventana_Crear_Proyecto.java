package interfaz_usuario;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import modelo.Participante;
import modelo.Proyecto;

@SuppressWarnings("serial")
public class Ventana_Crear_Proyecto extends JFrame implements ActionListener {
	
	private Ventana_Inicio ventanaInicio;
	private Ventana_Menu_Principal ventanaMenuPrincipal;
	private Proyecto proyecto;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldNombreProyecto;
	private JTextField txtFieldDescripcionProyecto;
	
	public Ventana_Crear_Proyecto(Ventana_Inicio padre, Ventana_Menu_Principal menu) {
		ventanaInicio = padre;
		ventanaMenuPrincipal = menu;
		addTextField();
		addButtons();
		addNorthLabel();
		setSize(400, 300);
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
		JLabel txtsolicitud = new JLabel("Ingrese la fecha de fin del proyecto:");
		JLabel txtnull = new JLabel();
		txtnull.setVisible(false);
		txtFieldNombreProyecto = new JTextField();
		txtFieldDescripcionProyecto = new JTextField();

		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtnombre).addComponent(txtdescripcion).addComponent(txtsolicitud));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldNombreProyecto).addComponent(txtFieldDescripcionProyecto).addComponent(txtnull));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtnombre).addComponent(txtFieldNombreProyecto));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtdescripcion).addComponent(txtFieldDescripcionProyecto));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtsolicitud).addComponent(txtnull));
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
			if (nombreProyecto.equals("") || descripcionProyecto.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar el nombre y la descripcion", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Participante usuario = ventanaInicio.getUsuario();
				LocalDate fechaFin = null;
				LocalDate fechaInicio = LocalDate.now();
				proyecto = Registro.nuevoProyecto(nombreProyecto, descripcionProyecto, fechaInicio, fechaFin, usuario);
				setVisible(false);
				new Ventana_Opciones(ventanaMenuPrincipal);
			}
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaMenuPrincipal.setVisible(true);
		}
	}
	
	public Proyecto getProyecto() {
		return proyecto;
	}
	
}
