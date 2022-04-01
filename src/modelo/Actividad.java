package modelo;

import java.time.LocalDate;

public class Actividad {
	/**
	 * El nombre de la actividad.
	 */
	private String nombre;
	/**
	 * La descripcion de la actividad.
	 */
	private String descripcion;
	/**
	 * El tipo de actividad.
	 */
	private String tipo;
	/**
	 * La fecha en que se realizó la actividad.
	 */
	private LocalDate fecha;
	/**
	 * El tiempo total invertido en esta actividad.
	 */
	//private int tiempoTotal; COMENTADO PARA EVITAR LA ADVERTENCIA
	
	//******************************************************************
	// Constructor
	//******************************************************************
		
	public Actividad(String nombre, String descripcion, String tipo, LocalDate fecha) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fecha = fecha;
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

	public String getTipo() {
		return tipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	
	//******************************************************************
	// Otros metodos
	//******************************************************************
}