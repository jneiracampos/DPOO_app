package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ventana_Planear_Proyecto extends JFrame implements ActionListener {
	
	private Ventana_Opciones ventanaOpciones;
	private JPanel panelCentro;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelW;
	private JPanel panelE;
	
	public Ventana_Planear_Proyecto(Ventana_Opciones padre) {
		ventanaOpciones = padre;
		
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
		JButton cambiarFecha = new JButton("Cambiar fecha final del proyecto");
		JButton registrarPaquete = new JButton("Registrar un paquete");
		JButton registrarTarea = new JButton("Registrar una tarea");
		JButton volver = new JButton("Volver");
		
		panelCentro.add(txtUsuario);
		panelCentro.add(cambiarFecha);
		panelCentro.add(registrarPaquete);
		panelCentro.add(registrarTarea);
		panelCentro.add(volver);
		
		cambiarFecha.addActionListener(this);
		registrarPaquete.addActionListener(this);
		registrarTarea.addActionListener(this);
		volver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Cambiar fecha final del proyecto")){
			setVisible(false);
			new Ventana_Cambiar_Fecha_Proyecto(this);
		}
		else if (comando.equals("Registrar un paquete")) {
			setVisible(false);
			new Ventana_Registrar_Paquete(this);
		}
		else if (comando.equals("Registrar una tarea")){
			setVisible(false);
			new Ventana_Registrar_Tarea(this);
		}
		else if (comando.equals("Volver")) {
			ventanaOpciones.setVisible(true);
			setVisible(false);
		}
	}

}
