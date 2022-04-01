package interfaz_usuario;

import java.time.LocalDate;

import modelo.Participante;
import modelo.Proyecto;
import modelo.Actividad;

public class Registro {
	
	Proyecto proyecto;
	Actividad actividad;
	Participante participante;
	
	//***************************************************************************************
	// Constructores de otras clases
	//***************************************************************************************
	
	/**
	 * Estos metodos inicializan los constructores de las clases en el modelo.
	 */
	
	public static Participante nuevoParticipante(String nombre, String correo) {
		Participante participante = new Participante(nombre, correo);
		return participante;
	}
	
	public static Proyecto nuevoProyecto(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Participante participanteInicial) {
		Proyecto proyecto = new Proyecto(nombre, descripcion, fechaInicio, fechaFin, participanteInicial);
		return proyecto;
	}
	
	public static Actividad nuevaActividad(String nombre, String descripcion, String tipo, LocalDate fecha) {
		Actividad actividad = new Actividad(nombre, descripcion, tipo, fecha);
		return actividad;
	}
	
	//***************************************************************************************
	// Otros metodos
	//***************************************************************************************
	
	/**
	 * Llamado a metodos de la clase Proyecto.
	 */
	
}
