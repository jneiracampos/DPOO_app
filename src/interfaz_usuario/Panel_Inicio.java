package interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Panel_Inicio extends JPanel implements ActionListener {
	
	private Ventana_app ventana_app;
	private JTextField txtFieldNombre;
	private JTextField txtFieldCorreo;
	private JButton btnAceptar;
	private JButton btnBorrar;
	
	public Panel_Inicio(Ventana_app padre) {
		ventana_app = padre;
		addNorthLabel();
		addTextField();
		addBottons();
		
	}
	
	private void addNorthLabel() {
		JPanel panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		ventana_app.add(panelNorte, BorderLayout.NORTH);
		
		JLabel txtBienvenida = new JLabel("¡Bienvenido a la aplicación!");
		panelNorte.add(txtBienvenida);
	}
	
	private void addTextField() {
		ventana_app.add(this, BorderLayout.CENTER);
		
		JLabel txtSolicitar = new JLabel("Por favor ingrese los siguientes datos:");
		JLabel txtnull = new JLabel();
		txtnull.setVisible(false);

		JLabel txtNombre = new JLabel("Nombre:");
		JLabel txtCorreo = new JLabel("Correo:");
		txtFieldNombre = new JTextField();
		txtFieldCorreo = new JTextField();
		add(txtSolicitar);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtSolicitar).addComponent(txtNombre).addComponent(txtCorreo));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtnull).addComponent(txtFieldNombre).addComponent(txtFieldCorreo));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtSolicitar).addComponent(txtnull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNombre).addComponent(txtFieldNombre));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtCorreo).addComponent(txtFieldCorreo));
		layout.setVerticalGroup(vGroup);

	}
	
	private void addBottons() {
		JPanel panelSur = new JPanel();
		panelSur.setOpaque(true);
		ventana_app.add(panelSur, BorderLayout.SOUTH);
		
		btnAceptar = new JButton("Aceptar");
		btnBorrar = new JButton("Borrar");
		
		panelSur.add(btnAceptar);
		panelSur.add(btnBorrar);
		
		btnAceptar.addActionListener(this);
		btnBorrar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Aceptar")) {
			String nombre = btnAceptar.getText();
			String correo = btnAceptar.getText();
		}
		else if (comando.equals("Borrar")){
			txtFieldNombre.setText(" ");
			txtFieldCorreo.setText(" ");
		}
		
	}
	
}
