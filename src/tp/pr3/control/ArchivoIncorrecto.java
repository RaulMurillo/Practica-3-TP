package tp.pr3.control;

/**
 * Clase que implementa una excepcion producida cuando no se puede abrir un
 * fichero.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class ArchivoIncorrecto extends Exception {
	public ArchivoIncorrecto() {
		super("No se pudo abrir el archivo.");
	}
}
