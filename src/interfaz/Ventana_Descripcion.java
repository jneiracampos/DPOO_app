package interfaz;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class Ventana_Descripcion extends JFrame implements ActionListener {
	
	private Ventana_Opciones ventanaOpciones;
	private JPanel panelNorte;
	private JPanel panel1;
	private JPanel panel2;
	private JTextField txtDescProyecto;
	
	public Ventana_Descripcion(Ventana_Opciones padre) {
		ventanaOpciones = padre;
		
		addPanelNorte();
		addPanel1();
		addPanel2();
		
		setSize(400,200);
		setTitle("Cambiar la descripcion del proyecto");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addPanelNorte() {
		panelNorte = new JPanel();
		panelNorte.setOpaque(true);
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setPreferredSize(new Dimension(400,40));
		JLabel txt = new JLabel("Cambiar descripcion");
		panelNorte.add(txt);
		
	}
	private void addPanel1() {
		panel1 = new JPanel();
		panel1.setOpaque(true);
		add(panel1, BorderLayout.CENTER);
		
		//agregar elementos al panel
		JLabel descProyecto = new JLabel("Nueva descripcion del proyecto:");
		txtDescProyecto = new JTextField(10);
		panel1.add(descProyecto);
		panel1.add(txtDescProyecto);
		
		//Ajustar Layout del panel
		GroupLayout layout = new GroupLayout(panel1);
		panel1.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(descProyecto));
		hGroup.addGroup(layout.createParallelGroup().addComponent(txtDescProyecto));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(descProyecto).addComponent(txtDescProyecto));
		layout.setVerticalGroup(vGroup);
		
	}
	
	private void addPanel2() {
		panel2 = new JPanel();
		panel2.setOpaque(true);
		add(panel2, BorderLayout.SOUTH);
		
		//agregar elementos al panel
		JButton volver = new JButton("Volver");
		JButton actualizar = new JButton("Actualizar");
		panel2.add(volver);
		panel2.add(actualizar);
		volver.addActionListener(this);
		actualizar.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("Volver")) {
			setVisible(false);
			ventanaOpciones.setVisible(true);
		}
		else if (comando.equals("Actualizar")){
			String descProyecto = txtDescProyecto.getText();
			Enrutador.getProyecto().setDescripcion(descProyecto);
			ventanaOpciones.setVisible(true);
			setVisible(false);
		}
	}
}
