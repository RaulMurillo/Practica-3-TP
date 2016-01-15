
package tp.pr3.main;

import tp.pr3.control.*;
import tp.pr3.logica.*;

import java.util.Scanner;

/**
 * Clase que contiene el metodo main de la practica.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class main {

	/**
	 * Crea el mundo, el controlador y realiza la simulacion.
	 * 
	 * @param args
	 *            .
	 */
	public static void main(String[] args) {
		Mundo mundo = new MundoSimple(3, 3, 3);
		Scanner in = new Scanner(System.in);
		Controlador controlador = new Controlador(mundo, in);
		controlador.realizarSimulacion();
	}
}
