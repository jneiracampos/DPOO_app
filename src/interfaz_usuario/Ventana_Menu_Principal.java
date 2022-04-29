package interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class Ventana_Menu_Principal extends JFrame implements ActionListener {
	
	private JPanel panelCentro;
	private JPanel panelNorte;
	
	public Ventana_Menu_Principal() {
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

		JLabel txt = new JLabel("Opciones de la aplicacion");
		panelNorte.add(txt);
	}
	
	private void addButtons() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);

		JButton btnCargar = new JButton("Cargar un proyecto del disco local");
		JButton btnCrear = new JButton("Crear proyecto");
		JButton btnBuscar = new JButton("Buscar proyecto");
		JButton btnSalir = new JButton("Salir de la aplicacion");
		JLabel txtUsuario = new JLabel("Por favor seleccione una de las siguientes opciones:");
		JLabel txtnull = new JLabel();
		txtnull.setVisible(false);
		
		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtUsuario).addComponent(btnCargar).addComponent(btnCrear).addComponent(btnBuscar).addComponent(btnSalir));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtnull).addComponent(txtnull).addComponent(txtnull).addComponent(txtnull).addComponent(txtnull));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtUsuario).addComponent(txtnull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btnCargar).addComponent(txtnull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btnCrear).addComponent(txtnull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btnBuscar).addComponent(txtnull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btnSalir).addComponent(txtnull));
		layout.setVerticalGroup(vGroup);
		
		btnCargar.addActionListener(this);
		btnCrear.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnSalir.addActionListener(this);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("Cargar un proyecto del disco local")) {
			setVisible(false);
		}
		else if (comando.equals("Crear proyecto")){
			setVisible(false);
		}
		else if (comando.equals("Buscar proyecto")) {
			setVisible(false);
			new Ventana_Buscar_Proyecto();
		}
		else if (comando.equals("Salir de la aplicacion")) {
			System.exit(0);
		}
	}

}
