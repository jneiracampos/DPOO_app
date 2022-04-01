package modelo;

public class Participante {
	/**
	 * El nombre de un participante del proyecto.
	 */
	private String nombre;
	/**
	 * El correo del participante del proyecto.
	 */
	private String correo;
	
	//******************************************************************
	// Constructor
	//******************************************************************
	
	/**
	 * Construye un nuevo participante e inicializa sus atributos con la 
	 * informacion de los parametros. Los HashMap se inicializan como un 
	 * HashMap vacio.
	 * 
	 * @param nombre El nombre del participante.
	 * @param correo El correo del participante.
	 */
	
	public Participante(String nombre, String correo) {
		this.nombre = nombre;
		this.correo = correo;
	}
	
	//******************************************************************
	// Metodos para consultar los atributos
	//******************************************************************

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}
	
	//******************************************************************
	// Otros metodos
	//******************************************************************
}
