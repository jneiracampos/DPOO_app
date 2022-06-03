package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ventana_Registrar_Tarea extends JFrame implements ActionListener {
	
	private Ventana_Planear_Proyecto ventana;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	
	public Ventana_Registrar_Tarea(Ventana_Planear_Proyecto padre) {
		ventana = padre;
		
		addNorthLabel();
		addTextField();
		addButtons();
		
		setSize(400,200);
		setTitle("Cambiar la fecha de fin del proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
