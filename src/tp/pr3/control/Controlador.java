package tp.pr3.control;

import tp.pr3.logica.*;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase que contiene el interprete de los posibles comandos que se pueden
 * ejecutar en consola.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class Controlador {
	// Mundo sobre el que ejecutar los comandos.
	private Mundo mundo;
	// Scanner para realizar las operaciones de lectura.
	private Scanner in;
	// Controla el fin de la simulacion.
	private boolean simulacionTerminada;

	/**
	 * Constructor de la clase. Inicializa los atributos.
	 * 
	 * @param mundo
	 *            Mundo sobre el que se ejecutaran los comandos.
	 * @param in
	 *            Scanner para realizar las operaciones de lectura.
	 */
	public Controlador(Mundo mundo, Scanner in) {
		this.mundo = mundo;
		this.in = in;
		this.simulacionTerminada = false;
	}

	/**
	 * Bucle en el que se pide un comando al usuario y se ejecuta dicho comando.
	 * Se continua pidiendo y ejecutando comandos (controlando excepciones)
	 * hasta que se introduce el comando SALIR-
	 */
	public void realizarSimulacion() {
		String cadena;
		do {
			mostrar();
			System.out.print("Comando > ");
			cadena = in.nextLine();
			/*
			 * Convierto todo a mayúsculas
			 * (http://www.forosdelweb.com/f13/validar-palabra-sin-importar-
			 * mayuscula-minuscula-511492/)
			 */
			cadena = cadena.toUpperCase();
			String[] array = cadena.split("\\s+");
			try {
				Comando comando = ParserComandos.parseaComando(array);
				comando.ejecuta(this);
			} catch (FormatoNumericoIncorrecto e) {
				System.out.println(e.getMessage());
			} catch (ErrorDeInicializacion e) {
				System.out.println(e.getMessage());
			} catch (IndicesFueraDeRango e) {
				System.out.println(e.getMessage());
			} catch (PalabraIncorrecta e) {
				System.out.println(e.getMessage());
			} catch (ArchivoNoEncontrado e) {
				System.out.println(e.getMessage());
			} catch (ArchivoIncorrecto e) {
				System.out.println(e.getMessage());
			} catch (ComandoNoValido e) {
				System.out.println(e.getMessage());
			} catch (SeleccionNoValida e) {
				System.out.println(e.getMessage());
			} catch (PosicionNoValida e) {
				System.out.println(e.getMessage());
			}

		} while (!simulacionTerminada); // El bucle termina cuando el
										// usuario teclea el comando
										// SALIR, que pone el
										// booleano a true.
		System.out.println("Fin de la simulación...");
	}

	/**
	 * Muestra por pantalla el mundo.
	 */
	public void mostrar() {
		System.out.println(mundo);
	}

	/**
	 * Cambia el valor del booleano de simulación a true.
	 */
	public void setSimulacionTerminadaTrue() {
		this.simulacionTerminada = true;
	}

	/**
	 * Muestra la ayuda referente a los distintos comandos ejecutables.
	 */
	public void muestraAyuda() {
		System.out.print(ParserComandos.AyudaComandos());
	}

	/**
	 * Crea una celula en la posicion indicada, distinguiendo si se esta jugando
	 * en un mundo simple o complejo.
	 * 
	 * @param f
	 *            Posicion fila de la celula.
	 * @param c
	 *            Posicion columna de la celula.
	 * @throws PosicionNoValida
	 * @throws SeleccionNoValida
	 * @throws IndicesFueraDeRango
	 */
	public void creaCelula(int f, int c) throws PosicionNoValida, SeleccionNoValida, IndicesFueraDeRango {
		this.fueraDeRango(f, c);
		Casilla casilla = new Casilla(f, c);
		// Caso Mundo Complejo
		if (!this.esSimple()) {
			System.out.print("De que tipo: Compleja (1) o Simple (2): ");
			String tipo;
			tipo = in.nextLine();
			// if (tipo != "1" && tipo != "2")
			// throw new SeleccionNoValida();
			if (tipo.equals("1")) {
				if (!((MundoComplejo) mundo).crearCelulaCompleja(casilla)) {
					throw new PosicionNoValida("Casilla ocupada.");
				}
			} else if (tipo.equals("2")) {
				if (!((MundoComplejo) mundo).crearCelulaSimple(casilla)) {
					throw new PosicionNoValida("Casilla ocupada.");
				}
			} else
				throw new SeleccionNoValida();
		}
		// El mundo es simple
		else {
			if (!mundo.crearCelulaSimple(casilla)) {
				throw new PosicionNoValida("Casilla ocupada.");
			}
		}
	}

	/**
	 * Elimina una celula de la posicion indicada
	 * 
	 * @param f
	 *            Posicion fila de la celula.
	 * @param c
	 *            Posicion columna de la celula.
	 * @throws IndicesFueraDeRango
	 */
	public void eliminaCelula(int f, int c) throws PosicionNoValida, IndicesFueraDeRango {
		this.fueraDeRango(f, c);
		if (!mundo.eliminarCelula(f, c)) {
			throw new PosicionNoValida("No hay celulas en esa posicion.");
		}
	}

	/**
	 * Inicia un nuevo mundo con los valores de juego fijados en ese momento.
	 */
	public void inicia() {
		mundo.vaciarMundo();
		mundo.inicializaMundo();
	}

	/**
	 * Cambia del juego actual a otro con otros valores.
	 * 
	 * @param mundo
	 *            Nuevo mundo sobre el que se realiza la simulacion.
	 */
	public void juega(Mundo mundo) {
		this.mundo = mundo;
	}

	/**
	 * Realiza un paso sobre todas las celulas del mundo.
	 */
	public void daUnPaso() {
		mundo.evoluciona();
	}

	/**
	 * Termina la simulacion.
	 */
	public void sal() {
		this.simulacionTerminada = true;
	}

	/**
	 * Vacia el mundo.
	 */
	public void vacia() {
		mundo.vaciarMundo();
	}

	/**
	 * Verifica si el los indices de aquellos comandos que contengan un valor de
	 * posicion se encuentran dentro del rango establecido. Sólo se emplea con
	 * los comandos CrearCelula y EliminarCelula.
	 * 
	 * @param fila
	 *            Numero de fila a procesar.
	 * @param columna
	 *            Numero de columna a procesar.
	 * @throws IndicesFueraDeRango
	 *             Cuando los indices no se encuentran en el rango.
	 */
	public void fueraDeRango(int fila, int columna) throws IndicesFueraDeRango {
		if (fila < 0 || fila >= mundo.getFilas() || columna < 0 || columna >= mundo.getColumnas()) {
			throw new IndicesFueraDeRango();
		}
	}

	/**
	 * Almacena en un fichero la configuracion del juego actual.
	 * 
	 * @param nombreFichero
	 *            Fichero donde almacenar los datos.
	 * @throws ArchivoIncorrecto
	 */
	public void guarda(String nombreFichero) throws ArchivoIncorrecto {
		// Escritura de datos
		try {
			PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(nombreFichero)));
			mundo.guardar(salida);
			salida.close();
		} catch (IOException e) {
			throw new ArchivoIncorrecto();
		}
	}

	/**
	 * Carga como juego actual, el almacenado en un fichero.
	 * 
	 * @param nombreFichero
	 *            Fichero desde el que cargar los datos.
	 * @throws ArchivoNoEncontrado
	 * @throws ArchivoIncorrecto
	 * @throws PalabraIncorrecta
	 */
	public void carga(String nombreFichero) throws ArchivoNoEncontrado, ArchivoIncorrecto, PalabraIncorrecta {
		// Lectura datos
		try {
			Scanner entrada = new Scanner(new File(nombreFichero));
			String tipo = entrada.nextLine();
			int f, c;
			f = Integer.parseInt(entrada.nextLine());
			c = Integer.parseInt(entrada.nextLine());
			if (f < 1 || c < 1) {
				entrada.close();
				throw new PalabraIncorrecta();
			}
			Mundo aux; // Evita problemas en caso de error.
			if (tipo.equals("simple")) {
				aux = new MundoSimple(f, c, 0);
				aux.cargar(entrada);
				this.mundo = aux;
			} else if (tipo.equals("complejo")) {
				aux = new MundoComplejo(f, c, 0, 0);
				aux.cargar(entrada);
				this.mundo = aux;
			}
			entrada.close();
		} catch (NumberFormatException e) {
			throw new PalabraIncorrecta();
		} catch (FileNotFoundException e) {
			throw new ArchivoNoEncontrado();
		} catch (NoSuchElementException e) {
			throw new PalabraIncorrecta();
		}
	}

	/**
	 * Indica si el mundo del controlador es simple.
	 * 
	 * @return true si es simple.
	 */
	public boolean esSimple() {
		return this.mundo.esSimple();
	}
}
