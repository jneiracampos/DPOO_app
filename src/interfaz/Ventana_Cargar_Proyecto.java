package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;

import modelo.Proyecto;
import procesamiento.Administrador_Datos;

@SuppressWarnings("serial")
public class Ventana_Cargar_Proyecto extends JFrame implements ActionListener {
	
	private Ventana_Menu_Principal ventanaMenuPrincipal;
	private JPanel panelNorte;
	private JPanel panel1;
	private JPanel panel2;
	private JTextField txtNombre;
	
	public Ventana_Cargar_Proyecto(Ventana_Menu_Principal padre) {
		ventanaMenuPrincipal = padre;
		addPanelNorte();
		addPanel1();
		addPanel2();
		setSize(400,200);
		setTitle("Cargar un proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addPanelNorte() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setPreferredSize(new Dimension(400,40));
		JLabel txt = new JLabel("Cargar un proyecto");
		panelNorte.add(txt);
		
	}
	private void addPanel1() {
		panel1 = new JPanel();
		panel1.setOpaque(true);
		add(panel1, BorderLayout.CENTER);
		
		//agregar elementos al panel
		JLabel nombre = new JLabel("Nombre del proyecto:");
		txtNombre = new JTextField(10);
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
			ventanaMenuPrincipal.setVisible(true);
		}
		else if (comando.equals("Cargar")){
			try {
				Proyecto proyecto = Administrador_Datos.getInstance().cargarArchivo(txtNombre.getText());
				Enrutador.getInstance().setProyecto(proyecto);
				setVisible(false);
				new Ventana_Opciones(ventanaMenuPrincipal);
			} catch (Throwable e1) {
				//	e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "No se encontro el proyecto en el disco local", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			setVisible(false);
		}
	}
}
