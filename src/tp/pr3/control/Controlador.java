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
				Comando comando = ParserComandos.parseaComando(array);
			} catch (NumberFormatException e) {
				throw new FormatoNumericoIncorrecto();
			} catch (ErrorDeInicializacion f) {

			} catch (IndicesFueraDeRango g) {

			} catch (PalabraIncorrecta h) {

			} catch (FileNotFoundException k) {
				throw new ArchivoNoEncontrado();
			} catch (IOException l) {

			}
			if (comando != null) {
				comando.ejecuta(this);
			} else
				System.out.println("Comando no válido.");
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

	public void cargar(String nombreFichero) {
		/*
		 * 
		 */
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

	public void creaCelula(int f, int c){
		Casilla casilla = new Casilla(f, c);
		if(this.mundo instanceof MundoComplejo){
			System.out.print("De que tipo: Compleja (1) o Simple (2): ");
			int tipo;
			try {
				tipo = in.nextInt();
			}catch(NumberFormatException e){
				throw new FormatoNumericoIncorrecto();
			}catch(IndicesFueraDeRango2 f){
				...
			}
			if(tipo == 1){
				if (!((MundoComplejo) mundo).crearCelulaCompleja(casilla)){
					System.out.println("No se pudo crear la celula, " + "posición no válida");
				}
			}
			else
				if (!((MundoComplejo) mundo).crearCelulaSimple(casilla)){
					System.out.println("No se pudo crear la celula, " + "posición no válida");
				}
		}
		//El mundo es simple o la célula a crear lo es
		else{
			if (!mundo.crearCelulaSimple(casilla)) {
				System.out.println("No se pudo crear la celula, " + "posición no válida");
			}
		}
			
			
		
	}

	public void eliminaCelula(int f, int c) {
		if (!mundo.eliminarCelula(f, c)) {
			System.out.println("No se pudo eliminar la celula, " + "posicion no valida");
		}
	}

	public void inicia() {
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
}
