package modelo;

import java.util.HashMap;

public class Participante {
	/**
	 * El nombre de un participante del proyecto.
	 */
	private String nombre;
	/**
	 * El correo del participante del proyecto.
	 */
	private String correo;
	/**
	 * HashMap que almacena las parejas (nombre: correo)
	 */
	private HashMap<String, String> nombres;
	/**
	 * HashMap que almacena las parejas (correo: nombre)
	 */
	private HashMap<String, String> correos;
	
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
		this.nombres = new HashMap<String, String>();
		this.correos = new HashMap<String, String>();
		nombres.put(nombre, correo);
		correos.put(correo, nombre);
	}
	
	//******************************************************************
	// Metodos para consultar los atributos
	//******************************************************************

	public String getNombre() {
		return nombre;
	}
	
	public String getUnNombre(String uncorreo) {
		return correos.get(uncorreo);
	}

	public String getCorreo() {
		return correo;
	}
	
	public String getUnCorreo(String unnombre) {
		return nombres.get(unnombre);
	}
	
	//******************************************************************
	// Otros metodos
	//******************************************************************
	
}
