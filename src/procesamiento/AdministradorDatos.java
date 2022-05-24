package procesamiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import interfaz.Enrutador;
import modelo.Actividad;
import modelo.Participante;
import modelo.Proyecto;

public class AdministradorDatos {
	
	@SuppressWarnings("resource")
	public static Proyecto cargarDatos(String nombreproyecto) throws Throwable {
		
		String nombreArchivo = nombreproyecto;
		BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
		String nombreProyecto = reader.readLine();
		String descripcionProyecto = reader.readLine();
		LocalDate fechaInicioProyecto = LocalDate.parse(reader.readLine());
		LocalDate fechaFinProyecto = LocalDate.parse(reader.readLine());
		String nombreParticipanteInicial = reader.readLine();
		String correoParticipanteInicial = reader.readLine();
		Participante participante = Enrutador.nuevoParticipante(nombreParticipanteInicial, correoParticipanteInicial);
		Proyecto proyecto = Enrutador.nuevoProyecto(nombreProyecto, descripcionProyecto, fechaInicioProyecto, fechaFinProyecto, participante);
		reader.readLine();
		int marcador = 0;
		while (marcador == 0) {
			String inicio = reader.readLine();
			if (inicio.equals("--")) {
				marcador = 1;
			}
			else {
				String nombreActividad = inicio;
				String descripcionActividad = reader.readLine();
				String tipoActividad = reader.readLine();
				LocalDate fechaActividad = LocalDate.parse(reader.readLine());
				String nombreParticipante = reader.readLine();
				String correoParticipante = reader.readLine();
				LocalTime horaInicioActividad = LocalTime.parse(reader.readLine());
				LocalTime horaFinActividad = LocalTime.parse(reader.readLine());
				participante = Enrutador.nuevoParticipante(nombreParticipante, correoParticipante);
				Actividad actividad = Enrutador.nuevaActividad(nombreActividad, descripcionActividad, tipoActividad, fechaActividad, horaInicioActividad, horaFinActividad, participante);
				actividad.addTiempo(horaInicioActividad, horaFinActividad);
				proyecto.addActividad(actividad);
			}
		}
		reader.readLine();
		while (marcador == 1) {
			String inicio = reader.readLine();
			if (inicio.equals("--")) {
				marcador = 0;
			}
			else {
				String nombreParticipante = inicio;
				String correoParticipante = reader.readLine();
				participante = Enrutador.nuevoParticipante(nombreParticipante, correoParticipante);
				proyecto.addOtroParticipante(participante);
			}
		}
		
		return proyecto;	
	}
	
	public static void generarArchivo(Proyecto proyecto) throws IOException {
		String nombreArchivo = proyecto.getNombre();
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
			writer.write(actividades.get(i).getTipo());
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
