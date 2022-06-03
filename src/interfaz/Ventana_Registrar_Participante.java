package interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;

import modelo.Participante;

@SuppressWarnings("serial")
public class Ventana_Registrar_Participante extends JFrame implements ActionListener {
	
	private Ventana_Opciones ventanaOpciones;
	private JTextField txtFieldNombre;
	private JTextField txtFieldCorreo;	
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelCentro;
	private Participante participante;

	public Ventana_Registrar_Participante(Ventana_Opciones padre) {
		ventanaOpciones = padre;
		
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

		JLabel txtBienvenida = new JLabel("Registrar un participante");
		panelNorte.add(txtBienvenida);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtSolicitar = new JLabel("Ingresa los datos del nuevo participante:");
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
		
		JButton btnVolver = new JButton("Volver");
		JButton btnBorrar = new JButton("Borrar");
		JButton btnRegistrar = new JButton("Registrar");
		
		panelSur.add(btnVolver);
		panelSur.add(btnBorrar);
		panelSur.add(btnRegistrar);
		
		btnVolver.addActionListener(this);
		btnBorrar.addActionListener(this);
		btnRegistrar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		String nombreParticipante = txtFieldNombre.getText();
		String correoParticipante = txtFieldCorreo.getText();
		
		if (comando.equals("Registrar")) {
			if (nombreParticipante.equals("") || correoParticipante.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar el nombre y correo del participante", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else if (Enrutador.getInstance().getProyecto().isParticipantePorCorreo(correoParticipante) || Enrutador.getInstance().getProyecto().getParticipante().getCorreo().equals(correoParticipante)) {
				JOptionPane.showMessageDialog(this, "Ya existe un participante con este correo", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				participante = Enrutador.getInstance().nuevoParticipante(nombreParticipante, correoParticipante);
				Enrutador.getInstance().getProyecto().addParticipante(participante);
				setVisible(false);
				ventanaOpciones.setVisible(true);
			}	
		}
		else if (comando.equals("Borrar")){
			txtFieldNombre.setText("");
			txtFieldCorreo.setText("");
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaOpciones.setVisible(true);
		}
	}
	
}
