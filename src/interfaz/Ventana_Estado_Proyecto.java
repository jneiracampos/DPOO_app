package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import procesamiento.AdministradorDatos;


@SuppressWarnings("serial")
public class Ventana_Estado_Proyecto extends JFrame implements ActionListener {
	
	private Ventana_Opciones ventanaOpciones;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;

	public Ventana_Estado_Proyecto(Ventana_Opciones padre) {
		ventanaOpciones = padre;
		
		addNorthLabel();
		addTextField();
		addButtons();
		
		setSize(400,200);
		setTitle("Consultar el estado de este proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txt = new JLabel("Consultar el estado de este proyecto");
		panelNorte.add(txt);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton("Guardar en disco local");
		JButton btnVolver = new JButton("Volver");
		
		panelSur.add(btnVolver);
		panelSur.add(btnGuardar);
		
		btnGuardar.addActionListener(this);
		btnVolver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Guardar en disco local")) {
			try {
				AdministradorDatos.generarArchivo(Enrutador.getProyecto());
				ventanaOpciones.setVisible(true);
				setVisible(false);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaOpciones.setVisible(true);
		}
	}
}