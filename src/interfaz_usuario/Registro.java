package interfaz_usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import modelo.Participante;
import modelo.Proyecto;
import modelo.Actividad;

/**
 * Los metodos y variables deben ser static porque no tiene sentido que haya una instancia de la clase Registro
 */

public class Registro {
	
	/**
	 * HashMap que almacena las parejas (nombreProyecto: proyecto)
	 */
	private static HashMap<String, Proyecto> proyectos = new HashMap<String, Proyecto>();

	//***************************************************************************************
	// Constructores
	//***************************************************************************************
	
	/**
	 * Estos metodos inicializan los constructores de las clases en el modelo.
	 */
	
	public static Proyecto nuevoProyecto(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Participante participanteInicial) {
		Proyecto proyecto = new Proyecto(nombre, descripcion, fechaInicio, fechaFin, participanteInicial);
		proyectos.put(nombre, proyecto);
		return proyecto;
	}
	
	public static Participante nuevoParticipante(String nombre, String correo) {
		Participante participante = new Participante(nombre, correo);
		return participante;
	}
	
	public static Actividad nuevaActividad(String nombre, String descripcion, String tipo, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Participante participante) {
		Actividad actividad = new Actividad(nombre, descripcion, tipo, fecha, horaInicio, horaFin, participante);
		return actividad;
	}

	
	//***************************************************************************************
	// Otros metodos
	//***************************************************************************************
	
	public static boolean isProyecto(String nombreProyecto) {
		return proyectos.containsKey(nombreProyecto);
	}
	
	public static Proyecto getProyecto(String nombreProyecto) {
		return proyectos.get(nombreProyecto);
	}	
}
