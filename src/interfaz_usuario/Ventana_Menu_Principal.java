package interfaz_usuario;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Ventana_Menu_Principal extends JFrame implements ActionListener {
	
	private JPanel panelCentro;
	private JPanel panelNorte;
	private JPanel panelW;
	private JPanel panelE;
	private JPanel panelS;
	
	private Ventana_Inicio ventanaInicio;
	
	public Ventana_Menu_Principal(Ventana_Inicio padre) {
		ventanaInicio = padre;
		
		panelW = new JPanel();
		panelW.setOpaque(true);
		add(panelW, BorderLayout.WEST);
		
		panelE = new JPanel();
		panelE.setOpaque(true);
		add(panelE, BorderLayout.EAST);
		
		panelS = new JPanel();
		panelS.setOpaque(true);
		add(panelS, BorderLayout.SOUTH);
		panelS.setPreferredSize(new Dimension(400,80));
		
		addButtons();
		addNorthLabel();
		
		setSize(400, 400);
		setTitle("Administrador de proyectos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setPreferredSize(new Dimension(400,40));
		
		JLabel txt = new JLabel("Opciones de la aplicacion");
		panelNorte.add(txt);
	}
	
	private void addButtons() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		panelCentro.setLayout(new GridLayout(5, 1, 0, 12));

		add(panelCentro, BorderLayout.CENTER);

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
			setVisible(false);
			new Ventana_Cargar_Proyecto(this);
		}
		else if (comando.equals("Crear proyecto")){
			setVisible(false);
			new Ventana_Crear_Proyecto(ventanaInicio, this);
		}
		else if (comando.equals("Buscar proyecto")) {
			setVisible(false);
			new Ventana_Buscar_Proyecto(this);
		}
		else if (comando.equals("Salir de la aplicacion")) {
			System.exit(0);
		}
	}

}
