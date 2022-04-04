package interfaz_usuario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import modelo.Actividad;
import modelo.Participante;
import modelo.Proyecto;

public class administradorDatos {
	
	private static String nombreArchivo;
	
	private static Participante participante;
	
	
	@SuppressWarnings("resource")
	public static Proyecto cargarDatos(String nombreproyecto) throws Throwable {
		
		nombreArchivo = nombreproyecto;

		BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
		String nombreProyecto = reader.readLine();
		String descripcionProyecto = reader.readLine();
		String inicioProyecto = reader.readLine();
		LocalDate fechaInicioProyecto = LocalDate.parse(inicioProyecto);
		String finProyecto = reader.readLine();
		LocalDate fechaFinProyecto = LocalDate.parse(finProyecto);
		String nombreParticipanteInicial = reader.readLine();
		String correoParticipanteInicial = reader.readLine();
		participante = Registro.nuevoParticipante(nombreParticipanteInicial, correoParticipanteInicial);
		Proyecto proyecto = Registro.nuevoProyecto(nombreProyecto, descripcionProyecto, fechaInicioProyecto, fechaFinProyecto, participante);
		reader.readLine();
		while (reader.readLine() != "--") {
			String nombreActividad = reader.readLine();
			String descripcionActividad = reader.readLine();
			String tipoActividad = reader.readLine();
			String fecha = reader.readLine();
			LocalDate fechaActividad = LocalDate.parse(fecha);
			String nombreParticipante = reader.readLine();
			String correoParticipante = reader.readLine();
			String horaInicio = reader.readLine();
			LocalTime horaInicioActividad = LocalTime.parse(horaInicio);
			String horaFin = reader.readLine();
			LocalTime horaFinActividad = LocalTime.parse(horaFin);
			participante = Registro.nuevoParticipante(nombreParticipante, correoParticipante);
			Actividad actividad = Registro.nuevaActividad(nombreActividad, descripcionActividad, tipoActividad, fechaActividad, horaInicioActividad, horaFinActividad, participante);
			proyecto.addActividad(actividad);
		}
		reader.readLine();
		while (reader.readLine() != "--") {
			String nombreParticipante = reader.readLine();
			String correoParticipante = reader.readLine();
			participante = Registro.nuevoParticipante(nombreParticipante, correoParticipante);
			proyecto.addOtroParticipante(participante);
		}
		return proyecto;	
	}
	
	public static void generarArchivo(Proyecto proyecto) throws IOException {
		nombreArchivo = proyecto.getNombre();
		BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
		writer.write(proyecto.getNombre());
		writer.newLine();
		writer.write(proyecto.getDescripcion());
		writer.newLine();
		writer.write(proyecto.getFechaInicio().toString());
		writer.newLine();
		writer.write(proyecto.getFechaFin().toString());
		writer.newLine();
		writer.write(proyecto.getParticipante().getNombre());
		writer.newLine();
		writer.write(proyecto.getParticipante().getCorreo());
		writer.newLine();
		writer.write("Actividades:");
		ArrayList<Actividad> actividades = proyecto.getActividadesAlmacenamiento();
		for (int i = 0; i < actividades.size(); i++) {
			writer.newLine();
			writer.write(actividades.get(i).getNombre());
			writer.newLine();
			writer.write(actividades.get(i).getDescripcion());
			writer.newLine();
			writer.write(actividades.get(i).getFecha().toString());
			writer.newLine();
			writer.write(actividades.get(i).getParticipanteActividad().getNombre());
			writer.newLine();
			writer.write(actividades.get(i).getParticipanteActividad().getCorreo());
			writer.newLine();
			writer.write(actividades.get(i).getHoraInicio().toString());
			writer.newLine();
			writer.write(actividades.get(i).getHoraFin().toString());
		}
		writer.newLine();
		writer.write("--");
		writer.newLine();
		writer.write("Participantes:");
		ArrayList<Participante> participantes = proyecto.getParticipanteAlmacenamiento();
		for (int i = 0; i < participantes.size(); i++) {
			writer.newLine();
			writer.write(participantes.get(i).getNombre());
			writer.newLine();
			writer.write(participantes.get(i).getCorreo());
		}
		writer.newLine();
		writer.write("--");
		writer.close();
	}
	
}
