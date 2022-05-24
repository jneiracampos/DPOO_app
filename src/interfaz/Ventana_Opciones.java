package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Ventana_Opciones extends JFrame implements ActionListener {
	
	private Ventana_Menu_Principal ventanaMenuPrincipal;
	
	private JPanel panelCentro;
	private JPanel panelNorte;
	private JPanel panelW;
	private JPanel panelE;
	private JPanel panelS;
	
	public Ventana_Opciones(Ventana_Menu_Principal padre) {
		ventanaMenuPrincipal = padre;
		
		panelW = new JPanel();
		panelW.setOpaque(true);
		add(panelW, BorderLayout.WEST);
		
		panelE = new JPanel();
		panelE.setOpaque(true);
		add(panelE, BorderLayout.EAST);
		
		panelS = new JPanel();
		panelS.setOpaque(true);
		add(panelS, BorderLayout.SOUTH);
		panelS.setPreferredSize(new Dimension(400,20));
		
		addButtons();
		addNorthLabel();
		
		setSize(550, 500);
		setTitle("Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		String nombreProyecto = Enrutador.getProyecto().getNombre();
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

		JButton cambiarDesc = new JButton("Cambiar la descripcion del proyecto");
		JButton cambiarFecha = new JButton("Cambiar fecha final del proyecto");
		JButton registrarPart = new JButton("Registrar un participante");
		JButton realizarAct = new JButton("Realizar una actividad");
		JButton registrarAct = new JButton("Registrar una actividad pasada");
		JButton cambiarHora = new JButton("Cambiar la hora final de una actividad");
		JButton reporte = new JButton("Consultar el reporte de un participante");
		JButton guardarProyecto = new JButton("Consultar el estado de este proyecto");
		JButton volver = new JButton("Volver al Menu Principal");
		
		panelCentro.add(cambiarDesc);
		panelCentro.add(cambiarFecha);
		panelCentro.add(registrarPart);
		panelCentro.add(realizarAct);
		panelCentro.add(registrarAct);
		panelCentro.add(cambiarHora);
		panelCentro.add(reporte);
		panelCentro.add(guardarProyecto);
		panelCentro.add(volver);
		
		cambiarDesc.addActionListener(this);
		cambiarFecha.addActionListener(this);
		registrarPart.addActionListener(this);
		realizarAct.addActionListener(this);
		registrarAct.addActionListener(this);
		cambiarHora.addActionListener(this);
		reporte.addActionListener(this);
		guardarProyecto.addActionListener(this);
		volver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Cambiar la descripcion del proyecto")) {
			setVisible(false);
			new Ventana_Descripcion(this);
		}
		else if (comando.equals("Cambiar fecha final del proyecto")){
			setVisible(false);
			new Ventana_Cambiar_Fecha_Proyecto(this);
		}
		else if (comando.equals("Registrar un participante")) {
			setVisible(false);
			new Ventana_Registrar_Participante(this);
		}
		else if (comando.equals("Realizar una actividad")){
			setVisible(false);
			new Ventana_Realizar_Actividad(this);
		}
		else if (comando.equals("Registrar una actividad pasada")) {
			setVisible(false);
			new Ventana_Registrar_Actividad(this);
		}
		else if (comando.equals("Cambiar la hora final de una actividad")){
			setVisible(false);
			new Ventana_Cambiar_Hora_Actividad(this);
		}
		else if (comando.equals("Consultar el reporte de un participante")) {
			setVisible(false);
			new Ventana_Consultar_Reporte(this);
		}
		else if (comando.equals("Consultar el estado de este proyecto")) {
			setVisible(false);
			new Ventana_Estado_Proyecto(this);
		}
		else if (comando.equals("Volver al Menu Principal")) {
			ventanaMenuPrincipal.setVisible(true);
			setVisible(false);
		}
	}

}
