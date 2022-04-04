package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
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
	 * HashMap que almacena las parejas (nombreParticipante: Participante)
	 */
	private HashMap<String, Participante> nombres;
	/**
	 * HashMap que almacena las parejas (correoParticipante: Participante)
	 */
	private HashMap<String, Participante> correos;
	/**
	 * ArrayList que almacena objetos de tipo Actividad para introducirlo 
	 * en las estructuras de datos
	 */
	private ArrayList<Actividad> arregloActividades;
	/**
	 * HashMap que almacena las parejas (nombreActividad: ArrayList(Actividad))
	 */
	private HashMap<String, ArrayList<Actividad>> actividades;
	/**
	 * ArrayList con las actividades para administradorDatos
	 */
	private ArrayList<Actividad> actividadesAlmacenamiento;
	/**
	 * ArrayList con los participantes para administradorDatos
	 */
	private ArrayList<Participante> participantesAlmacenamiento;
	/**
	 * HashMap que almacena las parejas (nombreParticipante: HashMap(diaActividad: ArrayList(Actividad)))
	 */
	private HashMap<String, HashMap<LocalDate, ArrayList<Actividad>>> diaActividadPorParticipante;
	/**
	 * HashMap que almacena las parejas (nombreParticipante: HashMap(tipoActividad: ArrayList(Actividad)))
	 */
	private HashMap<String, HashMap<String, ArrayList<Actividad>>> tipoActividadesPorParticipante;
	/**
	 * HashMap que almacena las parejas (fechaActividad: arrayList(Actividad))
	 */
	private HashMap<LocalDate, ArrayList<Actividad>> localDate;
	/**
	 * HashMap que almacena las parejas (tipoActividad: arrayList(Actividad))
	 */
	private HashMap<String, ArrayList<Actividad>> tipo;
	
	
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
		this.nombres = new HashMap<String, Participante>();
		this.correos = new HashMap<String, Participante>();
		this.actividadesAlmacenamiento = new ArrayList<Actividad>();
		this.participantesAlmacenamiento = new ArrayList<Participante>();
		this.actividades = new HashMap<String, ArrayList<Actividad>>();
		this.diaActividadPorParticipante = new HashMap<String, HashMap<LocalDate, ArrayList<Actividad>>>();
		this.tipoActividadesPorParticipante = new HashMap<String, HashMap<String, ArrayList<Actividad>>>();
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
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public ArrayList<Actividad> getActividadesAlmacenamiento() {
		return actividadesAlmacenamiento;
	}
	
	public ArrayList<Participante> getParticipanteAlmacenamiento() {
		return participantesAlmacenamiento;
	}
	
	//************************************************************************************
	// Metodos para almacenar otros participantes a una instancia de Proyecto
	//************************************************************************************
	
	public void addOtroParticipante(Participante otroParticipante) {
		nombres.put(otroParticipante.getNombre(), otroParticipante);
		correos.put(otroParticipante.getCorreo(), otroParticipante);
		participantesAlmacenamiento.add(otroParticipante);
	}

	public Participante getParticipantePorNombre(String nombre) {
		return nombres.get(nombre);
	}
	
	public Participante getParticipantePorCorreo(String correo) {
		return correos.get(correo);
	}

	public boolean isParticipantePorNombre(String nombre) {
		return nombres.containsKey(nombre);
	}
	
	public boolean isParticipantePorCorreo(String correo) {
		return correos.containsKey(correo);
	}
	
	
	//************************************************************************************
	// Metodos para almacenar actividades a una instancia de Proyecto
	//************************************************************************************
	
	private void addActividadPorParticipante(Actividad actividad) {
		if(actividades.containsKey(actividad.getParticipanteActividad().getCorreo())) {
			arregloActividades = actividades.get(actividad.getParticipanteActividad().getCorreo());
			arregloActividades.add(actividad);				
		}
		else {
			arregloActividades = new ArrayList<Actividad>();
			arregloActividades.add(actividad);
			actividades.put(actividad.getParticipanteActividad().getCorreo(), arregloActividades);
		}
		actividadesAlmacenamiento.add(actividad);
	}
	
	private void addDiaActividadPorParticipante(Actividad actividad) {
		if(diaActividadPorParticipante.containsKey(actividad.getParticipanteActividad().getCorreo())) {
			if(localDate.containsKey(actividad.getFecha())) {
				arregloActividades = localDate.get(actividad.getFecha());
				arregloActividades.add(actividad);
			}
			else {
				arregloActividades = new ArrayList<Actividad>();
				arregloActividades.add(actividad);
				localDate.put(actividad.getFecha(), arregloActividades);
			}
		}
		else {
			localDate = new HashMap<LocalDate, ArrayList<Actividad>>();
			arregloActividades = new ArrayList<Actividad>();
			arregloActividades.add(actividad);
			diaActividadPorParticipante.put(actividad.getParticipanteActividad().getCorreo(), localDate);
			localDate.put(actividad.getFecha(), arregloActividades);
		}
	}
	
	private void addTipoActividadesPorParticipante(Actividad actividad) {
		if(tipoActividadesPorParticipante.containsKey(actividad.getParticipanteActividad().getCorreo())) {
			if(tipo.containsKey(actividad.getTipo())) {
				arregloActividades = tipo.get(actividad.getTipo());
				arregloActividades.add(actividad);
			}
			else {
				arregloActividades = new ArrayList<Actividad>();
				arregloActividades.add(actividad);
				tipo.put(actividad.getTipo(), arregloActividades);
			}
		}
		else {
			tipo = new HashMap<String, ArrayList<Actividad>>();
			arregloActividades = new ArrayList<Actividad>();
			arregloActividades.add(actividad);
			tipoActividadesPorParticipante.put(actividad.getParticipanteActividad().getCorreo(), tipo);
			tipo.put(actividad.getTipo(), arregloActividades);
		}
	}
	
	public void addActividad(Actividad actividad) {
		addActividadPorParticipante(actividad);
		addDiaActividadPorParticipante(actividad);
		addTipoActividadesPorParticipante(actividad);
	}
	
	//************************************************************************************
	// Metodos para consultar las estructuras de datos
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
