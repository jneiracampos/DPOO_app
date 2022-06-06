package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;
import javax.swing.GroupLayout.Alignment;
import modelo.Paquete;
import modelo.Tarea;

@SuppressWarnings("serial")
public class Ventana_Registrar_Paquete extends JFrame implements ActionListener {
	
	private ProyectTree arbol;
	private Ventana_Planear_Proyecto ventanaPlanear;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JPanel panelNorte;
	private JTextField txtFieldNombre;
	private JTextField txtFieldDescripcion;
	private JCheckBox documentacion;
	private JCheckBox implementacion;
	private JCheckBox pruebas;
	private JCheckBox investigacion;
	private JCheckBox diseño;
	private JCheckBox analisis;
	
	public Ventana_Registrar_Paquete(Ventana_Planear_Proyecto padre) {
		ventanaPlanear = padre;
		
		addNorthLabel();
		addTextField();
		addButtons();
		
		setSize(550,600);
		setTitle("Cambiar la fecha de fin del proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addNorthLabel() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);
		JLabel txt = new JLabel("Registrar un paquete");
		panelNorte.add(txt);

	}

	private void addTextField() {
		panelCentro = new JPanel();
		panelCentro.setOpaque(true);
		add(panelCentro, BorderLayout.CENTER);
		
		JLabel txtSolicitud = new JLabel("Ingrese los siguientes datos sobre el paquete...");
		JLabel txtNombre = new JLabel("Nombre:");
		JLabel txtDescripcion = new JLabel("Descripcion:");
		JLabel txtTipos = new JLabel("Seleccione:");
		documentacion = new JCheckBox("Documentación");
		implementacion = new JCheckBox("Implementacion");
		pruebas = new JCheckBox("Pruebas");
		investigacion = new JCheckBox("Investigacion");
		diseño = new JCheckBox("Diseño");
		analisis = new JCheckBox("Analisis");
		JLabel txtNull = new JLabel();
		JLabel txtNull1 = new JLabel();
		JLabel txtNull2 = new JLabel();
		JLabel txtNull3 = new JLabel();
		JLabel txtNull4 = new JLabel();
		JLabel txtNull5 = new JLabel();
		
		txtFieldNombre = new JTextField();
		txtFieldDescripcion = new JTextField();
		
		GroupLayout layout = new GroupLayout(panelCentro);
		panelCentro.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtSolicitud).addComponent(txtNombre).addComponent(txtDescripcion).addComponent(txtTipos).addComponent(txtNull1).addComponent(txtNull2).addComponent(txtNull3).addComponent(txtNull4).addComponent(txtNull5));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtNull).addComponent(txtFieldNombre).addComponent(txtFieldDescripcion).addComponent(documentacion).addComponent(implementacion).addComponent(pruebas).addComponent(investigacion).addComponent(diseño).addComponent(analisis));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtSolicitud).addComponent(txtNull));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNombre).addComponent(txtFieldNombre));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtDescripcion).addComponent(txtFieldDescripcion));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtTipos).addComponent(documentacion));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNull1).addComponent(implementacion));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNull2).addComponent(pruebas));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNull3).addComponent(investigacion));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNull4).addComponent(diseño));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(txtNull5).addComponent(analisis));
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
		ArrayList<String> tipos = new ArrayList<String>();
		
		if (comando.equals("Registrar")) {
			
			String nombrePaquete = txtFieldNombre.getText();
			String descripcionPaquete = txtFieldDescripcion.getText();
			
			if (documentacion.isSelected())
				tipos.add("Documentacion");
			else if (implementacion.isSelected())
				tipos.add("Implementacion");
			else if (pruebas.isSelected())
				tipos.add("Pruebas");
			else if (investigacion.isSelected())
				tipos.add("Investigacion");
			else if (diseño.isSelected())
				tipos.add("Diseño");
			else if (analisis.isSelected())
				tipos.add("Analisis");
			
			if (nombrePaquete.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar el nombre de la actividad", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else if (descripcionPaquete.equals("")) {
				JOptionPane.showMessageDialog(this, "Recuerde ingresar la descripcion de la actividad", "Aviso",
				JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Paquete paquete = Enrutador.getInstance().nuevoPaquete(nombrePaquete, descripcionPaquete, tipos);
				TreePath ruta = arbol.getRuta();
				if (ruta.getPathCount() == 1) {
					Enrutador.getInstance().getProyecto().addPaquete(paquete);
				}
				else {
					Paquete paquete1 = Enrutador.getInstance().getProyecto().getPaquete(ruta.getPathComponent(1).toString());
					
					for (int i=2; i<ruta.getPathCount(); i++) {
						paquete1 = paquete1.getPaquete(ruta.getPathComponent(i).toString());
					}
					paquete1.addPaquete(paquete);
				}
				setVisible(false);
				ventanaPlanear.setVisible(true);
			}
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
