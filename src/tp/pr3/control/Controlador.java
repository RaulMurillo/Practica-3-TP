package tp.pr3.control;

import tp.pr3.logica.*;

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
			Comando comando = ParserComandos.parseaComando(array);
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

}
