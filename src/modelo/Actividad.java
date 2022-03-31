package modelo;

import java.time.LocalDate;

public class Actividad {
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
	private String tipo;
	/**
	 * La fecha de fin del proyecto.
	 */
	private LocalDate fecha;
	/**
	 * El participante creador del proyecto.
	 */
	private int tiempoTotal;
	
	//******************************************************************
	// Constructor
	//******************************************************************
		
	public Actividad(String nombre, String descripcion, String tipo, LocalDate fecha, int tiempoTotal) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fecha = fecha;
		this.tiempoTotal = tiempoTotal;
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

	public int getTiempoTotal() {
		return tiempoTotal;
	}
	
	//******************************************************************
	// Otros metodos
	//******************************************************************
}