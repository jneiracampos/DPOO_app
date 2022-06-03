package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;

import modelo.Actividad;

@SuppressWarnings("serial")
public class Ventana_Cronometro extends JFrame implements ActionListener {
	
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private Timer timer;
	private JLabel txtFieldTiempo;
	private int tiempo = 0;
	private Actividad actividadUsuario;
	private Ventana_Opciones ventanaOpciones;

	public Ventana_Cronometro(Actividad actividad, Ventana_Opciones opciones) {
		ventanaOpciones = opciones;
		actividadUsuario = actividad;
		
		addNorthLabel();
		addTextField();
		addButtons();
		
		setSize(350, 300);
		setTitle("");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);

		JLabel txt = new JLabel("Cronometro");
		panelNorte.add(txt);
	}
	
	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtTiempo = new JLabel("Tiempo:");
		txtFieldTiempo = new JLabel("0 minutos");

		JButton iniciarCronometro = new JButton("Iniciar el cronometro");
		JButton pausarCronometro = new JButton("Pausar el cronometro");
		iniciarCronometro.addActionListener(this);
		pausarCronometro.addActionListener(this);
				
		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtTiempo).addComponent(iniciarCronometro));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldTiempo).addComponent(pausarCronometro));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtTiempo).addComponent(txtFieldTiempo));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(iniciarCronometro).addComponent(pausarCronometro));
		layout.setVerticalGroup(vGroup);
	}
	
	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Finalizar la actividad");
		panelSur.add(btnVolver);
		btnVolver.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("Iniciar el cronometro")) {
			timer = new Timer(60000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					tiempo += 1;
					txtFieldTiempo.setText(String.valueOf(tiempo) + " minutos");
				}
			});	
			timer.start();
		}
		else if (comando.equals("Pausar el cronometro")) {
			timer.stop();
			actividadUsuario.addTiempo(actividadUsuario.getHoraInicio(), LocalTime.now());
		}
		else if (comando.equals("Finalizar la actividad")) {
			timer.stop();
			actividadUsuario.addTiempo(actividadUsuario.getHoraInicio(), LocalTime.now());
			actividadUsuario.setHoraFin(LocalTime.now());
			//Enrutador.getInstance().getProyecto().addActividad(actividadUsuario);
			setVisible(false);
			ventanaOpciones.setVisible(true);
		}
	}

}
