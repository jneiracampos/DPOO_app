package procesamiento;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Actividad;
import modelo.Proyecto;

public class Reporte {
	/**
	 * El tiempo total invertido en todas las actividades de un participante
	 */
	private static long tiempoTotal;
	/**
	 * El tiempo total invertido en todas las actividades de un tipo de un participante
	 */
	private static long tiempoTipoActividad;
	/**
	 * El tiempo toal invertido en todas las actividades en un día de un participante
	 */
	private static long tiempoDiaActividad;
	
	
	//************************************************************************************************
	// Generar las estadisticas de un participante
	//************************************************************************************************
	
	public static long getTiempoTotal(Proyecto proyecto, String correoParticipante) {
		try {
			ArrayList<Actividad> actividades = proyecto.getActividadPorParticipante(correoParticipante);
			for (int i = 0; i < actividades.size(); i++) {
				tiempoTotal += actividades.get(i).getTiempoTotal();
			}
		}
		catch(Exception e) {
			tiempoTotal = 0;
		}
		
		return tiempoTotal;
	}
	
	public static int getSizeTiempoTotal(Proyecto proyecto, String correoParticipante) {
		int cantidad;
		try {
			ArrayList<Actividad> actividades = proyecto.getActividadPorParticipante(correoParticipante);
			cantidad = actividades.size();
		}
		catch (Exception e) {
			cantidad = 0;
		}
		return cantidad;
	}
	
	public static double getTiempoPromedio(Proyecto proyecto, String correoParticipante) {
		int cantidad = getSizeTiempoTotal(proyecto, correoParticipante);
		return tiempoTotal/cantidad;
	}
	
	public static long getTiempoDiaActividad(Proyecto proyecto, String correoParticipante, LocalDate fechaActividad) {
		try {
			ArrayList<Actividad> actividades = proyecto.getDiaActividadPorParticipante(correoParticipante, fechaActividad);
			for (int i = 0; i < actividades.size(); i++) {
				tiempoTipoActividad += actividades.get(i).getTiempoTotal();
			}
		}
		catch(Exception e) {
			tiempoTipoActividad = 0;
		}
		
		return tiempoTipoActividad;
	}
	
	public static int getSizeTiempoDiaActividad(Proyecto proyecto, String correoParticipante, LocalDate fechaActividad) {
		int cantidad;
		try {
			ArrayList<Actividad> actividades = proyecto.getDiaActividadPorParticipante(correoParticipante, fechaActividad);
			cantidad =  actividades.size();
		}
		catch(Exception e) {
			cantidad = 0;
		}
		return cantidad;
	}
	
	public static long getTiempoTipoActividad(Proyecto proyecto, String correoParticipante, String tipoActividad) {
		try {
			ArrayList<Actividad> actividades = proyecto.getTipoActividadesPorParticipante(correoParticipante, tipoActividad);
			for (int i = 0; i < actividades.size(); i++) {
				tiempoDiaActividad += actividades.get(i).getTiempoTotal();
			}
		}
		catch(Exception e) {
			tiempoDiaActividad = 0;
		}
		return tiempoDiaActividad;
	}
	
	public static int getSizeTiempoTipoActividad(Proyecto proyecto, String correoParticipante, String tipoActividad) {
		int cantidad;
		try {
			ArrayList<Actividad> actividades = proyecto.getTipoActividadesPorParticipante(correoParticipante, tipoActividad);
			cantidad = actividades.size();
		}
		catch(Exception e) {
			cantidad = 0;
		}
		return cantidad;
	}
}
