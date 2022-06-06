package procesamiento;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.tree.TreePath;

import interfaz.Enrutador;
import modelo.Actividad;
import modelo.Paquete;
import modelo.Participante;
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
	
	private Participante participante;
	
	private LocalDate fechaActividad;
	
	//************************************************************************************************
	// Constructor
	//************************************************************************************************
	
	public Reporte_Participante(Proyecto proyecto, String correoParticipante, TreePath ruta, LocalDate fechaActividad) throws Exception {
		Tarea tarea = encontrarTarea(proyecto, ruta);
		setTiempoTotal(tarea, correoParticipante);
		setSizeTiempoTotal(tarea, correoParticipante);
		setTiempoDiaActividad(tarea, correoParticipante, fechaActividad);
		setSizeTiempoDiaActividad(tarea, correoParticipante, fechaActividad);
		setTiempoPromedio(tarea, correoParticipante);
		participante = proyecto.getParticipantePorCorreo(correoParticipante);
		this.fechaActividad = fechaActividad;
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
	
	public Participante getParticipante() {
		return participante;
	}
	
	public LocalDate getFechaActividad() {
		return fechaActividad;
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
	
	//***************************************************************************************
	// Otros metodos
	//***************************************************************************************
	
	public Tarea encontrarTarea(Proyecto proyecto, TreePath ruta) throws Exception {

		if (ruta.getPathCount() == 1) {
			throw new Exception("Seleccione una ruta");
		}
		else {
			Paquete paquete = null;
			
			for (int i=1; i<(ruta.getPathCount()-1); i++) {
				paquete = Enrutador.getInstance().getProyecto().getPaquete(ruta.getPathComponent(i).toString());
			}
			
			Tarea tarea = paquete.getTarea(ruta.getLastPathComponent().toString());
			
			if (tarea == null) {
				throw new Exception("No se encuentra la tarea");
			}
			else {
				return tarea;
			}
		}
	}

	
}
