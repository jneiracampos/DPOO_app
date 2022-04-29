package interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class Ventana_Cargar_Proyecto extends JFrame implements ActionListener {
	
	private Ventana_Menu_Principal ventanaMenuPrincipal;
	private JPanel panel1;
	private JPanel panel2;
	
	public Ventana_Cargar_Proyecto(Ventana_Menu_Principal padre) {
		//
		ventanaMenuPrincipal = padre;
		
		//crear y agregar paneles
		addPanel1();
		addPanel2();
		
		//set ventana
		setSize(400,400);
		setTitle("Cargar Proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addPanel1() {
		panel1 = new JPanel();
		panel1.setOpaque(true);
		add(panel1, BorderLayout.NORTH);
		
		//agregar elementos al panel
		JLabel nombre = new JLabel("Nombre del proyecto: ");
		JTextField txtNombre = new JTextField(10);
		panel1.add(nombre);
		panel1.add(txtNombre);
		
		//Ajustar Layout del panel
		GroupLayout layout = new GroupLayout(panel1);
		panel1.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(nombre));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtNombre));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(nombre).addComponent(txtNombre));
		layout.setVerticalGroup(vGroup);
		
	}
	
	private void addPanel2() {
		panel2 = new JPanel();
		panel2.setOpaque(true);
		add(panel2, BorderLayout.SOUTH);
		
		//agregar elementos al panel
		JButton volver = new JButton("Volver");
		JButton cargar = new JButton("Cargar");
		panel2.add(volver);
		panel2.add(cargar);
		volver.addActionListener(this);
		cargar.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("Volver")) {
			setVisible(false);
		}
		else if (comando.equals("Cargar")){
			setVisible(false);
		}
	}
}
