package modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

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
	 * El participante que realizó la actividad.
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
	 * El tiempo total invertido en esta actividad.
	 */
	private long tiempoTotal;
	
	//******************************************************************
	// Constructor
	//******************************************************************
		
	public Actividad(String nombre, String descripcion, String tipo, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Participante participante) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.participante = participante;
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

	public Participante getParticipanteActividad() {
		return participante;
	}
	
	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}
	
	public long getTiempoTotal() {
		tiempoTotal = Duration.between(horaInicio, horaFin).toMinutes();
		return tiempoTotal;
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
	
	
	
}