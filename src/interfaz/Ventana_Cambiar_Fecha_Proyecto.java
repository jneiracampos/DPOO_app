package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class Ventana_Cambiar_Fecha_Proyecto extends JFrame implements ActionListener {

	private Ventana_Planear_Proyecto ventana;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JDateChooser calendario;
	
	public Ventana_Cambiar_Fecha_Proyecto(Ventana_Planear_Proyecto padre) {
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

		JLabel txt = new JLabel("Cambiar la fecha de fin del proyecto");
		panelNorte.add(txt);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtsolicitud = new JLabel("Nueva fecha:");
		calendario = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');

		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtsolicitud));
		hGroup.addGroup(layout.createParallelGroup().addComponent(calendario));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtsolicitud).addComponent(calendario));
		layout.setVerticalGroup(vGroup);
		
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Actualizar");
		JButton btnVolver = new JButton("Volver");
		
		panelSur.add(btnVolver);
		panelSur.add(btnAceptar);
		
		btnAceptar.addActionListener(this);
		btnVolver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Actualizar")) {
			if (calendario.getDate() == null) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar la fecha de fin del proyecto", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				LocalDate fechaFin = calendario.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if (fechaFin.isAfter(LocalDate.now())) {
					Enrutador.getInstance().getProyecto().setFechaFin(fechaFin);
					setVisible(false);
					ventana.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(this, "Ingresa una fecha de fin que sea posterior a " + LocalDate.now(), "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventana.setVisible(true);
		}
	}
}
