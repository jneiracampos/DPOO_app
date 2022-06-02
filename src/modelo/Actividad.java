package modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
	 * La fecha en que se realiz� la actividad.
	 */
	private LocalDate fecha;
	/**
	 * El participante que realiz� la actividad.
	 */
	private Participante participante;
	/**
	 * La hora de inicio de la actividad.
	 */
	private LocalTime horaInicio;
	/**
	 * La hora de fin de la actividad.
	 */
	private LocalTime horaFin;
	/**
	 * ArrayList que almacena el tiempo de una actividad. 
	 */
	private ArrayList<Long> tiempoTotal = new ArrayList<Long>();
	/**
	 * Tiempo de la actividad
	 */
	private long tiempo;
	/**
	 * Indica si esa actividad finaliza con la tarea asociada
	 */
	private Boolean finalizar;
	
	//******************************************************************
	// Constructor
	//******************************************************************
		
	public Actividad(String nombre, String descripcion, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Boolean finaliza, Participante participante) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.participante = participante;
		this.finalizar = finaliza;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public Participante getParticipanteActividad() {
		return participante;
	}
	
	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}
	
	public Boolean getFinalizar() {
		return finalizar;
	}
	
	public long getTiempoTotal() {
		try {
			for (int i = 0; i < tiempoTotal.size(); i++)
				tiempo = tiempoTotal.get(i);
		}
		catch (Exception e) {
			tiempo = 0;
		}
		return tiempo;
	}
	
	//******************************************************************
	// Otros metodos
	//******************************************************************
	
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	
	public void addTiempo(LocalTime horaInicio, LocalTime horaFin) {
		tiempo = Duration.between(horaInicio, horaFin).toMinutes();
		tiempoTotal.add(tiempo);
	}

}