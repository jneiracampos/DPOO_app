package interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import modelo.Proyecto;

@SuppressWarnings("serial")
public class Ventana_Buscar_Proyecto extends JFrame implements ActionListener {
	
	private Proyecto proyecto;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldNombreProyecto;	

	
	public Ventana_Buscar_Proyecto() {
		addTextField();
		addButtons();
		addNorthLabel();
		setSize(500, 500);
		setTitle("Administrador de proyectos");
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
		
		JButton btnAceptar = new JButton("Aceptar");
		JButton btnBorrar = new JButton("Borrar");
		
		panelSur.add(btnAceptar);
		panelSur.add(btnBorrar);
		
		btnAceptar.addActionListener(this);
		btnBorrar.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Aceptar")) {
			String nombreProyecto = txtFieldNombreProyecto.getText();
			if (Registro.isProyecto(nombreProyecto)) {
				proyecto = Registro.getProyecto(nombreProyecto);
				setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(this, "No se tiene registro de este proyecto", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
			}	
		}
		else if (comando.equals("Borrar")){
			txtFieldNombreProyecto.setText(" ");
		}
	}
	
	public Proyecto getProyecto() {
		return proyecto;
	}
	
}
