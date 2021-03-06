package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;

import modelo.Participante;

@SuppressWarnings("serial")
public class Ventana_Inicio extends JFrame implements ActionListener {
	
	private JTextField txtFieldNombre;
	private JTextField txtFieldCorreo;	
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelCentro;
	private Participante usuario;

	public Ventana_Inicio() {
		addNorthLabel();
		addTextField();
		addButtons();
		
		setSize(400, 400);
		setTitle("Administrador de proyectos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txtBienvenida = new JLabel("Bienvenido a la aplicacion!");
		panelNorte.add(txtBienvenida);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtSolicitar = new JLabel("Por favor ingresa los siguientes datos:");
		JLabel txtnull = new JLabel();
		txtnull.setVisible(false);

		JLabel txtNombre = new JLabel("Nombre:");
		JLabel txtCorreo = new JLabel("Correo:");
		txtFieldNombre = new JTextField();
		txtFieldCorreo = new JTextField();

		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
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
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Aceptar");
		JButton btnBorrar = new JButton("Borrar");
		
		panelSur.add(btnBorrar);
		panelSur.add(btnAceptar);
		
		btnAceptar.addActionListener(this);
		btnBorrar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Aceptar")) {
			String nombreParticipante = txtFieldNombre.getText();
			String correoParticipante = txtFieldCorreo.getText();
			if (nombreParticipante.equals("") || correoParticipante.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerda ingresar su nombre y correo", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				usuario = Enrutador.getInstance().nuevoParticipante(nombreParticipante, correoParticipante);
				setVisible(false);
				new Ventana_Menu_Principal(this);
			}	
		}
		else if (comando.equals("Borrar")){
			txtFieldNombre.setText(" ");
			txtFieldCorreo.setText(" ");
		}
	}
	
	public Participante getUsuario() {
		return usuario;
	}
		
	public static void main(String[] args) {
		new Ventana_Inicio();
	}

}
