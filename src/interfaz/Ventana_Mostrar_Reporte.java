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

@SuppressWarnings("serial")
public class Ventana_Mostrar_Reporte extends JFrame implements ActionListener {
	
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	
	public Ventana_Mostrar_Reporte(long tiempoTotal, int cantidadActividades, long tiempoTipoActividad, int cantidadTipoActividad, long tiempoDiaActividad, int cantidadDiaActividad, double tiempoPromedio, Participante participante, String tipo, LocalDate fecha) {
		
		addNorthLabel();
		addTextField(tiempoTotal,cantidadActividades,tiempoTipoActividad,cantidadTipoActividad,tiempoDiaActividad,cantidadDiaActividad,tiempoPromedio,participante,tipo,fecha);
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
	
	private void addTextField(long tiempoTotal, int cantidadActividades, long tiempoTipoActividad, int cantidadTipoActividad, long tiempoDiaActividad, int cantidadDiaActividad, double tiempoPromedio, Participante participante, String tipo, LocalDate fecha) {
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
		
		txt.setText("Se le generó al participante llamado " + participante.getNombre() + " el siguiente reporte:");
		txt1.setText(participante.getNombre() + " realizó " + String.valueOf(cantidadActividades) + " actividades.");
		txt2.setText("Realizar estas actividades le tomó en total " + String.valueOf(tiempoTotal) + " minutos.");
		txt3.setText("Realizar estas actividades le tomó en promedio " + String.valueOf(tiempoPromedio) + " minutos.");
		txt4.setText(participante.getNombre() + " realizó " + String.valueOf(cantidadTipoActividad) + " actividades de tipo " + tipo + ".");
		txt5.setText("Realizar estas actividades le tomó en total " + String.valueOf(tiempoTipoActividad) + " minutos.");
		txt6.setText(participante.getNombre() + " realizó " + String.valueOf(cantidadDiaActividad) + " actividades en la fecha " + fecha + ".");
		txt7.setText("Realizar estas actividades le tomó en total " + String.valueOf(tiempoDiaActividad) + " minutos.");
		
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
