package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.tree.TreePath;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;

import modelo.Paquete;
import modelo.Participante;
import modelo.Tarea;

@SuppressWarnings("serial")
public class Ventana_Registrar_Tarea extends JFrame implements ActionListener {
	
	private ProyectTree arbol;
	private Ventana_Planear_Proyecto ventanaPlanear;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldNombreTarea;
	private JTextField txtFieldDescripcionTarea;
	private JTextField txtFieldCorreoParticipante;
	private TimePicker tiempoPlaneado;
	private JDateChooser calendario;

	
	public Ventana_Registrar_Tarea(Ventana_Planear_Proyecto padre) {
		ventanaPlanear = padre;
		
		addTextField();
		addButtons();
		addNorthLabel();
		
		setSize(540, 270);
		setTitle("Cambiar la fecha de fin del proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);
		JLabel txt = new JLabel("Registrar una tarea");
		panelNorte.add(txt);

	}

	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtCorreoParticipante = new JLabel("Correo del participante:");
		JLabel txtSolicitud = new JLabel("Ingrese los siguientes datos sobre la tarea...");
		JLabel txtNombre = new JLabel("Nombre:");
		JLabel txtDescripcion = new JLabel("Descripcion:");
		JLabel txtFecha = new JLabel("Fecha de fin planeada:");
		JLabel txtTiempo = new JLabel("Tiempo planedo:");
		JLabel txtNull = new JLabel();
		
		txtFieldCorreoParticipante = new JTextField();
		txtFieldNombreTarea = new JTextField();
		txtFieldDescripcionTarea = new JTextField();
		calendario = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		tiempoPlaneado = new TimePicker();
		
		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtCorreoParticipante).addComponent(txtSolicitud).addComponent(txtNombre).addComponent(txtDescripcion).addComponent(txtFecha).addComponent(txtTiempo));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtFieldCorreoParticipante)).addComponent(txtNull).addComponent(txtFieldNombreTarea).addComponent(txtFieldDescripcionTarea).addComponent(calendario).addComponent(tiempoPlaneado);
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtCorreoParticipante).addComponent(txtFieldCorreoParticipante));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtSolicitud).addComponent(txtNull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNombre).addComponent(txtFieldNombreTarea));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtDescripcion).addComponent(txtFieldDescripcionTarea));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtFecha).addComponent(calendario));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtTiempo).addComponent(tiempoPlaneado));
		layout.setVerticalGroup(vGroup);
	}

	private void addButtons() {
		panelSur = new JPanel();
		panelSur.setOpaque(true);
		add(panelSur, BorderLayout.SOUTH);
		
		JButton btnRegistrar = new JButton("Registrar");
		JButton btnUbicacion = new JButton("Seleccionar ubicacion");
		JButton btnVolver = new JButton("Volver");
		
		panelSur.add(btnVolver);
		panelSur.add(btnUbicacion);
		panelSur.add(btnRegistrar);
		
		btnRegistrar.addActionListener(this);
		btnUbicacion.addActionListener(this);
		btnVolver.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Registrar")) {
			String nombreTarea = txtFieldNombreTarea.getText();
			String descripcionTarea = txtFieldDescripcionTarea.getText();
			LocalDate fecha = calendario.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalTime tiempoPlaneadoo = tiempoPlaneado.getTime();
			String correo = txtFieldCorreoParticipante.getText();
			Participante participante = Enrutador.getInstance().getProyecto().getParticipantePorCorreo(correo);
			Tarea tarea = Enrutador.getInstance().nuevaTarea(nombreTarea, descripcionTarea, correo, fecha, tiempoPlaneadoo, participante);
			
			TreePath ruta = arbol.getRuta();
			if (ruta.getPathCount() == 2) {
				Enrutador.getInstance().getProyecto().getPaquete(ruta.getPathComponent(1).toString()).addTarea(tarea);
			}
			
			setVisible(false);
			ventanaPlanear.setVisible(true);
		}
		else if (comando.equals("Seleccionar ubicacion")) {
			SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	arbol = new ProyectTree();
	            }
	        });
		}
		else if (comando.equals("Volver")) {
			setVisible(false);
			ventanaPlanear.setVisible(true);
		}
	}

}
