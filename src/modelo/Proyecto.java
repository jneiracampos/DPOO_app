package modelo;

import java.time.LocalDate;

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
	}
	
	//******************************************************************
	// Metodos para consultar los atributos
	//******************************************************************

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
	
	//******************************************************************
	// Otros metodos
	//******************************************************************
}
