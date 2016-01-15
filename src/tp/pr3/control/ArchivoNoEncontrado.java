package tp.pr3.control;

/**
 * Clase que implementa una excepcion producida cuando, al tratar de leer un
 * fichero, no se encuentra dicho fichero.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class ArchivoNoEncontrado extends Exception {
	public ArchivoNoEncontrado() {
		super("Archivo no encontrado.");
	}
}
