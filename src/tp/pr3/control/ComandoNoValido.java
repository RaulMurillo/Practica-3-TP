package tp.pr3.control;

/**
 * Clase que implementa una excepcion producida cuando se introduce un comando
 * no reconocido.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class ComandoNoValido extends Exception {
	public ComandoNoValido() {
		super("Comando no valido.");
	}
}
