package interfaz_usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;

import modelo.Actividad;
import modelo.Participante;
import modelo.Proyecto;
import procesamiento.Reporte;

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
	 * Actividad
	 */
	private Actividad actividad;
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
	 * @throws IOException 
	 */

private void ejecutaraplicacion() {
		
		System.out.println("Por favor ingresa los siguientes datos para iniciar a la aplicacion\n");
		nombreParticipante = input("Nombre");
		correoParticipante = input("Correo");
		usuario = Registro.nuevoParticipante(nombreParticipante, correoParticipante);
		
		boolean continuar = true;
		while(continuar) {
			try {
				mostrarMenu1();
				int opcion_seleccionada = Integer.parseInt(input("\nPor favor selecciona una opcion"));				
				if (opcion_seleccionada == 1) {
					//Nombre y descripcion del proyecto
					String nombreProyecto = input("Ingresa el nombre del proyecto");
					while (Registro.isProyecto(nombreProyecto)) {
						System.out.println("\nYa existe un proyecto con este nombre, por favor usa uno diferente.\n");
						nombreProyecto = input("Ingresa el nombre del proyecto");
					}
					String descripcionProyecto = input("Ingresa la descripcion del proyecto");
					//Fecha de inicio del proyecto
					LocalDate fechaInicio = LocalDate.now();
					//Fecha de fin del proyecto
					boolean continuar1 = true;
					while (continuar1) {
						try {
							System.out.println("Ingresa una fecha aproximada del fin del proyecto:");
							dia = Integer.parseInt(input("Dia"));
							mes = Integer.parseInt(input("Mes"));
							anio = Integer.parseInt(input("Año"));
							LocalDate fechaFin = LocalDate.of(anio, mes, dia);
							//Crear proyecto
							if (fechaFin.isAfter(fechaInicio)) {
								proyecto = Registro.nuevoProyecto(nombreProyecto, descripcionProyecto, fechaInicio, fechaFin, usuario);
								System.out.println("\n¡" + proyecto.getParticipante().getNombre() + "! Creaste exitosamente el proyecto llamado " + proyecto.getNombre() + ".");
								try {
									administradorDatos.generarArchivo(proyecto);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								ejecutarMenu();
								continuar1 = false;
							}
							else {
								System.out.println("\nIngresa una fecha de fin que sea posterior a la fecha de inicio " + fechaInicio + " (hoy)\n");
							}
						}
						catch (NumberFormatException e) {
							System.out.println("\nPor favor ingresa un valor valido.\n");
						}
					}
				}
				else if (opcion_seleccionada == 2) {
					String nombreProyecto = input("\nIngresa el nombre del proyecto que desea abrir");
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
				System.out.println("\nPor favor selecciona una opcion valida");
			}
		}
	}
	
	private void ejecutarMenu() {
		boolean continuar = true;
		while(continuar) {
			try {	
				mostrarMenu2();
				int opcion_seleccionada = Integer.parseInt(input("\nPor favor selecciona una opcion"));
				if (opcion_seleccionada == 1) {
					String descripcionProyecto = input("\nIngresa la nueva descripcion del proyecto");
					proyecto.setDescripcion(descripcionProyecto);
					System.out.println("Se actualizó exitosamente la descripción del proyecto " + proyecto.getNombre() + ".");
				}
				else if (opcion_seleccionada == 2) {
					boolean continuar2 = true;
					while (continuar2) {
						try {
							System.out.println("Ingresa la nueva fecha aproximada del fin del proyecto:");
							dia = Integer.parseInt(input("Dia"));
							mes = Integer.parseInt(input("Mes"));
							anio = Integer.parseInt(input("Año"));
							LocalDate fechaFin = LocalDate.of(anio, mes, dia);
							if (fechaFin.isAfter(LocalDate.now())) {
								proyecto.setFechaFin(fechaFin);
								System.out.println("\nSe actualizó exitosamente la fecha de fin del proyecto a " + proyecto.getFechaFin() + ".");
								continuar2 = false;
							}
							else {
								System.out.println("\nIngresa una fecha de fin que sea posterior a la fecha de inicio " + LocalDate.now() + "\n");
							}
						}
						catch (NumberFormatException e) {
							System.out.println("\nPor favor ingresa un valor valido.\n");
						}
					}
				}
				else if (opcion_seleccionada == 3) {
					nombreParticipante = input("\nIngresa el nombre del participante que desea añadir al proyecto");
					correoParticipante = input("Ingresa el correo del participante que desea añadir al proyecto");
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
					boolean continuar4 = true;
					int propia = 0;
					while (continuar4) {
						try {
							propia = Integer.parseInt(input("\n¿Otro usuario va a realizar la actividad? (1=Si, 2=No)"));
							if (propia == 1 || propia == 2)
								continuar4 = false;
							else
								System.out.println("\nPor favor ingresa una opcion valida.\n");
						}
						catch (NumberFormatException e){
							System.out.println("\nPor favor ingresa un valor valido.\n");
						}
					}
					if (propia == 2) {
						participante = usuario;
					}
					else {
						boolean continuar40 = true;
						while (continuar40) {
							correoParticipante = input("\nIngresa el correo del participante que va a realizar la actividad");
							if (proyecto.isParticipantePorCorreo(correoParticipante)) {
								participante = proyecto.getParticipantePorCorreo(correoParticipante);
								continuar40 = false;
							}
							else {
								System.out.println("\nNo existe un participante con este correo.");
							}
						}
					}
					if (seguir == true) {
						// Atributos de la actividad
						String nombreActividad = input("Ingresa el nombre de la actividad");
						String descripcion = input("Ingresa la descripcion de la actividad");
						//Tipo de actividad
						String tipo = tipo();
						//Fecha de inicio de la atividad
						LocalDate fecha = LocalDate.now();
						//Hora de inicio de la actividad
						LocalTime horaInicio = LocalTime.now();
						//Hora de fin de la actividad
						LocalTime horaFin = null;
						//Crea una instancia de Actividad con la horaFinal = null, que despues será reemplazada
						actividad = Registro.nuevaActividad(nombreActividad, descripcion, tipo, fecha, horaInicio, horaFin, participante);
						//Trabajar en la actividad
						System.out.println("\nYa puede trabajar en su actividad...\n");
						boolean condicion = true;
						while (condicion) {
							String comando = input("Oprima el boton 'P' para pausar la actividad o oprima el boton 'F' para finalizar la actividad");
							if (comando.equals("P") || comando.equals("p")) {
								actividad.addTiempo(horaInicio, LocalTime.now());
								comando = input("Oprima el boton 'R' para reanudar la actividad");
							}
							else if (comando.equals("F") || comando.equals("f")) {
								horaFin = LocalTime.now();
								actividad.addTiempo(horaInicio, horaFin);
								actividad.setHoraFin(horaFin);
								condicion = false;
							}
						}
						
						//Crear una actividad
						
						//Almacenar la actividad
						proyecto.addActividadPorParticipante(actividad);
						proyecto.addDiaActividadPorParticipante(actividad);
						proyecto.addTipoActividadesPorParticipante(actividad);
						System.out.println("\n¡La actividad se registro con exito!");
						//Imprimir tiempo
						System.out.println("¡" + participante.getNombre()  + " trabajó en esta actividad un total de " + actividad.getTiempoTotal() + " minutos!");						
					}
				}
				
				else if (opcion_seleccionada == 5) {
					int propia = Integer.parseInt(input("\n¿Vas a registrar la actividad de otra persona? (1=Si, 2=No)"));
					if (propia == 2) {
						participante = usuario;
					}
					else {
						boolean continuar5 = true;
						while (continuar5) {
							correoParticipante = input("\nIngresa el correo del participante que realizó la actividad");
							if (proyecto.isParticipantePorCorreo(correoParticipante)) {
								participante = proyecto.getParticipantePorCorreo(correoParticipante);
								continuar5 = false;
							}
							else {
								System.out.println("\nNo existe un participante registrado con este correo.");
							}
						}
					}
					// Atributos de la actividad
					String nombreActividad = input("Ingresa el nombre de la actividad");
					String descripcion = input("Ingresa la descripcion de la actividad");
					//Tipo de actividades
					String tipo = tipo();
					//Fecha en la que se realizó la actividad
					boolean continuar50 = true;
					LocalDate fecha = null;
					LocalTime horaFin = null;
					while (continuar50) {
						try {
							System.out.println("Ingresa la fecha en la que se realizo la actividad");
							dia = Integer.parseInt(input("Dia"));
							mes = Integer.parseInt(input("Mes"));
							anio = Integer.parseInt(input("Año"));
							fecha = LocalDate.of(anio, mes, dia);
							if (fecha.isAfter(LocalDate.now())) {
								System.out.println("Por favor ingresa una fecha anterior a " + LocalDate.now() + ".");
							}
							else{
								continuar50 = false;
							}
						}
						catch (NumberFormatException e){
							System.out.println("\nPor favor ingresa un valor valido.\n");
						}
					}							
					//Hora de inicio de la actividad
					System.out.println("Ingresa la hora de inicio de la actividad (0:00-23:59)");
					boolean continuar51 = true;
					while (continuar51) {
						try {
							hora = Integer.parseInt(input("Hora"));
							minuto = Integer.parseInt(input("Minuto"));
							LocalTime horaInicio = LocalTime.of(hora, minuto);
							//Hora de fin de la actividad
							boolean continuar52 = true;
							while (continuar52) {
								System.out.println("Ingresa la hora de fin de la actividad (0:00-23:59)");
								hora = Integer.parseInt(input("Hora"));
								minuto = Integer.parseInt(input("Minuto"));
								horaFin = LocalTime.of(hora, minuto);
								if (horaFin.isBefore(horaInicio)) {
									System.out.println("Por favor ingresa una hora posterior a " + horaInicio + ".");
								}
								else
									continuar52 = false;
							}
							continuar51 = false;
							//Crear una actividad
							Actividad actividad = Registro.nuevaActividad(nombreActividad, descripcion, tipo, fecha, horaInicio, horaFin, participante);
							//Almacenar la actividad
							proyecto.addActividadPorParticipante(actividad);
							proyecto.addDiaActividadPorParticipante(actividad);
							proyecto.addTipoActividadesPorParticipante(actividad);
							System.out.println("\n¡La actividad se registro con exito!");
							//Imprimir tiempo
							actividad.addTiempo(horaInicio, horaFin);
							System.out.println("¡" + participante.getNombre()  + " trabajó en esta actividad un total de " + actividad.getTiempoTotal() + " minutos!");
							
						}
						catch (NumberFormatException e){
							System.out.println("\nPor favor ingresa un valor valido.\n");
						}
					}		
				}
				
				else if (opcion_seleccionada == 6) {
					String nombreActividad = input("Ingresa el nombre de la actividad que desea modificar");
					if (Registro.isActividad(nombreActividad)) {
						Actividad actividad = Registro.getActividad(nombreActividad);
						boolean continuar60 = true;
						while (continuar60) {
							try {
								System.out.println("Ingresa la nueva hora de fin de la actividad (0:00-23:59)");
								hora = Integer.parseInt(input("Hora"));
								minuto = Integer.parseInt(input("Minuto"));
								LocalTime horaFin = LocalTime.of(hora, minuto);
								LocalTime horaInicio = actividad.getHoraInicio();
								if (horaFin.isBefore(horaInicio)) {
									System.out.println("Por favor ingresa una hora posterior a " + horaInicio + ".");
								}
								else
									continuar60 = false;
									actividad.setHoraFin(horaFin);
									System.out.println("\nSe actualizó exitosamente la hora de fin de la actividad a " + actividad.getHoraFin() + ".");
							}
							catch (NumberFormatException e){
								System.out.println("\nPor favor ingresa un valor valido.\n");
							}
						}
					}
					else {
						System.out.println("No existe una actividad con este nombre");
					}
				}
				
				else if (opcion_seleccionada == 7) {
					boolean seguir = true;
					int propia = Integer.parseInt(input("\n¿Necesitas el reporte de otro participante? (1=Si, 2=No)"));
					if (propia == 2) {
						participante = usuario;
					}
					else {
						correoParticipante = input("\nIngrese el correo del participante");
						if (proyecto.isParticipantePorCorreo(correoParticipante)) {
							participante = proyecto.getParticipantePorCorreo(correoParticipante);
						}
						else {
							System.out.println("\nNo existe un participante con este correo.");
							seguir = false;
						}
					}
					if (seguir == true) {
						//Tipo actividad
						String tipo = tipo();
						//Fecha de consulta
						System.out.println("Ingrese la fecha que desea consultar en el reporte:");
						dia = Integer.parseInt(input("Dia"));
						mes = Integer.parseInt(input("Mes"));
						anio = Integer.parseInt(input("Año"));
						LocalDate fecha = LocalDate.of(anio, mes, dia);
						//Reporte
						Reporte.getReporte(proyecto, participante, tipo, fecha);
					}
				}
				else if (opcion_seleccionada == 8) {
					System.out.println("\nVolviendo al anterior menu...");
					continuar = false;
				}
				else {
					System.out.println("Por favor seleccione una opcion valida");
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
		System.out.println("4. Realizar una actividad");
		System.out.println("5. Registar una actividad pasada");
		System.out.println("6. Cambiar la hora de fin una actividad");		
		System.out.println("7. Consultar reporte de un participante");
		System.out.println("8. Volver al anterior menu");
	}
	
	private void mostrarTipoActividades() {
		System.out.println("Seleccione un tipo de actividad");
		System.out.println("1. Documentacion");
		System.out.println("2. Implementacion");
		System.out.println("3. Pruebas");
		System.out.println("4. Investigacion");
		System.out.println("5. Diseño");
		System.out.println("6. Analisis");
		System.out.println("7. Otra");
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
	
	//*************************************************************************************************
	// Funciones auxiliares
	//*************************************************************************************************
	
	private String tipo() {
		String tipo = null;
		mostrarTipoActividades();
		int tipoActividad = Integer.parseInt(input("Ingrese el tipo de actividad"));
		boolean continuar = true;
		while(continuar) {
			try {
				if (tipoActividad == 1) {
					tipo = "Documentacion";
					continuar = false;
				}
				else if (tipoActividad == 2) {
					tipo = "Implementacion";
					continuar = false;
				}
				else if (tipoActividad == 3) {
					tipo = "Pruebas";
					continuar = false;
				}
				else if (tipoActividad == 4) {
					tipo = "Investigacion";
					continuar = false;
				}
				else if (tipoActividad == 5) {
					tipo = "Diseño";
					continuar = false;
				}
				else if (tipoActividad == 6) {
					tipo = "Analisis";
					continuar = false;
				}
				else if (tipoActividad == 7) {
					tipo = input("Ingrese el tipo de actividad");
					continuar = false;
				}
				else {
					System.out.println("Por favor seleccione una opcion valida");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("\nDebe seleccionar uno de las opciones");
			}
		}
		return tipo;
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
