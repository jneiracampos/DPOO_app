package procesamiento;

import java.time.LocalDate;
import java.util.ArrayList;

import interfaz_usuario.Ventana_Mostrar_Reporte;
import modelo.Actividad;
import modelo.Participante;
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
	
	private static long getTiempoTotal(Proyecto proyecto, String correoParticipante) {
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
	
	private static int getSizeTiempoTotal(Proyecto proyecto, String correoParticipante) {
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
	
	private static double getTiempoPromedio(Proyecto proyecto, Participante participante) {
		int cantidad = getSizeTiempoTotal(proyecto, participante.getCorreo());
		return (tiempoTotal/cantidad);
	}
	
	private static long getTiempoDiaActividad(Proyecto proyecto, String correoParticipante, LocalDate fechaActividad) {
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
	
	private static int getSizeTiempoDiaActividad(Proyecto proyecto, String correoParticipante, LocalDate fechaActividad) {
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
	
	private static long getTiempoTipoActividad(Proyecto proyecto, String correoParticipante, String tipoActividad) {
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
	
	private static int getSizeTiempoTipoActividad(Proyecto proyecto, String correoParticipante, String tipoActividad) {
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
	
	public static void getReporte(Proyecto proyecto, Participante participante, String tipo, LocalDate fecha) {
		long tiempoTotal = getTiempoTotal(proyecto, participante.getCorreo());
		int cantidadActividades = getSizeTiempoTotal(proyecto, participante.getCorreo());
		long tiempoTipoActividad = getTiempoTipoActividad(proyecto, participante.getCorreo(), tipo);
		int cantidadTipoActividad = getSizeTiempoTipoActividad(proyecto, participante.getCorreo(), tipo);
		long tiempoDiaActividad = getTiempoDiaActividad(proyecto, participante.getCorreo(), fecha);
		int cantidadDiaActividad = getSizeTiempoDiaActividad(proyecto, participante.getCorreo(), fecha);
		double tiempoPromedio = getTiempoPromedio(proyecto, participante);
		
		new Ventana_Mostrar_Reporte(tiempoTotal,cantidadActividades,tiempoTipoActividad,cantidadTipoActividad,tiempoDiaActividad,cantidadDiaActividad,tiempoPromedio,participante,tipo,fecha);
	}
}
