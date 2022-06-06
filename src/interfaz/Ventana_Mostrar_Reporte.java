package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.Participante;
import procesamiento.Reporte_Participante;

@SuppressWarnings("serial")
public class Ventana_Mostrar_Reporte extends JFrame implements ActionListener {
	
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	
	public Ventana_Mostrar_Reporte(Reporte_Participante reporte) {
		
		addNorthLabel();
		addTextField(reporte);
		addButtons();
		
		setSize(500,300);
		setTitle("Consultar el reporte de un participante");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txt = new JLabel("Consultar el reporte de un participante");
		panelNorte.add(txt);
	}
	
	private void addTextField(Reporte_Participante reporte) {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		panelCentro.setLayout(new GridLayout(8, 1, 0, 8));
		add(panelCentro, BorderLayout.CENTER);
		JLabel txt = new JLabel();
		JLabel txt1 = new JLabel();
		JLabel txt2 = new JLabel();
		JLabel txt3 = new JLabel();
		JLabel txt4 = new JLabel();
		JLabel txt5 = new JLabel();
		JLabel txt6 = new JLabel();
		JLabel txt7 = new JLabel();
		
		String nombreParticipante = reporte.getParticipante().getNombre();
		txt.setText("Se le generó al participante llamado " + nombreParticipante + " el siguiente reporte:");
		txt1.setText(nombreParticipante + " realizó " + String.valueOf(reporte.getCantidadActividades()) + " actividades.");
		txt2.setText("Realizar estas actividades le tomó en total " + String.valueOf(reporte.getTiempoTotal()) + " minutos.");
		txt3.setText("Realizar estas actividades le tomó en promedio " + String.valueOf(reporte.getTiempoPromedio()) + " minutos.");
		txt6.setText(nombreParticipante + " realizó " + String.valueOf(reporte.getCantidadDiaActividades()) + " actividades en la fecha " + reporte.getFechaActividad() + ".");
		txt7.setText("Realizar estas actividades le tomó en total " + String.valueOf(reporte.getTiempoDiaActividad()) + " minutos.");
		
		panelCentro.add(txt);
		panelCentro.add(txt1);
		panelCentro.add(txt2);
		panelCentro.add(txt3);
		panelCentro.add(txt4);
		panelCentro.add(txt5);
		panelCentro.add(txt6);
		panelCentro.add(txt7);
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		panelSur.add(btnVolver);
		btnVolver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("Volver")) {
			setVisible(false);
		}		
	}

}
