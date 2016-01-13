package tp.pr3.logica;
import java.io.*;

import tp.pr3.control.PalabraIncorrecta; 

/**
 * Es una clase abstracta de la que heredan las clases concretas celula compleja
 * y celula simple.
 * 
 * @version 1.0, 07/11/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */

interface Celula {

	/**
	 * Realiza el movimiento de una celula colocada en la posicion (f, c) de la
	 * superficie.
	 * 
	 * @param f
	 *            coordenada fila
	 * @param c
	 *            coordenada columna
	 * @param superficie
	 *            superficie donde se encuentra la celula
	 * @return casilla a la que se ha movido la celula, null en caso contrario
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	
	public abstract String toString();
	
	public abstract void guardar(PrintWriter salida);
	
	public abstract void cargar(int n, int m) throws PalabraIncorrecta;

}
