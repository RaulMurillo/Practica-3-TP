package tp.pr3.control;

/**
 * Clase que implementa una excepcion producida cuando se intenta jugar a un
 * juego donde la superficie no tiene capacidad para almacenar el numero de
 * celulas introducidas por el usuario.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public final class ErrorDeInicializacion extends Exception {
	public ErrorDeInicializacion() {
		super("Error de inicializacion. Eñ numero de celulas debe ser menor.");
	}
}
