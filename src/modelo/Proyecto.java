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
	 * ArrayList que almacena objetos de tipo Actividad
	 */
	private ArrayList<Actividad> arregloActividades;
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
	
	//************************************************************************************
	// Metodos para almacenar otros participantes a una instancia de Proyecto
	//************************************************************************************
	
	public void addOtroParticipante(Participante otroParticipante) {
		nombres.put(otroParticipante.getNombre(), otroParticipante);
		correos.put(otroParticipante.getCorreo(), otroParticipante);
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
	
	public void addActividad(Actividad actividad) {
		arregloActividades = new ArrayList<Actividad>();
		arregloActividades.add(actividad);
		actividades.put(actividad.getParticipanteActividad().getCorreo(), arregloActividades);
	}
	
	public void addDiaActividadPorParticipante(Actividad actividad) {
		HashMap<LocalDate, ArrayList<Actividad>> localDate = new HashMap<LocalDate, ArrayList<Actividad>>();
		arregloActividades = new ArrayList<Actividad>();
		arregloActividades.add(actividad);
		localDate.put(actividad.getFecha(), arregloActividades);
		diaActividadPorParticipante.put(actividad.getParticipanteActividad().getCorreo(), localDate);
	}
	
	public void addTipoActividadesPorParticipante(Actividad actividad) {
		HashMap<String, ArrayList<Actividad>> tipo = new HashMap<String, ArrayList<Actividad>>();
		arregloActividades = new ArrayList<Actividad>();
		arregloActividades.add(actividad);
		tipo.put(actividad.getTipo(), arregloActividades);
		tipoActividadesPorParticipante.put(actividad.getParticipanteActividad().getCorreo(), tipo);
		
	}
}
