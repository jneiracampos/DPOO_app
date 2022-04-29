package interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import modelo.Proyecto;

@SuppressWarnings("serial")
public class Ventana_Crear_Proyecto extends JFrame implements ActionListener {
	
	private Ventana_Menu_Principal ventanaMenuPrincipal;
	private Proyecto proyecto;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldNombreProyecto;
	private JTextField txtFieldDescripcionProyecto;

	
	public Ventana_Crear_Proyecto(Ventana_Menu_Principal padre) {
		ventanaMenuPrincipal = padre;
		addTextField();
		addButtons();
		addNorthLabel();
		setSize(500, 500);
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
		JLabel txtDescripcion = new JLabel("Descripción del proyecto:");
		JLabel txtnull = new JLabel();
		txtFieldNombreProyecto = new JTextField();
		txtFieldDescripcionProyecto = new JTextField();
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



