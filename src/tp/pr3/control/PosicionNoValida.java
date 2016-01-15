package tp.pr3.control;

/**
 * Clase que implementa una excepcion producida cuando se intenta acceder a una
 * posicion de la superficie no valida para cierto comando (ej. crear una celula
 * en una posicion previamente ocupada).
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class PosicionNoValida extends Exception {

	public PosicionNoValida() {
		super("Posicion del tablero no valida. ");
	}
	
	public PosicionNoValida(String text) {
		super(new PosicionNoValida().getMessage() + text);
	}
}
