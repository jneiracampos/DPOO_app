/**
 * 
 */
package interfaz_usuario;

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
		JLabel txt = new JLabel("Acciones sobre el proyecto");
		panelNorte.add(txt);
	}
	
	private void addButtons() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		panelCentro.setLayout(new GridLayout(8, 1, 0, 8));
		add(panelCentro, BorderLayout.CENTER);

		JButton cambiarDesc = new JButton("Cambiar descripcion");
		JButton cambiarFecha = new JButton("Cambiar fecha final");
		JButton registrarPart = new JButton("Registrar participante");
		JButton realizarAct = new JButton("Realizar actividad");
		JButton registrarAct = new JButton("Registrar actividad pasada");
		JButton cambiarHora = new JButton("Cambiar hora final de una actividad");
		JButton reporte = new JButton("Consultar reporte de un participante");
		JButton volver = new JButton("Volver al Menu Principal");
		
		panelCentro.add(cambiarDesc);
		panelCentro.add(cambiarFecha);
		panelCentro.add(registrarPart);
		panelCentro.add(realizarAct);
		panelCentro.add(registrarAct);
		panelCentro.add(cambiarHora);
		panelCentro.add(reporte);
		panelCentro.add(volver);
		
		cambiarDesc.addActionListener(this);
		cambiarFecha.addActionListener(this);
		registrarPart.addActionListener(this);
		realizarAct.addActionListener(this);
		registrarAct.addActionListener(this);
		cambiarHora.addActionListener(this);
		reporte.addActionListener(this);
		volver.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Cambiar descripcion")) {
			setVisible(false);
			
		}
		else if (comando.equals("Cambiar fecha final")){
			setVisible(false);
			
		}
		else if (comando.equals("Registrar participante")) {
			setVisible(false);
			
		}
		else if (comando.equals("Realizar actividad")){
			setVisible(false);
			
		}
		else if (comando.equals("Registrar actividad pasada")) {
			setVisible(false);
			
		}
		else if (comando.equals("Cambiar hora final de una actividad")){
			setVisible(false);
			
		}
		else if (comando.equals("Consultar reporte de un participante")) {
			setVisible(false);
			
		}
		else if (comando.equals("Volver al Menu Principal")) {
			ventanaMenuPrincipal.setVisible(true);
			setVisible(false);
		}
	}

}
