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
	 * HashMap que almacena las parejas (correoParticipante: Participante)
	 */
	private HashMap<String, Participante> correos;
	/**
	 * ArrayList con los participantes de este proyecto
	 */
	private ArrayList<Participante> participantes;
	/**
	 * HashMap que almacena las parejas (nombrePaquete: Paquete)
	 */
	private HashMap<String,Paquete> paquetes;
	
	//******************************************************************
	// Constructor
	//******************************************************************
	
	public Proyecto(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Participante participante, Paquete paquete) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.participante = participante;
		this.participantes = new ArrayList<Participante>();
		this.correos = new HashMap<String, Participante>();
		this.paquetes = new HashMap<String,Paquete>();
		addParticipante(participante);
		addPaquete(paquete);
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
	
	public Paquete getPaquete(String nombrePaquete) {
		return paquetes.get(nombrePaquete);	
	}
	
	public ArrayList<Participante> getParticipantes() {
		return participantes;
	}
	
	//************************************************************************************
	// Otros metodos
	//************************************************************************************
	
	public void addParticipante(Participante otroParticipante) {
		correos.put(otroParticipante.getCorreo(), otroParticipante);
		participantes.add(otroParticipante);
	}
	
	public Participante getParticipantePorCorreo(String correo) {
		return correos.get(correo);
	}
	
	public boolean isParticipantePorCorreo(String correo) {
		return correos.containsKey(correo);
	}
	
	public void addPaquete(Paquete paquete) {
		paquetes.put(paquete.getNombre(), paquete);
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
