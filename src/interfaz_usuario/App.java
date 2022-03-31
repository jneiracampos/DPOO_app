package interfaz_usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
	
	/**
	 * Ejecuta la aplicacion: le muestra el menu al usuario y le pide que ingrese 
	 * una opcion, y ejecuta la opcion seleccionada por el usuario. Este proceso se
	 * repite hasta que el usuario seleccione la opcion de abandonar la aplicacion.
	 */
	public void ejecutaraplicacion() {
		boolean continuar = true;
		while(continuar) {
			try {
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));
				if (opcion_seleccionada == 1) {
					
				}

				if (opcion_seleccionada == 2) {
					
				}

				if (opcion_seleccionada == 3) {
					
				}

				if (opcion_seleccionada == 4) {
					
				}

				if (opcion_seleccionada == 5) {
					
				}
				else if (opcion_seleccionada == 6) {
					System.out.println("Saliendo de la aplicacion...");
					continuar = false;
				}
				
			}
			catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de las opciones");
			}
		}
	}
	
	
	/**
	 * Muestra al usuario el menu con las opciones para que escoja la siguiente
	 * accion que quiere ejecutar.
	 */
	
	public void mostrarMenu() {
		System.out.println("\nOpciones de la aplicacion\n");
		System.out.println("1. Crear proyecto");
		System.out.println("2. Agregar otro participante");
		System.out.println("3. Agregar una actividad");
		System.out.println("4. Modificar fecha y hora de una actividad"); //revisar
		System.out.println("5. Consultar reporte de un participante");
		System.out.println("6. Salir de la aplicacion");
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
