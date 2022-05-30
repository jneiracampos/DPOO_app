package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Paquete {

	private String nombre;
	private String descripcion;
	private ArrayList<String> tipos;
	/**
	 * HashMap que almacena las parejas (nombreTarea: Tarea)
	 */
	private HashMap<String,Tarea> tareas;

	//******************************************************************
	// Constructor
	//******************************************************************
	
	public Paquete(String nombrePaquete, String descripcionPaquete, ArrayList<String> tipos) {
		this.nombre = nombrePaquete;
		this.descripcion = descripcionPaquete;
		this.tipos = tipos;
		this.tareas = new HashMap<String,Tarea>();
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

	public Tarea getTarea(String nombreTarea) {
		return tareas.get(nombreTarea);
	}
	
	//******************************************************************
	// Otros metodos
	//******************************************************************
	
	public Tarea addTarea(String nombreTarea, String descripcionTarea, String tipo, LocalDate fechaPlaneada, LocalTime tiempoPlaneado, String nombreParticipante, String correoParticipante) {
		Participante participante = new Participante(nombreParticipante, correoParticipante);
		Tarea tarea = new Tarea(nombreTarea, descripcionTarea, tipo, fechaPlaneada, tiempoPlaneado, participante);
		tareas.put(tarea.getNombre(), tarea);
		return tarea;
	}
	
	public boolean isTipo(String tipo) {
		return tipos.contains(tipo);
	}
	
	public void addTipo(String tipo) {
		tipos.add(tipo);
	}

}
