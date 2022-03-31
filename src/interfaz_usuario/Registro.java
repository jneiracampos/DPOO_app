package interfaz_usuario;

import java.time.LocalDate;

import modelo.Participante;
import modelo.Proyecto;
import modelo.Actividad;

public class Registro {
	
	public Registro() {
		
	}
	
	public Participante nuevoParticipante(String nombre, String correo) {
		Participante participante = new Participante(nombre, correo);
		return participante;
	}
	
	public Proyecto nuevoProyecto(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Participante participanteInicial) {
		Proyecto proyecto = new Proyecto(nombre, descripcion, fechaInicio, fechaFin, participanteInicial);
		return proyecto;
	}
	
	public Actividad nuevaActividad(String nombre, String descripcion, String tipo, LocalDate fecha, int tiempoTotal) {
		Actividad actividad = new Actividad(nombre, descripcion, tipo, fecha, tiempoTotal);
		return actividad;
	}
	
}
