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
	 * ArrayList que almacena los participantes de esta tarea
	 */
	private ArrayList<Participante> participantes;
	/**
	 * HashMap que almacena las parejas (nombreActividad: ArrayList(Actividad))
	 */
	private HashMap<String, ArrayList<Actividad>> actividades;
	/**
	 * HashMap que almacena las parejas (nombreParticipante: HashMap(diaActividad: ArrayList(Actividad)))
	 */
	private HashMap<String, HashMap<LocalDate, ArrayList<Actividad>>> diaActividadPorParticipante;
	/**
	 * HashMap que almacena las parejas (nombreParticipante: HashMap(tipoActividad: ArrayList(Actividad)))
	 */
	private HashMap<String, HashMap<String, ArrayList<Actividad>>> tipoActividadesPorParticipante;
	
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
	/**
	 * HashMap que almacena las parejas (tipoActividad: arrayList(Actividad))
	 */
	private HashMap<String, ArrayList<Actividad>> auxiliarTipos;
	
	//******************************************************************
	// Constructor
	//******************************************************************
	
	public Tarea(String nombreTarea, String descripcionTarea, String tipo, LocalDate fechaPlaneada, LocalTime tiempoPlaneado, Participante participante) {
		this.nombre = nombreTarea;
		this.descripcion = descripcionTarea;
		this.tipo = tipo;
		this.fechaPlaneada = fechaPlaneada;
		this.tiempoPlaneado = tiempoPlaneado;
		this.actividades = new HashMap<String, ArrayList<Actividad>>();
		this.diaActividadPorParticipante = new HashMap<String, HashMap<LocalDate, ArrayList<Actividad>>>();
		this.tipoActividadesPorParticipante = new HashMap<String, HashMap<String, ArrayList<Actividad>>>();
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
	
	//************************************************************************************
	// Metodos para almacenar una actividad
	//************************************************************************************
	
	private void addActividadPorParticipante(Actividad actividad) {
		if(actividades.containsKey(actividad.getParticipanteActividad().getCorreo())) {
			auxiliarActividades = actividades.get(actividad.getParticipanteActividad().getCorreo());
			auxiliarActividades.add(actividad);				
		}
		else {
			auxiliarActividades = new ArrayList<Actividad>();
			auxiliarActividades.add(actividad);
			actividades.put(actividad.getParticipanteActividad().getCorreo(), auxiliarActividades);
		}
		//actividadesAlmacenamiento.add(actividad);
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
	
	private void addTipoActividadesPorParticipante(Actividad actividad) {
		if(tipoActividadesPorParticipante.containsKey(actividad.getParticipanteActividad().getCorreo())) {
			if(auxiliarTipos.containsKey(actividad.getTipo())) {
				auxiliarActividades = auxiliarTipos.get(actividad.getTipo());
				auxiliarActividades.add(actividad);
			}
			else {
				auxiliarActividades = new ArrayList<Actividad>();
				auxiliarActividades.add(actividad);
				auxiliarTipos.put(actividad.getTipo(), auxiliarActividades);
			}
		}
		else {
			auxiliarTipos = new HashMap<String, ArrayList<Actividad>>();
			auxiliarActividades = new ArrayList<Actividad>();
			auxiliarActividades.add(actividad);
			auxiliarTipos.put(actividad.getTipo(), auxiliarActividades);
			tipoActividadesPorParticipante.put(actividad.getParticipanteActividad().getCorreo(), auxiliarTipos);
		}
	}
	
	public void addActividad(Actividad actividad) {
		addActividadPorParticipante(actividad);
		addDiaActividadPorParticipante(actividad);
		addTipoActividadesPorParticipante(actividad);
	}
	
	//************************************************************************************
	// Metodos para consultar una actividad
	//************************************************************************************
	
	public ArrayList<Actividad> getActividadPorParticipante(String correoParticipante) {
		return actividades.get(correoParticipante);
	}
	
	public ArrayList<Actividad> getDiaActividadPorParticipante(String correoParticipante, LocalDate fechaActividad) {
		return diaActividadPorParticipante.get(correoParticipante).get(fechaActividad);
	}
	
	public ArrayList<Actividad> getTipoActividadesPorParticipante(String correoParticipante, String tipoActividad) {
		return tipoActividadesPorParticipante.get(correoParticipante).get(tipoActividad);
	}
	
}
