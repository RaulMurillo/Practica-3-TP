package tp.pr3.control;

/**
 * Clase que implementa una excepcion producida cuando se intenta acceder a
 * posiciones de la superficie que no existen.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class IndicesFueraDeRango extends Exception {

	public IndicesFueraDeRango() {
		super("No existe dicha posición de la superficie.");
	}
}
