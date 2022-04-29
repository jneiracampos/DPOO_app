package interfaz_usuario;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Ventana_Menu_Principal extends JFrame implements ActionListener {
	
	private JPanel panelCentro;
	private JPanel panelNorte;
	
	public Ventana_Menu_Principal() {
		addButtons();
		addNorthLabel();
		setSize(400,400);
		setTitle("Administrador de proyectos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txt = new JLabel("Opciones de la aplicacion");
		panelNorte.add(txt);
	}
	
	private void addButtons() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(5,1));

		JButton btnCargar = new JButton("Cargar un proyecto del disco local");
		JButton btnCrear = new JButton("Crear proyecto");
		JButton btnBuscar = new JButton("Buscar proyecto");
		JButton btnSalir = new JButton("Salir de la aplicacion");
		JLabel txtUsuario = new JLabel("Por favor seleccione una de las siguientes opciones:");
		
		panelCentro.add(txtUsuario);
		panelCentro.add(btnCargar);
		panelCentro.add(btnCrear);
		panelCentro.add(btnBuscar);
		panelCentro.add(btnSalir);
		
		btnCargar.addActionListener(this);
		btnCrear.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnSalir.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Cargar un proyecto del disco local")) {
			
			new Ventana_Cargar_Proyecto(this);
		}
		else if (comando.equals("Crear proyecto")){
			setVisible(false);
		}
		else if (comando.equals("Buscar proyecto")) {
			setVisible(false);
		}
		else if (comando.equals("Salir de la aplicacion")) {
			System.exit(0);
		}
	}

}
