package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Tarea {
	
	private String nombre;
	private String descripcion;
	private String tipo;
	private LocalDate fechaPlaneada;
	private LocalTime tiempoPlaneado;
	/**
	 * Fecha de finalización de la tarea
	 */
	private LocalDate fechaFin;
	/**
	 * Tiempo que tomo finalizar la tarea
	 */
	private Long tiempoReal;
	/**
	 * Verifica si la tarea se ha finalizado
	 */
	private Boolean finalizado;
	/**
	 * ArrayList que almacena los participantes de esta tarea
	 */
	private ArrayList<Participante> participantes;
	/**
	 * ArrayList que almacena las actividades de esta tarea
	 */
	private ArrayList<Actividad> actividades;
	/**
	 * HashMap que almacena las parejas (nombreParticipante: ArrayList(Actividad))
	 */
	private HashMap<String, ArrayList<Actividad>> actividadesPorParticipante;
	/**
	 * HashMap que almacena las parejas (nombreParticipante: HashMap(diaActividad: ArrayList(Actividad)))
	 */
	private HashMap<String, HashMap<LocalDate, ArrayList<Actividad>>> diaActividadPorParticipante;
	
	//******************************************************************
	// Estructuras de datos auxiliares
	//******************************************************************
	
	/**
	 * ArrayList que almacena objetos de tipo Actividad
	 */
	private ArrayList<Actividad> auxiliarActividades;
	/**
	 * HashMap que almacena las parejas (fechaActividad: arrayList(Actividad))
	 */
	private HashMap<LocalDate, ArrayList<Actividad>> auxiliarFechas;
	
	//******************************************************************
	// Constructor
	//******************************************************************
	
	public Tarea(String nombreTarea, String descripcionTarea, String tipo, LocalDate fechaPlaneada, LocalTime tiempoPlaneado, Participante participante) {
		this.nombre = nombreTarea;
		this.descripcion = descripcionTarea;
		this.tipo = tipo;
		this.fechaPlaneada = fechaPlaneada;
		this.tiempoPlaneado = tiempoPlaneado;
		this.finalizado = false;
		this.actividades = new ArrayList<Actividad>();
		this.actividadesPorParticipante = new HashMap<String, ArrayList<Actividad>>();
		this.diaActividadPorParticipante = new HashMap<String, HashMap<LocalDate, ArrayList<Actividad>>>();
		participantes.add(participante);	
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

	public String getTipo() {
		return tipo;
	}

	public LocalDate getFechaPlaneada() {
		return fechaPlaneada;
	}

	public LocalTime getTiempoPlaneado() {
		return tiempoPlaneado;
	}
	
	public Boolean getFinalizado() {
		return finalizado;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	
	//************************************************************************************
	// Metodos para almacenar una actividad
	//************************************************************************************
	
	private void addActividadPorParticipante(Actividad actividad) {
		if(actividadesPorParticipante.containsKey(actividad.getParticipanteActividad().getCorreo())) {
			auxiliarActividades = actividadesPorParticipante.get(actividad.getParticipanteActividad().getCorreo());
			auxiliarActividades.add(actividad);				
		}
		else {
			auxiliarActividades = new ArrayList<Actividad>();
			auxiliarActividades.add(actividad);
			actividadesPorParticipante.put(actividad.getParticipanteActividad().getCorreo(), auxiliarActividades);
		}
	}
	
	private void addDiaActividadPorParticipante(Actividad actividad) {
		if(diaActividadPorParticipante.containsKey(actividad.getParticipanteActividad().getCorreo())) {
			if(auxiliarFechas.containsKey(actividad.getFecha())) {
				auxiliarActividades = auxiliarFechas.get(actividad.getFecha());
				auxiliarActividades.add(actividad);
			}
			else {
				auxiliarActividades = new ArrayList<Actividad>();
				auxiliarActividades.add(actividad);
				auxiliarFechas.put(actividad.getFecha(), auxiliarActividades);
			}
		}
		else {
			auxiliarFechas = new HashMap<LocalDate, ArrayList<Actividad>>();
			auxiliarActividades = new ArrayList<Actividad>();
			auxiliarActividades.add(actividad);
			auxiliarFechas.put(actividad.getFecha(), auxiliarActividades);
			diaActividadPorParticipante.put(actividad.getParticipanteActividad().getCorreo(), auxiliarFechas);
		}
	}
	
	public void addActividad(Actividad actividad) {
		actividades.add(actividad);
		addActividadPorParticipante(actividad);
		addDiaActividadPorParticipante(actividad);
		finalizarTarea(actividad);
	}
	
	//************************************************************************************
	// Metodos para consultar una actividad
	//************************************************************************************
	
	public ArrayList<Actividad> getActividadPorParticipante(String correoParticipante) {
		return actividadesPorParticipante.get(correoParticipante);
	}
	
	public ArrayList<Actividad> getDiaActividadPorParticipante(String correoParticipante, LocalDate fechaActividad) {
		return diaActividadPorParticipante.get(correoParticipante).get(fechaActividad);
	}
	
	//************************************************************************************
	// Otros metodos
	//************************************************************************************
	
	private void finalizarTarea(Actividad actividad) {
		if (actividad.getFinalizar().equals(true)) {
			this.finalizado = true;
			this.fechaFin = actividad.getFecha();
			for (int i = 0; i < actividades.size(); i++) {
				tiempoReal += actividades.get(i).getTiempoTotal();
			}
		}
	}
	
}
