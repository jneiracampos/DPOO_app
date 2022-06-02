package procesamiento;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Actividad;
import modelo.Proyecto;
import modelo.Tarea;

public class Reporte_Participante {

	/**
	 * El tiempo total invertido en todas las actividades de un participante
	 */
	private long tiempoTotal;
	/**
	 * El tiempo promedio invertido en todas las actividad de un participante
	 */
	private long tiempoPromedio;
	/**
	 * El tiempo total invertido en todas las actividades en un día de un participante
	 */
	private long tiempoDiaActividad;
	/**
	 * El total de actividades realizadas por un participante
	 */
	private int cantidadActividades;
	/**
	 * El total de actividades realizadas por un participante en un dia
	 */
	private int cantidadDiaActividades;
	
	//************************************************************************************************
	// Constructor
	//************************************************************************************************
	
	public Reporte_Participante(Proyecto proyecto, String correoParticipante, String nombrePaquete, String nombreTarea, LocalDate fechaActividad) {
		Tarea tarea = proyecto.getPaquete(nombrePaquete).getTarea(nombreTarea);
		setTiempoTotal(tarea, correoParticipante);
		setSizeTiempoTotal(tarea, correoParticipante);
		setTiempoDiaActividad(tarea, correoParticipante, fechaActividad);
		setSizeTiempoDiaActividad(tarea, correoParticipante, fechaActividad);
		setTiempoPromedio(tarea, correoParticipante);
	}
	
	//***************************************************************************************
	// Metodos para consultar los atributos
	//***************************************************************************************
	
	public long getTiempoTotal() {
		return tiempoTotal;
	}
	
	public long getTiempoPromedio() {
		return tiempoPromedio;
	}
	
	public long getTiempoDiaActividad() {
		return tiempoDiaActividad;
	}
	
	public int getCantidadActividades() {
		return cantidadActividades;
	}
	
	public int getCantidadDiaActividades() {
		return cantidadDiaActividades;
	}
	
	//***************************************************************************************
	// Metodos para generar el reporte
	//***************************************************************************************
	
	private void setTiempoTotal(Tarea tarea, String correoParticipante) {
		try {
			ArrayList<Actividad> actividades = tarea.getActividadPorParticipante(correoParticipante);
			for (int i = 0; i < actividades.size(); i++) {
				tiempoTotal += actividades.get(i).getTiempoTotal();
			}
		}
		catch (Exception e) {
			tiempoTotal = 0;
		}		
	}
	
	private void setSizeTiempoTotal(Tarea tarea, String correoParticipante) {
		try {
			ArrayList<Actividad> actividades = tarea.getActividadPorParticipante(correoParticipante);
			cantidadActividades = actividades.size();
		}
		catch (Exception e) {
			cantidadActividades = 0;
		}
	}
	
	private void setTiempoDiaActividad(Tarea tarea, String correoParticipante, LocalDate fechaActividad) {
		try {
			ArrayList<Actividad> actividades = tarea.getDiaActividadPorParticipante(correoParticipante, fechaActividad);
			for (int i = 0; i < actividades.size(); i++) {
				tiempoDiaActividad += actividades.get(i).getTiempoTotal();
			}
		}
		catch (Exception e) {
			tiempoDiaActividad = 0;
		}		
	}
	
	private void setSizeTiempoDiaActividad(Tarea tarea, String correoParticipante, LocalDate fechaActividad) {
		try {
			ArrayList<Actividad> actividades = tarea.getDiaActividadPorParticipante(correoParticipante, fechaActividad);
			cantidadDiaActividades =  actividades.size();
		}
		catch(Exception e) {
			cantidadDiaActividades = 0;
		}
	}
	
	private void setTiempoPromedio(Tarea tarea, String correoParticipante) {
		try {
			tiempoPromedio = (tiempoTotal/cantidadActividades);
		}
		catch (Exception e) {
			tiempoPromedio = 0;
		}
	}

}
