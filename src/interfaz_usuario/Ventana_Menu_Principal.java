package interfaz_usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Ventana_Menu_Principal extends JFrame implements ActionListener {
	
	private Ventana_Inicio panelInicio;
	
	
	public Ventana_Menu_Principal(Ventana_Inicio padre) {
		panelInicio = padre;
		
		System.out.println(panelInicio.getUsuario().getCorreo());
		
		setSize(500, 500);
		setTitle("Administrador de proyectos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
