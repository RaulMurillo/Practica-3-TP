package tp.pr3.control;

/**
 * Clase que implementa una excepcion producida cuando, en una seleccion de
 * opciones, la indicada por el usuario no concuerda con ninguna de las dadas.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class SeleccionNoValida extends Exception {
	public SeleccionNoValida() {
		super("Opcion no valida.");
	}

}
