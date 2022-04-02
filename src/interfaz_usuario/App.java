package interfaz_usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;

import modelo.Actividad;
import modelo.Participante;
import modelo.Proyecto;

public class App {
	/**
	 * Este es el usuario de la aplicacion.
	 */
	private Participante usuario;
	/**
	 * Este es un participante de un proyecto.
	 */
	private Participante participante;
	/**
	 * Este es el proyecto que el usuario tiene abierto en un momento dado.
	 */
	private Proyecto proyecto;
	/**
	 * Anio
	 */
	private int anio;
	/**
	 * Mes
	 */
	private int mes;
	/**
	 * dia
	 */
	private int dia;
	/**
	 * Hora
	 */
	private int hora;
	/**
	 * Minuto
	 */
	private int minuto;
	/**
	 * Nombre del participante
	 */
	private String nombreParticipante;
	/**
	 * Correo del participante
	 */
	private String correoParticipante;
	
	
	//***********************************************************************************************************************
	// Ejecutar la aplicacion
	//***********************************************************************************************************************

	/**
	 * Le muestra el menu al usuario, le solicita que ingrese una opcion y ejecuta esta opcion. 
	 * Este proceso se repite hasta que el usuario seleccione la opcion de abandonar la aplicacion.
	 */

	private void ejecutaraplicacion() {
		
		System.out.println("Por favor ingrese los siguientes datos para iniciar a la aplicacion\n");
		nombreParticipante = input("Nombre");
		correoParticipante = input("Correo");
		usuario = Registro.nuevoParticipante(nombreParticipante, correoParticipante);
		
		boolean continuar = true;
		while(continuar) {
			try {
				mostrarMenu1();
				int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));				
				if (opcion_seleccionada == 1) {
					//Nombre y descripcion del proyecto
					String nombreProyecto = input("Ingrese el nombre del proyecto");
					while (Registro.isProyecto(nombreProyecto)) {
						System.out.println("\nExiste un proyecto con este nombre.\n");
						nombreProyecto = input("Ingrese el nombre del proyecto");
					}
					String descripcionProyecto = input("Ingrese la descripcion del proyecto");
					//Fecha de inicio del proyecto
					LocalDate fechaInicio = LocalDate.now();
					//Fecha de fin del proyecto
					System.out.println("Ingrese una fecha aproximada del fin del proyecto:");
					dia = Integer.parseInt(input("Dia"));
					mes = Integer.parseInt(input("Mes"));
					anio = Integer.parseInt(input("Año"));
					LocalDate fechaFin = LocalDate.of(anio, mes, dia);
					//Crear proyecto
					if (fechaFin.isAfter(fechaInicio)) {
						proyecto = Registro.nuevoProyecto(nombreProyecto, descripcionProyecto, fechaInicio, fechaFin, usuario);
						System.out.println("\n¡" + proyecto.getParticipante().getNombre() + "! Creaste exitosamente el proyecto llamado " + proyecto.getNombre() + ".");
						ejecutarMenu();
					}
					else {
						System.out.println("\nIngrese una fecha de fin que sea posterior a la fecha de inicio " + fechaInicio + "\n");
					}
				}
				else if (opcion_seleccionada == 2) {
					String nombreProyecto = input("\nIngrese el nombre del proyecto que desea abrir");
					if (Registro.isProyecto(nombreProyecto)) {
						proyecto = Registro.getProyecto(nombreProyecto);
						System.out.println("\nSe abrió exitosamente el proyecto llamado " + proyecto.getNombre() + ".\n");
						ejecutarMenu();
					} 
					else {
						System.out.println("\nNo se tiene registro de este proyecto");
					}
				}
				else if (opcion_seleccionada == 3) {
					System.out.println("\nSaliendo de la aplicacion...");
					continuar = false;
				}
			}
			catch (NumberFormatException e) {
				System.out.println("\nDebe seleccionar uno de las opciones");
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
					String descripcionProyecto = input("\nIngrese la nueva descripcion del proyecto");
					proyecto.setDescripcion(descripcionProyecto);
					System.out.println("Se actualizó exitosamente la descripción del proyecto " + proyecto.getNombre() + ".");
				}
				else if (opcion_seleccionada == 2) {
					anio = Integer.parseInt(input("\nIngrese el año de fin del proyecto"));
					mes = Integer.parseInt(input("Ingrese el mes de fin del proyecto"));
					dia = Integer.parseInt(input("Ingrese el dia de fin del proyecto"));
					LocalDate fechaFin = LocalDate.of(anio, mes, dia);
					proyecto.setFechaFin(fechaFin);
					System.out.println("\nSe actualizó exitosamente la fecha de fin del proyecto a " + proyecto.getFechaFin() + ".");
				}
				else if (opcion_seleccionada == 3) {
					nombreParticipante = input("\nIngrese el nombre del participante que desea añadir al proyecto");
					correoParticipante = input("Ingrese el correo del participante que desea añadir al proyecto");
					if (proyecto.isParticipantePorCorreo(correoParticipante) || proyecto.getParticipante().getCorreo().equals(correoParticipante)) {
						System.out.println("\nYa existe un participante con este correo.");
					}
					else {
						participante = Registro.nuevoParticipante(nombreParticipante, correoParticipante);
						proyecto.addOtroParticipante(participante);
						System.out.println("\nSe añadió exitosamente al participante llamado " + proyecto.getParticipantePorCorreo(correoParticipante).getNombre() + ".");
					}
				}
				
				else if (opcion_seleccionada == 4) {
					boolean seguir = true;
					int propia = Integer.parseInt(input("\n¿Vas a registrar la actividad de otra persona? (1=Si, 2=No)"));
					if (propia == 2) {
						participante = proyecto.getParticipante();
					}
					else {
						correoParticipante = input("\nIngrese el correo del participante que realizó la actividad");
						if (proyecto.isParticipantePorCorreo(correoParticipante)) {
							participante = proyecto.getParticipantePorCorreo(correoParticipante);
						}
						else {
							System.out.println("\nNo existe un participante con este correo.");
							seguir = false;
						}
					}
					if (seguir == true) {
						// Atributos de la actividad
						String nombreActividad = input("Ingrese el nombre de la actividad");
						String descripcion = input("Ingrese la descripcion de la actividad");
						String tipo = input("Ingrese el tipo de actividad");
						//Fecha de inicio de la atividad
						LocalDate fecha = LocalDate.now();
						//Hora de inicio de la actividad
						LocalTime horaInicio = LocalTime.now();
						//Hora de fin de la actividad
						System.out.println("Ingrese una fecha aproximada del fin de la actividad:");
						hora = Integer.parseInt(input("Hora"));
						minuto = Integer.parseInt(input("Minuto"));
						LocalTime horaFin = LocalTime.of(hora, minuto);
						//Crear una actividad
						Actividad actividad = Registro.nuevaActividad(nombreActividad, descripcion, tipo, fecha, horaInicio, horaFin, participante);
						//Almacenar la actividad
						proyecto.addActividad(actividad);
						proyecto.addDiaActividadPorParticipante(actividad);
						proyecto.addTipoActividadesPorParticipante(actividad);
						System.out.println("\n¡La actividad se registro con exito!");
					}
				}
				
				else if (opcion_seleccionada == 5) {
					boolean seguir = true;
					int propia = Integer.parseInt(input("\n¿Vas a registrar la actividad de otra persona? (1=Si, 2=No)"));
					if (propia == 2) {
						participante = proyecto.getParticipante();
					}
					else {
						correoParticipante = input("\nIngrese el correo del participante que realizó la actividad");
						if (proyecto.isParticipantePorCorreo(correoParticipante)) {
							participante = proyecto.getParticipantePorCorreo(correoParticipante);
						}
						else {
							System.out.println("\nNo existe un participante con este correo.");
							seguir = false;
						}
					}
					if (seguir == true) {
						// Atributos de la actividad
						String nombreActividad = input("Ingrese el nombre de la actividad");
						String descripcion = input("Ingrese la descripcion de la actividad");
						String tipo = input("Ingrese el tipo de actividad");
						//Fecha en la que se realizó la actividad
						anio = Integer.parseInt(input("Ingrese el año de la actividad"));
						mes = Integer.parseInt(input("Ingrese el mes de la actividad"));
						dia = Integer.parseInt(input("Ingrese el dia de la actividad"));
						LocalDate fecha = LocalDate.of(anio, mes, dia);
						//Hora de inicio de la actividad
						hora = Integer.parseInt(input("Ingrese la hora de inicio de la actividad"));
						minuto = Integer.parseInt(input("Ingrese el minuto de inicio de la actividad"));
						LocalTime horaInicio = LocalTime.of(hora, minuto);
						//Hora de fin de la actividad
						hora = Integer.parseInt(input("Ingrese la hora de fin de la actividad"));
						minuto = Integer.parseInt(input("Ingrese el minuto de fin de la actividad"));
						LocalTime horaFin = LocalTime.of(hora, minuto);
						//Crear una actividad
						Actividad actividad = Registro.nuevaActividad(nombreActividad, descripcion, tipo, fecha, horaInicio, horaFin, participante);
						//Almacenar la actividad
						proyecto.addActividad(actividad);
						proyecto.addDiaActividadPorParticipante(actividad);
						proyecto.addTipoActividadesPorParticipante(actividad);
						System.out.println("\n¡La actividad se registro con exito!");
					}
				}
				
				else if (opcion_seleccionada == 6) {
					String nombreActividad = input("Ingrese el nombre de la actividad que desea modificar");
					Actividad actividad = Registro.getActividad(nombreActividad);
					hora = Integer.parseInt(input("Hora"));
					minuto = Integer.parseInt(input("Minuto"));
					LocalTime horaFin = LocalTime.of(hora, minuto);
					actividad.setHoraFin(horaFin);
					System.out.println("\nSe actualizó exitosamente la hora de fin de la actividad a " + actividad.getHoraFin() + ".");
				}
				else if (opcion_seleccionada == 7) {
					
				}
				else if (opcion_seleccionada == 8) {
					System.out.println("\nVolviendo al anterior menu...");
					continuar = false;
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Debe seleccionar uno de las opciones");
			}	
		}
	}
	
	//*************************************************************************************************
	// Menu
	//*************************************************************************************************
	
	private void mostrarMenu1() {
		System.out.println("\nOpciones de la aplicacion\n");
		System.out.println("1. Crear proyecto");
		System.out.println("2. Abrir proyecto");
		System.out.println("3. Salir de la aplicacion");
	}
	
	private void mostrarMenu2() {
		System.out.println("\nOpciones de la aplicacion\n");
		System.out.println("1. Cambiar la descripcion del proyecto");
		System.out.println("2. Cambiar la fecha de fin del proyecto");
		System.out.println("3. Registrar un participante");
		System.out.println("4. Registrar una actividad");
		System.out.println("5. Registar una actividad pasada");
		System.out.println("6. Cambiar la hora de fin una actividad");		
		System.out.println("7. Consultar reporte de un participante");
		System.out.println("8. Volver al anterior menu");
	}
	

	/**
	 * Este metodo sirve para imprimir un mensaje en la consola pidiendole
	 * informacion al usuario y luego leer lo que escribe el usuario.
	 * @param mensaje
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	
	private String input(String mensaje) {
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
