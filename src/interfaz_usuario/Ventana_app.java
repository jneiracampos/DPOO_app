package interfaz_usuario;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Ventana_app extends JFrame {
	
	//private Registro registro;
	private Panel_Inicio panelInicio;

	public Ventana_app() {

		//Instanciar el panel de inicio
		panelInicio = new Panel_Inicio(this);

		setSize(500, 500);
		setTitle("Administrador de proyectos");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ventana_app();
	}

}
