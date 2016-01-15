package tp.pr3.control;

/**
 * Clase que implementa una excepcion producida cuando, leyendo un fichero, se
 * encuentran palabras que no se corresponden con el formato estipulado para el
 * fichero.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class PalabraIncorrecta extends Exception {
	public PalabraIncorrecta() {
		super("El fichero contiene valores de juego incorrectos.");
	}
}
