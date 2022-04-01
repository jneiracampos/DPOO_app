package interfaz_usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import modelo.Actividad;
import modelo.Participante;
import modelo.Proyecto;

public class App {
	
	private Proyecto proyecto;
	
	/**
	 * Ejecuta la aplicacion: le muestra el menu al usuario y le pide que ingrese 
	 * una opcion, y ejecuta la opcion seleccionada por el usuario. Este proceso se
	 * repite hasta que el usuario seleccione la opcion de abandonar la aplicacion.
	 */
	public void ejecutaraplicacion() {

		boolean continuar = true;

		while(continuar) {

			try {

				mostrarMenu1();

				int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));
				
				if (opcion_seleccionada == 1) {
					Participante participanteInicial = Registro.nuevoParticipante("JC", "j.neira");
					LocalDate fechaInicio = LocalDate.of(2020,03,02);
					LocalDate fechaFin = LocalDate.of(2020,04,02);
					proyecto = Registro.nuevoProyecto("DPOO", "Proyecto", fechaInicio, fechaFin, participanteInicial);
					System.out.println(proyecto.getNombre());
					System.out.println(proyecto.getParticipante().getNombre());
					ejecutarMenu();
				}

				else if (opcion_seleccionada == 2) {
					if (Registro.isProyecto("DPOO")) {
						proyecto = Registro.getProyecto("DPOO");
						ejecutarMenu();
					} 
					else {
						System.out.println("No se tiene registro de este proyecto");
					}
				}

				else if (opcion_seleccionada == 3) {
					System.out.println("Saliendo de la aplicacion...");
					continuar = false;
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de las opciones");
			}
		}
	}
	
	private void ejecutarMenu() {
		
		boolean continuar = true;
		
		while(continuar) {
			try {
				
				mostrarMenu2();
				
				int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));
				
				if (opcion_seleccionada == 1) {
					Participante otroParticipante = Registro.nuevoParticipante("DP", "d.perez");
					proyecto.addOtroParticipante(otroParticipante);
					System.out.println(proyecto.getNombreOtroParticipante("d.perez"));
					System.out.println(proyecto.getCorreoOtroParticipante("DP"));
				}
				
				else if (opcion_seleccionada == 2) {
					LocalDate fecha = LocalDate.of(2020,03,02);
					Actividad actividad = Registro.nuevaActividad("Implementacion", "Tercera entrega", "Java", fecha);
					proyecto.addActividad(actividad);
					System.out.println(proyecto.getActividad("Implementacion").getDescripcion());
				}
				
				else if (opcion_seleccionada == 3) {
					
				}
				
			}
			catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de las opciones");
			}	
		}
	}

	/**
	 * Muestra al usuario el menu con las opciones para que escoja la accion
	 * que quiere ejecutar.
	 */
	
	public void mostrarMenu1() {
		System.out.println("\nOpciones de la aplicacion\n");
		System.out.println("1. Crear proyecto");
		System.out.println("2. Abrir proyecto");
		System.out.println("3. Salir de la aplicacion");
	}
	
	private void mostrarMenu2() {
		System.out.println("1. Agregar un participante");
		System.out.println("2. Agregar una actividad");
		System.out.println("3. Consultar reporte de un participante");
		System.out.println("4. Volver al anterior menu");
	}

	/**
	 * Este metodo sirve para imprimir un mensaje en la consola pidiendole
	 * informacion al usuario y luego leer lo que escribe el usuario.
	 * 
	 * @param mensaje
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	
	public String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Este es el metodo principal de la aplicacion, con el que inicia la ejecucion 
	 * de la aplicacion
	 * @param args Parametros introducidos en la linea de comandos al invocar la aplicacion
	 */
	public static void main(String[] args) {
		App consola = new App();
		consola.ejecutaraplicacion();
	}
}
