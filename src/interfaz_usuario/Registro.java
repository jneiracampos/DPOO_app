package interfaz_usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.Participante;
import modelo.Proyecto;
import modelo.Actividad;

public class Registro {
	
	/**
	 * HashMap que almacena las parejas (nombreProyecto: proyecto)
	 */
	private static HashMap<String, Proyecto> proyectos = new HashMap<String, Proyecto>();
	ArrayList<HashMap<String, ArrayList<String>>> listaProyectos = new ArrayList<HashMap<String, ArrayList<String>>>();
	HashMap<String, ArrayList<String>> mapProyectos = new HashMap<String, ArrayList<String>>();
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
	
	public static Actividad nuevaActividad(String nombre, String descripcion, String tipo, LocalDate fecha) {
		Actividad actividad = new Actividad(nombre, descripcion, tipo, fecha);
		return actividad;
	}
	
	public Registro(String nuevoParticipante, String nuevaActividad) {

		this.nuevoParticipante =nuevoParticipante;
		this.nuevaActividad = nuevaActividad;
		ArrayList<String> participante = new ArrayList<String>();
		ArrayList<String> actividad = new ArrayList<String>();
		participante.add(nuevoParticipante);
		actividad.add(nuevaActividad);
		mapProyectos.put("Participante", participante);
		mapProyectos.put("Actividad", actividad);
		listaProyectos.add(mapProyectos);
		
	
	}
	//***************************************************************************************
	// Otros metodos
	//***************************************************************************************
	public int getProyecto() {
		return idPedido;
	}

	public String getParticipante() {
		return nombreParticipante;
	}

	public String getActividad() {
		return actividad;
	}
	
}
