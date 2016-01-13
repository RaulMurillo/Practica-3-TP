package tp.pr3.control;

import tp.pr3.logica.*;

import java.io.*;
import java.util.Scanner;

/**
 * Esta clase contiene el interprete de los posibles comandos que se pueden
 * ejecutar en consola.
 * 
 * @version 2.0, 09/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */

public class Controlador {
	private Mundo mundo; // Mundo sobre el que ejecutar los comandos.
	private Scanner in; // Scanner para realizar las operaciones de lectura.
	private boolean simulacionTerminada; // Controla el fin de la simulacion.

	/**
	 * Inicializa los atributos.
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
				// if(mundo )
				Comando comando = ParserComandos.parseaComando(array);
				this.fueraDeRango(comando);
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

	public void muestraAyuda() {
		System.out.print(ParserComandos.AyudaComandos());
	}

	public void creaCelula(int f, int c) throws PosicionNoValida, SeleccionNoValida {
		Casilla casilla = new Casilla(f, c);
		// Caso Mundo Complejo
		if (this.mundo instanceof MundoComplejo) {
			System.out.print("De que tipo: Compleja (1) o Simple (2): ");
			String tipo;
			tipo = in.nextLine();
			// if (tipo != "1" && tipo != "2")
			// throw new SeleccionNoValida();
			if (tipo.equals("1")) {
				if (!((MundoComplejo) mundo).crearCelulaCompleja(casilla)) {
					throw new PosicionNoValida();
				}
			} else if (tipo.equals("2")) {
				if (!((MundoComplejo) mundo).crearCelulaSimple(casilla)) {
					throw new PosicionNoValida();
				}
			}
			else throw new SeleccionNoValida();
		}
		// El mundo es simple
		else {
			if (!mundo.crearCelulaSimple(casilla)) {
				throw new PosicionNoValida();
			}
		}
	}

	public void eliminaCelula(int f, int c) {
		if (!mundo.eliminarCelula(f, c)) {
			System.out.println("No se pudo eliminar la celula, " + "posicion no valida");
		}
	}

	public void inicia() {
		mundo.vaciarMundo();
		mundo.inicializaMundo();
	}

	public void juega(Mundo mundo) {
		this.mundo = mundo;
	}

	public void daUnPaso() {
		mundo.evoluciona();
	}

	public void sal() {
		this.simulacionTerminada = true;
	}

	public void vacia() {
		mundo.vaciarMundo();
	}

	/**
	 * En caso de que el comando requiera verificar el rango de los indices,
	 * lanza una excepcion si dichos indices no se encuentran en el rango
	 * establecido.
	 * 
	 * @param comando
	 *            Sólo verifica los comandos CrearCelula y EliminarCelula
	 * @throws IndicesFueraDeRango
	 */
	public void fueraDeRango(Comando comando) throws IndicesFueraDeRango {
		if (comando instanceof CrearCelula) {
			int filaComando = ((CrearCelula) comando).getFilas();
			int columnaComando = ((CrearCelula) comando).getColumnas();
			if (filaComando < 0 || filaComando >= mundo.getFilas() || columnaComando < 0
					|| columnaComando >= mundo.getColumnas()) {
				throw new IndicesFueraDeRango();
			}
		} else if (comando instanceof EliminarCelula) {
			int filaComando = ((EliminarCelula) comando).getFilas();
			int columnaComando = ((EliminarCelula) comando).getColumnas();
			if (filaComando < 0 || filaComando >= mundo.getFilas() || columnaComando < 0
					|| columnaComando >= mundo.getColumnas()) {
				throw new IndicesFueraDeRango();
			}
		}
	}

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

	public void carga(String nombreFichero) throws ArchivoNoEncontrado, ArchivoIncorrecto, PalabraIncorrecta {
		// Lectura datos
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(nombreFichero));
			String tipo = entrada.readLine();
			int f, c;
			f = Integer.parseInt(entrada.readLine());
				c = Integer.parseInt(entrada.readLine());
				if (f < 1 || c < 1) {
					entrada.close();
					throw new PalabraIncorrecta();
				}
			if (tipo.equals("simple")) {				
				mundo = new MundoSimple(f, c, 0);
				mundo.cargar(entrada);
			}
			else if (tipo.equals("complejo")) {
				mundo = new MundoComplejo(f, c, 0, 0);
				mundo.cargar(entrada);
			}
			entrada.close();
		}catch (NumberFormatException e){
			throw new PalabraIncorrecta();
		} catch (FileNotFoundException e) {
			throw new ArchivoNoEncontrado();
		} catch (IOException e) {
			throw new ArchivoIncorrecto();
		}
	}
}
