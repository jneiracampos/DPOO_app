package interfaz_usuario;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import modelo.Proyecto;

@SuppressWarnings("serial")
public class Ventana_Crear_Proyecto extends JFrame implements ActionListener {
	
	private Ventana_Menu_Principal ventanaMenuPrincipal;
	
	private Proyecto proyecto;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	
	public Ventana_Crear_Proyecto(Ventana_Menu_Principal padre) {
		ventanaMenuPrincipal = padre;
		addTextField();
		addButtons();
		addNorthLabel();
		setSize(400, 300);
		setTitle("Administrador de proyectos");
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
		
		JLabel txtNombre = new JLabel("Nombre del proyecto:");
		JLabel txtDescripcion = new JLabel("Descripcion del proyecto:");
		JLabel txtnull = new JLabel();
		JTextField txtFieldNombreProyecto = new JTextField();
		JTextField txtFieldDescripcionProyecto = new JTextField();
		txtnull.setVisible(false);

		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtNombre).addComponent(txtDescripcion));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldNombreProyecto).addComponent(txtFieldDescripcionProyecto));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNombre).addComponent(txtFieldNombreProyecto));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtDescripcion).addComponent(txtFieldDescripcionProyecto));
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
			setVisible(false);
			new Ventana_Opciones(ventanaMenuPrincipal);
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
