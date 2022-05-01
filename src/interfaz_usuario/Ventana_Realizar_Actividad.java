package interfaz_usuario;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Ventana_Realizar_Actividad extends JFrame implements ActionListener {
	
	private JPanel panelCentro;
	private JPanel panelNorte;
	private JPanel panelW;
	private JPanel panelE;
	private JPanel panelS;
	
	private Ventana_Inicio ventanaInicio;
	
	public Ventana_Realizar_Actividad(Ventana_Opciones padre) {
		ventanaOpciones = padre;
		
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
		setTitle("Realizar Actividad");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setPreferredSize(new Dimension(400,40));
		
		JLabel txt = new JLabel("La actividad va a realizar: ");
		panelNorte.add(txt);
	}
	
	private void addButtons() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		panelCentro.setLayout(new GridLayout(5, 1, 0, 12));

		add(panelCentro, BorderLayout.CENTER);

		JButton btnYo = new JButton("Yo");
		JButton btnOtro = new JButton("Otro Usuario");
		

		panelCentro.add(btnYo);
		panelCentro.add(btnOtro);

		
		btnYo.addActionListener(this);
		btnOtro.addActionListener(this);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Yo")) {
			setVisible(false);
			new Ventana_Yo(this);
		}
		else if (comando.equals("Otro Usuario")){
			setVisible(false);
			new Ventana_Otro_Usuario(this);
		}

	}

}