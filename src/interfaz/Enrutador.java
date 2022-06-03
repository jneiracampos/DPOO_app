package interfaz;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.Participante;
import modelo.Proyecto;
import modelo.Tarea;
import procesamiento.Administrador_Datos;
import procesamiento.Reporte_Participante;
import modelo.Actividad;
import modelo.Paquete;

public class Enrutador {
	
	/**
	 * Proyecto con el que se esta trabajando
	 */
	private Proyecto proyecto;
	/**
	 * ArrayList que almacena todos los proyectos
	 */
	private HashMap<String, Proyecto> proyectos = new HashMap<String, Proyecto>();

	//***************************************************************************************
	// Patron de diseño Singleton
	//***************************************************************************************
	
	private static Enrutador single_instance = null;
	
	private Enrutador() {
		
	}
	
	public static Enrutador getInstance() {
		if (single_instance == null)
			single_instance = new Enrutador();
		return single_instance;
	}
	
	//***************************************************************************************
	// Constructores
	//***************************************************************************************
	
	public Proyecto nuevoProyecto(String nombreProyecto, String descripcionProyecto, LocalDate fechaInicio, LocalDate fechaFin, Participante participante) {
		proyecto = new Proyecto(nombreProyecto, descripcionProyecto, fechaInicio, fechaFin, participante);
		proyectos.put(nombreProyecto, proyecto);
		return proyecto;
	}
	
	public Participante nuevoParticipante(String nombre, String correo) {
		Participante participante = new Participante(nombre, correo);
		return participante;
	}
	
	public Paquete nuevoPaquete(String nombrePaquete, String descripcionPaquete, ArrayList<String> tipos) {
		Paquete paquete = new Paquete(nombrePaquete, descripcionPaquete, tipos);
		return paquete;
	}
	
	public Tarea nuevaTarea(String nombreTarea, String descripcionTarea, String tipo, LocalDate fechaPlaneada, LocalTime tiempoPlaneado, String nombreParticipante, String correoParticipante) {
		Participante participante = new Participante(nombreParticipante, correoParticipante);
		Tarea tarea = new Tarea(nombreTarea, descripcionTarea, tipo, fechaPlaneada, tiempoPlaneado, participante);
		return tarea;
	}
	
	public Actividad nuevaActividad(String nombre, String descripcion, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Boolean finaliza, Participante participante) {
		Actividad actividad = new Actividad(nombre, descripcion, fecha, horaInicio, horaFin, finaliza, participante);
		return actividad;
	}
	
	//***************************************************************************************
	// Metodos para cargar o generar un archivo que almecena un proyecto
	//***************************************************************************************
	
	public void cargarArchivo(String nombreProyecto) throws Throwable {
		Administrador_Datos singleton = Administrador_Datos.getInstance();
		proyecto = singleton.cargarArchivo(nombreProyecto);
	}
	
	public void generarArchivo(Proyecto proyecto) throws Throwable {
		Administrador_Datos singleton = Administrador_Datos.getInstance();
		singleton.generarArchivo(proyecto);
	}
	
	//***************************************************************************************
	// Metodo para generar el reporte de un participante
	//***************************************************************************************
	
	public Reporte_Participante generarReporte(Proyecto proyecto, String correoParticipante, String[] paquetes, String nombreTarea, LocalDate fechaActividad) {
		Reporte_Participante reporte = new Reporte_Participante(proyecto, correoParticipante, paquetes, nombreTarea, fechaActividad);
		return reporte;
	}
	
	//***************************************************************************************
	// Metodos de proyecto
	//***************************************************************************************

	public Proyecto getProyecto() {
		return proyecto;
	}
	
	public void setProyecto(Proyecto nuevoProyecto) {
		proyecto = nuevoProyecto;
	}
	
	public boolean isProyecto(String nombreProyecto) {
		return proyectos.containsKey(nombreProyecto);
	}
	
	public Proyecto getProyecto(String nombreProyecto) {
		return proyectos.get(nombreProyecto);
	}
}

