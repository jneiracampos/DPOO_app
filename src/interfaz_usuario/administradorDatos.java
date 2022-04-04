package interfaz_usuario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import modelo.Participante;
import modelo.Proyecto;

public class administradorDatos {
	
	private static String nombreArchivo;
	
	
	public static Proyecto cargarDatos() throws Throwable {
		
		 	BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
			String nombreProyecto = reader.readLine();
			String descripcionProyecto = reader.readLine();
			String inicioProyecto = reader.readLine();
			LocalDate fechaInicioProyecto = LocalDate.parse(inicioProyecto);
			String finProyecto = reader.readLine();
			LocalDate fechaFinProyecto = LocalDate.parse(finProyecto);
			String nombreParticipanteInicial = reader.readLine();
			String correoParticipanteInicial = reader.readLine();
			reader.readLine();
			
			Participante participanteInicial = Registro.nuevoParticipante(nombreParticipanteInicial, correoParticipanteInicial);
			Proyecto proyecto = Registro.nuevoProyecto(nombreProyecto, descripcionProyecto, fechaInicioProyecto, fechaFinProyecto, participanteInicial);
			
			
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
		writer.write("--");
		
		
		writer.close();
	}
	
}
