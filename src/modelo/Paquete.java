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
	 * HashMap que almacena las parejas (nombrePaquete: Paquete)
	 */
	private HashMap<String,Paquete> paquetes;
	/**
	 * HashMap que almacena las parejas (nombreTarea: Tarea)
	 */
	private HashMap<String,Tarea > tareas;

	//******************************************************************
	// Constructor
	//******************************************************************
	
	public Paquete(String nombrePaquete, String descripcionPaquete, ArrayList<String> tipos) {
		this.nombre = nombrePaquete;
		this.descripcion = descripcionPaquete;
		this.tipos = tipos;
		this.paquetes = new HashMap<String,Paquete>();
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
	
	public Paquete getPaquete(String nombrePaquete) {
		return paquetes.get(nombrePaquete);
	}
	
	public HashMap<String,Paquete> getPaquetes() {
		return paquetes;
	}
	
	public HashMap<String,Tarea> getTareas() {
		return tareas;
	}

	public Tarea getTarea(String nombreTarea) {
		return tareas.get(nombreTarea);
	}
	
	//******************************************************************
	// Metodos para agregar una tarea o un paquete
	//******************************************************************
	
	public Tarea addTarea(Tarea tarea) {
		tareas.put(tarea.getNombre(), tarea);
		return tarea;
	}
	
	public Paquete addPaquete(Paquete paquete) {
		paquetes.put(paquete.getNombre(), paquete);
		return paquete;
	}
	
	//******************************************************************
	// Otros metodos
	//******************************************************************
	
	public boolean isEmptyPaquetes() {
		return paquetes.isEmpty();
	}
	
	public boolean isEmptyTareas() {
		return tareas.isEmpty();
	}
	
	public boolean isTipo(String tipo) {
		return tipos.contains(tipo);
	}
	
	public void addTipo(String tipo) {
		tipos.add(tipo);
	}

}
