package tp.pr3.control;

/**
 * Clase que implementa una excepcion producida cuando se espera un numero pero
 * se teclean caracteres no numericos.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class FormatoNumericoIncorrecto extends Exception {

	public FormatoNumericoIncorrecto() {
		super("Error de formato. Por favor, introduce un numero.");
	}
}
