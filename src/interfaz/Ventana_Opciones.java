package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import procesamiento.Administrador_Datos;

@SuppressWarnings("serial")
public class Ventana_Opciones extends JFrame implements ActionListener {
	
	private Ventana_Menu_Principal ventanaMenuPrincipal;
	private JPanel panelCentro;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelW;
	private JPanel panelE;
	
	public Ventana_Opciones(Ventana_Menu_Principal padre) {
		ventanaMenuPrincipal = padre;
		
		panelW = new JPanel();
		panelW.setOpaque(true);
		add(panelW, BorderLayout.WEST);
		
		panelE = new JPanel();
		panelE.setOpaque(true);
		add(panelE, BorderLayout.EAST);
		
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		panelSur.setPreferredSize(new Dimension(400,20));
		
		addButtons();
		addNorthLabel();
		
		setSize(550, 500);
		setTitle("Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		String nombreProyecto = Enrutador.getInstance().getProyecto().getNombre();
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setPreferredSize(new Dimension(400,40));
		JLabel nombre = new JLabel(nombreProyecto);
		panelNorte.add(nombre);
	}
	
	private void addButtons() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		panelCentro.setLayout(new GridLayout(9, 1, 0, 9));
		add(panelCentro, BorderLayout.CENTER);

		JLabel txtUsuario = new JLabel("Por favor seleccione una de las siguientes opciones:");
		JButton registrarParticipante = new JButton("Registrar un participante");
		JButton planearProyecto = new JButton("Realizar la planeacion del proyecto");
		JButton realizarActividad = new JButton("Realizar una actividad");
		JButton registrarActividad = new JButton("Registrar una actividad pasada");
		JButton reporte = new JButton("Consultar un reporte");
		JButton guardarProyecto = new JButton("Guardar este proyecto en el disco local");
		JButton volver = new JButton("Volver");
		
		panelCentro.add(txtUsuario);
		panelCentro.add(registrarParticipante);
		panelCentro.add(planearProyecto);
		panelCentro.add(realizarActividad);
		panelCentro.add(registrarActividad);
		panelCentro.add(reporte);
		panelCentro.add(guardarProyecto);
		panelCentro.add(volver);
		
		registrarParticipante.addActionListener(this);
		planearProyecto.addActionListener(this);
		realizarActividad.addActionListener(this);
		registrarActividad.addActionListener(this);
		reporte.addActionListener(this);
		guardarProyecto.addActionListener(this);
		volver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Registrar un participante")) {
			setVisible(false);
			new Ventana_Registrar_Participante(this);
		}
		else if (comando.equals("Realizar la planeacion del proyecto")){
			setVisible(false);
			new Ventana_Planear_Proyecto(this);
		}
		else if (comando.equals("Realizar una actividad")){
			setVisible(false);
			new Ventana_Realizar_Actividad(this);
		}
		else if (comando.equals("Registrar una actividad pasada")) {
			setVisible(false);
			new Ventana_Registrar_Actividad(this);
		}
		else if (comando.equals("Consultar un reporte")) {
			setVisible(false);
			new Ventana_Consultar_Reporte(this);
		}
		else if (comando.equals("Guardar este proyecto en el disco local")) {
			setVisible(false);
			try {
				Administrador_Datos.getInstance().generarArchivo(Enrutador.getInstance().getProyecto());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (comando.equals("Volver")) {
			ventanaMenuPrincipal.setVisible(true);
			setVisible(false);
		}
	}

}
