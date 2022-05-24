package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;

import modelo.Proyecto;

@SuppressWarnings("serial")
public class Ventana_Buscar_Proyecto extends JFrame implements ActionListener {
	
	private Ventana_Menu_Principal ventanaMenuPrincipal;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldNombreProyecto;	

	public Ventana_Buscar_Proyecto(Ventana_Menu_Principal padre) {
		ventanaMenuPrincipal = padre;
		addTextField();
		addButtons();
		addNorthLabel();
		setSize(400, 200);
		setTitle("Buscar un proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txt = new JLabel("Buscar un proyecto");
		panelNorte.add(txt);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtSolicitar = new JLabel("Por favor ingrese el nombre del proyecto que desea buscar:");
		JLabel txtnull = new JLabel();
		txtFieldNombreProyecto = new JTextField();
		txtnull.setVisible(false);

		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtSolicitar).addComponent(txtFieldNombreProyecto));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtnull).addComponent(txtnull));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtSolicitar).addComponent(txtnull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtFieldNombreProyecto).addComponent(txtnull));
		layout.setVerticalGroup(vGroup);
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Buscar");
		JButton btnVolver = new JButton("Volver");
		
		panelSur.add(btnVolver);
		panelSur.add(btnAceptar);
		
		btnAceptar.addActionListener(this);
		btnVolver.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("Buscar")) {
			String nombreProyecto = txtFieldNombreProyecto.getText();
			if (Enrutador.isProyecto(nombreProyecto)) {
				Proyecto proyecto = Enrutador.getProyecto(nombreProyecto);
				Enrutador.setProyecto(proyecto);
				setVisible(false);
				new Ventana_Opciones(ventanaMenuPrincipal);
			}
			else {
				JOptionPane.showMessageDialog(this, "No se tiene registro de este proyecto", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}	
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaMenuPrincipal.setVisible(true);
		}
	}
}
