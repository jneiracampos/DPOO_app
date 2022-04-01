package modelo;

import java.time.LocalDate;
import java.util.HashMap;

public class Proyecto {
	/**
	 * El nombre del proyecto.
	 */
	private String nombre;
	/**
	 * La descripcion del proyecto.
	 */
	private String descripcion;
	/**
	 * La fecha de inicio del proyecto.
	 */
	private LocalDate fechaInicio;
	/**
	 * La fecha de fin del proyecto.
	 */
	private LocalDate fechaFin;
	/**
	 * El participante creador del proyecto.
	 */
	private Participante participante;
	/**
	 * HashMap que almacena las parejas (nombreParticipante: correoParticipante)
	 */
	private HashMap<String, String> nombres;
	/**
	 * HashMap que almacena las parejas (correoParticipante: nombreParticipante)
	 */
	private HashMap<String, String> correos;
	/**
	 * HashMap que almacena las parejas (nombreActividad: actividad)
	 */
	private HashMap<String, Actividad> actividades;
	
	//******************************************************************
	// Constructor
	//******************************************************************
	
	/**
	 * Construye un nuevo proyecto e inicializa sus atributos con la 
	 * informacion de los parametros.
	 * 
	 * @param nombre El nombre del proyecto.
	 * @param descripcion La descripcion del proyecto.
	 * @param fechaInicio La fecha de inicio del proyecto.
	 * @param fechaFin La fecha de fin del proyecto.
	 * @param participante El participante que creó el proyecto.
	 */

	public Proyecto(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Participante participanteInicial) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.participante = participanteInicial;
		this.nombres = new HashMap<String, String>();
		this.correos = new HashMap<String, String>();
		this.actividades = new HashMap<String, Actividad>();
	}
	
	//************************************************************************************
	// Metodos para consultar los atributos
	//************************************************************************************

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public Participante getParticipante() {
		return participante;
	}
	
	//************************************************************************************
	// Metodos para almacenar otros participantes a una instancia de Proyecto
	//************************************************************************************
	
	public void addOtroParticipante(Participante otroParticipante) {
		nombres.put(otroParticipante.getNombre(), otroParticipante.getCorreo());
		correos.put(otroParticipante.getCorreo(), otroParticipante.getNombre());
	}
	
	public String getNombreOtroParticipante(String correo) {
		return correos.get(correo);
	}
	
	public String getCorreoOtroParticipante(String nombre) {
		return nombres.get(nombre);
	}
	
	//************************************************************************************
	// Metodos para almacenar actividades a una instancia de Proyecto
	//************************************************************************************
	
	public void addActividad(Actividad actividad) {
		actividades.put(actividad.getNombre(), actividad);
	}
	
	public Actividad getActividad(String nombreActividad) {
		return actividades.get(nombreActividad);
	}
}
